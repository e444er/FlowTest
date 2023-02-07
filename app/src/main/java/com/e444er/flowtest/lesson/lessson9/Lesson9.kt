package com.e444er.flowtest.lesson.lessson9

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main() {

    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch{
        repeat(5) {
            println("Emitted: $it")
            flow.emit(it)
            delay(1000)
        }
    }

    val job1 = coroutineScope.launch {
        flow.collect {
            println(it)
        }
    }

    delay(5000)
    val job2 = coroutineScope.launch {
        flow.collect {
            println(it)
        }
    }

    job1.join()
    job2.join()

}

fun getFlow(): Flow<Int> = flow {

    repeat(100) {
        println("Emitted: $it")
        emit(it)
        delay(1000)
    }
}