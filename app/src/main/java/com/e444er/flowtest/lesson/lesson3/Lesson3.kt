package com.e444er.flowtest.lesson.lesson3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    val result = getFlow().filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
//        .collect { println(it) }
//        .toList()
        .first()
//        .last()
    println(result)
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(2, 4, 32, 44, 47, 84, 116, 53, 59, 61, 67)
}

fun getFlow(): Flow<Int> {
    val numbers = getFlowByFlowOfBuilder()
    return flow {
//        numbers.collect {
//            println("Emit $it")
//            emit(it)
//        }
        emitAll(numbers)
    }
}


suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}