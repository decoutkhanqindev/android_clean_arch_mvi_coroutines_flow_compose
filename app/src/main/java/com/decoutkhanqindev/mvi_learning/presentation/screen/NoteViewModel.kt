package com.decoutkhanqindev.mvi_learning.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decoutkhanqindev.mvi_learning.domain.model.Note
import com.decoutkhanqindev.mvi_learning.domain.usecase.AddNoteUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.DeleteNoteUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.GetAllNotesUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.GetNoteByIdUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.SavePassKeyUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.SearchNotesUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class NoteViewModel(
  private val addNoteUseCase: AddNoteUseCase,
  private val getAllNotesUseCase: GetAllNotesUseCase,
  private val getNoteByIdUseCase: GetNoteByIdUseCase,
  private val searchNoteUseCase: SearchNotesUseCase,
  private val updateNoteUseCase: UpdateNoteUseCase,
  private val deleteNoteUseCase: DeleteNoteUseCase,
  private val savePassKeyUseCase: SavePassKeyUseCase,
  private val getPassKeyUseCase: SavePassKeyUseCase,
) : ViewModel() {
  private val _noteListState = MutableStateFlow<NoteListState>(NoteListState.Initial)
  val noteListState: StateFlow<NoteListState> = _noteListState.asStateFlow()

  private val _noteDetailState = MutableStateFlow<NoteDetailState>(NoteDetailState.Initial)
  val noteDetailState: StateFlow<NoteDetailState> = _noteDetailState.asStateFlow()

  private val _addNoteState = MutableStateFlow<AddNoteState>(AddNoteState.Initial)
  val addNoteState: StateFlow<AddNoteState> = _addNoteState.asStateFlow()

  private val _notePassKeyState =
    MutableStateFlow<NotePassKeyState>(NotePassKeyState.Initial)
  val notePassKeyState: StateFlow<NotePassKeyState> = _notePassKeyState.asStateFlow()

  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  private val _events = Channel<NoteEvent>(capacity = Channel.CONFLATED)
  val events: Flow<NoteEvent> = _events.receiveAsFlow()

  init {
    observeSearchQuery()
    handleIntent(NoteIntent.GetAllNotes)
  }

  fun handleIntent(intent: NoteIntent) =
    when (intent) {
      is NoteIntent.AddNote -> addNote(intent.note)

      NoteIntent.GetAllNotes -> getAllNotes()

      is NoteIntent.GetNoteById -> getNoteById(intent.id)

      is NoteIntent.SearchNotes -> setSearchQuery(intent.query)

      is NoteIntent.UpdateNote -> updateNote(intent.note)

      is NoteIntent.DeleteNote -> deleteNote(intent.note)

      is NoteIntent.ChangePassKey -> setPassKey(intent.key)

      NoteIntent.SavePassKey -> savePassKey()
    }

  private fun addNote(note: Note) {
    viewModelScope.launch {
      _noteDetailState.value = NoteDetailState.Loading

      addNoteUseCase(note)
        .onSuccess {
          _events.trySend(NoteEvent.ShowSnackBar("Note added successfully"))
          _events.trySend(NoteEvent.NavigateBack)
        }
        .onFailure {
          _noteDetailState.value = NoteDetailState.Error
          _events.trySend(NoteEvent.ShowSnackBar("Failed to add note"))
          _events.trySend(NoteEvent.NavigateBack)
        }
    }
  }

  private fun getAllNotes() {
    viewModelScope.launch {
      getAllNotesUseCase()
        .onStart { _noteListState.value = NoteListState.Loading }
        .collect { result ->
          result.onSuccess { notes ->
            _noteListState.value = NoteListState.Success(notes)
          }.onFailure {
            _noteListState.value = NoteListState.Error
            _events.trySend(NoteEvent.ShowSnackBar("Failed to fetch notes"))
          }
        }
    }
  }

  private fun getNoteById(id: Long) {
    viewModelScope.launch {
      _noteDetailState.value = NoteDetailState.Loading

      getNoteByIdUseCase(id)
        .onSuccess { note ->
          _noteDetailState.value = NoteDetailState.Success(note = note)
        }.onFailure {
          _noteDetailState.value = NoteDetailState.Error
          _events.trySend(NoteEvent.ShowSnackBar("Failed to fetch note details"))
        }
    }
  }

  private fun updateNote(note: Note) {
    viewModelScope.launch {
      _noteDetailState.value = NoteDetailState.Loading

      updateNoteUseCase(note)
        .onSuccess {
          _events.trySend(NoteEvent.ShowSnackBar("Note updated successfully"))
          _events.trySend(NoteEvent.NavigateBack)
        }
        .onFailure {
          _noteDetailState.value = NoteDetailState.Error
          _events.trySend(NoteEvent.ShowSnackBar("Failed to update note"))
          _events.trySend(NoteEvent.NavigateBack)
        }
    }
  }

  private fun deleteNote(note: Note) {
    viewModelScope.launch {
      _noteDetailState.value = NoteDetailState.Loading

      deleteNoteUseCase(note)
        .onSuccess {
          _events.trySend(NoteEvent.ShowSnackBar("Note deleted successfully"))
          _events.trySend(NoteEvent.NavigateBack)
        }
        .onFailure {
          _noteDetailState.value = NoteDetailState.Error
          _events.trySend(NoteEvent.ShowSnackBar("Failed to delete note"))
          _events.trySend(NoteEvent.NavigateBack)
        }
    }
  }

  @OptIn(FlowPreview::class)
  private fun setSearchQuery(query: String) {
    _searchQuery.value = query
  }

  private fun observeSearchQuery() {
    val currentNotes = (_noteListState.value as? NoteListState.Success)?.notes ?: return

    viewModelScope.launch {
      _searchQuery
        .debounce(300L)
        .distinctUntilChanged()
        .flatMapLatest { query ->
          if (query.isBlank() && currentNotes.isEmpty()) getAllNotesUseCase()
          else searchNoteUseCase(query)
        }
        .onStart { _noteListState.value = NoteListState.Loading }
        .collect { result ->
          result.onSuccess { notes ->
            _noteListState.value = NoteListState.Success(notes)
          }.onFailure {
            _noteListState.value = NoteListState.Error
            _events.send(NoteEvent.ShowSnackBar("Failed to search"))
          }
        }
    }
  }

  private fun setPassKey(key: String) {
    _notePassKeyState.value = NotePassKeyState.FillingPassKey(key)
  }

  private fun savePassKey() {

  }


}
