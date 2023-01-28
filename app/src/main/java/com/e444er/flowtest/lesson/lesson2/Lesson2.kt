package com.e444er.flowtest.lesson.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    getFlow().filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .collect { println(it) }
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(2, 4, 32, 41, 47, 84, 116, 53, 59, 61, 67)
}

fun getFlow(): Flow<Int> {
    val numbers = listOf(2, 4, 32, 41, 47, 84, 116, 53, 59, 61, 67)
    return flow {
        numbers.forEach {
            emit(it)
        }
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