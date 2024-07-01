package com.vectorincng.socketchat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vectorincng.socketchat.data.remote.MessageService
import com.vectorincng.socketchat.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val client: MessageService
): ViewModel() {

    private val _state = MutableStateFlow<State<String>>(State.failed(""))
    val state: StateFlow<State<String>> = _state

    private var job: Job? = null

    fun login(username: String) {
        job?.cancel() // Cancel any existing job to prevent leaks
        job = viewModelScope.launch {
            try {
                client.connectToSocket(username)
                    .onEach { newState ->
                        _state.value = newState
                    }
                    .collect()
            } catch (e: Exception) {
                println("WebSocket connection failed: ${e.message}")
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel() // Cancel the job to ensure the coroutine is stopped
        viewModelScope.launch {
            client.closeConnection()
        }
    }
}