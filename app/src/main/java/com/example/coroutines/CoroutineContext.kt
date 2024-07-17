package com.example.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("C1: ${Thread.currentThread().name}") // thread: main
        delay(20)
        println("C1 after: ${Thread.currentThread().name}") // thread: main
    }

    launch (Dispatchers.Default){
        println("C2: ${Thread.currentThread().name}") // thread: T1
        delay(50)
        println("C2 after: ${Thread.currentThread().name}") // thread: T1 or some other
    }

    launch (Dispatchers.Unconfined){
        println("C3: ${Thread.currentThread().name}") // thread: main
        delay(100)
        println("C3 after: ${Thread.currentThread().name}") // thread: some other as F1

        launch (coroutineContext){
            println("C4: ${Thread.currentThread().name}") // thread: F1
            delay(100)
            println("C4 after: ${Thread.currentThread().name}") // thread: F1
        }
    }

    launch (coroutineContext){
        println("C5: ${Thread.currentThread().name}") // thread: main
        delay(100)
        println("C5 after: ${Thread.currentThread().name}") // thread: maim
    }

    println("program ends...")
}