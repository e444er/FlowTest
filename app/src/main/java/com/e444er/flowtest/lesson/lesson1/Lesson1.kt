package com.e444er.flowtest.lesson.lesson1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

suspend fun main() {
    val numbers = listOf(2, 4, 32, 41, 47, 84, 116, 53, 59, 61, 67).asFlow()
    numbers.filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .collect { println(it) }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}