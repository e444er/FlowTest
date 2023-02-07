package com.e444er.flowtest.lesson.lesson8

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main() {

    val flow = getFlow()

    val job1 = coroutineScope.launch {
        flow.first().let {
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