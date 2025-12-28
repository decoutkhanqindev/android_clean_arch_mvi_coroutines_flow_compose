@file:Suppress("WRONG_INVOCATION_KIND")

package com.decoutkhanqindev.mvi_learning.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalContracts::class)
suspend inline fun <T> runSuspendCatching(
  context: CoroutineContext = EmptyCoroutineContext,
  crossinline block: suspend () -> T,
): Result<T> {
  contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }

  return try {
    Result.success(withContext(context) { block() })
  } catch (c: CancellationException) {
    throw c
  } catch (e: Throwable) {
    Result.failure(e)
  }
}

fun <T> Flow<T>.toResultFlow(): Flow<Result<T>> =
  this.map { value ->
    try {
      Result.success(value)
    } catch (c: CancellationException) {
      throw c
    } catch (e: Throwable) {
      Result.failure(e)
    }
  }

@Composable
inline fun <T> OneTimeEventCollection(
  lifecycleOwner: LifecycleOwner,
  state: Lifecycle.State = Lifecycle.State.STARTED,
  events: Flow<T>,
  crossinline onEvent: (T) -> Unit,
) {
  LaunchedEffect(events, lifecycleOwner) {
    lifecycleOwner.repeatOnLifecycle(state) {
      events.collect { onEvent(it) }
    }
  }
}