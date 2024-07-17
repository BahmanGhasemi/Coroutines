package com.example.coroutines

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    println("program starts...")
    try {
        withTimeout(2000) {
            for (i in 1..500) {
                print("$i. ")
                delay(400)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("\ncatch exception safely: ${e.message}")
    } finally {
        // ..code..
    }
    println("program ends...")
}