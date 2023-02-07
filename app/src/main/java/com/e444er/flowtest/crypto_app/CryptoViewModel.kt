package com.e444er.flowtest.crypto_app


import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state: Flow<State> = repository.getCurrencyList()
    .filter { it.isNotEmpty() }
    .map { State.Content(currencyList = it) as State }
    .onStart { emit(State.Loading)
        Log.d("TAG", "onStart")
    }
    .onEach { Log.d("TAG", "onEach")  }
    .onCompletion {
        Log.d("TAG", "onCompletion")
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
