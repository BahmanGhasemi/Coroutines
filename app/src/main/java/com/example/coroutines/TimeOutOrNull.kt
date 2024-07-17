package com.example.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    println("program starts...")
    val result = withTimeoutOrNull(2000) {
        for (i in 1..500) {
            print("$i. ")
            delay(400)
        }
        "loop is done!"
    }
    println("\nresult: $result")
    println("program ends...")
}