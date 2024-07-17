package com.example.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        println("starts on ${Thread.currentThread().name}")
        delay(500)
        println("ends on ${Thread.currentThread().name}")
    }

    Thread.sleep(1000)
    println("program ended!")
}
