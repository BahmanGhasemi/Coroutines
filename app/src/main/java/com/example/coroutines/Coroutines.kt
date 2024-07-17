package com.example.coroutines

import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("running on ${Thread.currentThread().name}")
    }

    Thread.sleep(1000)
}