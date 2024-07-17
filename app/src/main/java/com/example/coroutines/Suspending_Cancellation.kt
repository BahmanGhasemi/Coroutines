package com.example.coroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("program starts at ${Thread.currentThread().name}")
    val job = launch {
        for (i in 1..500) {
            println("$i")
            delay(10)
        }
    }
    delay(300)
    job.cancelAndJoin()

    println("program ends at ${Thread.currentThread().name}")
}