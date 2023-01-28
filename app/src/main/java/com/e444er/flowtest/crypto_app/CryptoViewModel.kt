package com.e444er.flowtest.crypto_app


import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

//    private val _state = MutableLiveData<State>(State.Initial)
    val state: LiveData<State> = repository.getCurrencyList()
    .filter { it.isNotEmpty() }
    .map { State.Content(currencyList = it) as State }
    .onStart { emit(State.Loading) }
    .asLiveData()



//    private fun loadData() {
//        viewModelScope.launch {
//            repository.getCurrencyList()
//                .onStart {
//                    val currentState = _state.value
//                    if (currentState !is State.Content || currentState.currencyList.isEmpty()) {
//                        _state.value = State.Loading
//                    }
//                }
//                .catch {  }
//                .filter { it.isNotEmpty() }
//                .onEach {
//                    _state.value = State.Content(currencyList = it)
//                }
//                .collect()
//        }
//        repository.getCurrencyList()
//            .filter { it.isNotEmpty() }
//            .map { State.Content(currencyList = it) as State }
//            .onStart { emit(State.Loading) }
//            .onEach { _state.value = it }
//            .launchIn(viewModelScope)
//    }
}