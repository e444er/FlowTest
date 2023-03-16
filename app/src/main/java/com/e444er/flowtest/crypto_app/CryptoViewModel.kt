package com.e444er.flowtest.crypto_app


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    private val loadingFlow = MutableSharedFlow<State>()

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart { emit(State.Loading) }
        .mergeWith(loadingFlow)


    private fun <T>Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }

    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refreshList()
        }
    }


//    val state: LiveData<State> = repository.getCurrencyList()
//        .filter { it.isNotEmpty() }
//        .map { State.Content(currencyList = it) as State }
//        .onStart { emit(State.Loading)
//            Log.d("TAG", "onStart")
//        }
//        .onEach { Log.d("TAG", "onEach")  }
//        .onCompletion {
//            Log.d("TAG", "onCompletion")
//        }
//        .asLiveData()

//    private var job: Job? = null
//    private var isResumed = false
//
//    fun loadData() {
//        isResumed =true
//        if (job != null) return
//        job = repository.getCurrencyList()
//            .onStart {
//                _state.value = State.Loading
//            }
//            .catch { }
//            .filter { it.isNotEmpty() }
//            .onEach {
//                _state.value = State.Content(currencyList = it)
//            }
//            .launchIn(viewModelScope)
//    }
//
//    fun stopLoading() {
//        viewModelScope.launch {
//            delay(5000)
//            if (!isResumed){
//                job?.cancel()
//                job = null
//            }else{
//                isResumed = false
//            }
//
//        }
//    }
}


//        repository.getCurrencyList()
//            .filter { it.isNotEmpty() }
//            .map { State.Content(currencyList = it) as State }
//            .onStart { emit(State.Loading) }
//            .onEach { _state.value = it }
//            .launchIn(viewModelScope)
//    }
