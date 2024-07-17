package com.example.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

fun main() = runBlocking {
    println("program starts...")
    val time = measureTime {
        val numOne = async(start = CoroutineStart.LAZY) { getNumOne() }
        val numTwo = async(start = CoroutineStart.LAZY) { getNumTwo() }
        println("sum is ${numOne.await() + numTwo.await()}")
    }
    println("it takes $time ms.")
    println("program ends...")
}

suspend fun getNumOne(): Int {
    delay(1000)
    println("getNumOne() executed!")
    return 25
}

suspend fun getNumTwo(): Int {
    delay(1000)
    println("getNumTwo() executed!")
    return 16
}
