package com.e444er.flowtest.crypto_app

sealed class State {
    object Initial : State()
    object Loading : State()
    data class Content(val currencyList: List<Currency>) : State()
}
