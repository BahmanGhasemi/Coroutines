package com.example.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("program starts at ${Thread.currentThread().name}")

    val job = launch(Dispatchers.Default) {
        for (i in 1..500){
            if (!isActive)
                break
            print("$i\t")
            delay(2)
        }
    }

    delay(50)
    job.cancelAndJoin()
    println("\nprogram ends at ${Thread.currentThread().name}")
}