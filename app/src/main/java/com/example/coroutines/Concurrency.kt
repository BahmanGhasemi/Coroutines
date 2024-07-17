package com.example.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("program starts...")

    val time = measureTimeMillis {

        val msgOne = async { getMessageOne() }
        val msgTwo = async { getMessageTwo() }
        println("result is: ${msgOne.await() + msgTwo.await()}")
    }

    println("it's take $time ms.")
    println("program ends...")
}

private suspend fun getMessageOne(): String {
    delay(1000)
    return "Hello "
}

private suspend fun getMessageTwo(): String {
    delay(1000)
    return "World"
}