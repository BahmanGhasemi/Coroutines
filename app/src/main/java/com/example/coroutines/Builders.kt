package com.example.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    println("started on ${Thread.currentThread().name}")

    val job = launch {
        println("start launching on ${Thread.currentThread().name}")
        delay(100)
        println("end launching on ${Thread.currentThread().name}")
    }

    val result = async {
        println("start async on ${Thread.currentThread().name}")
        delay(100)
        println("end async on ${Thread.currentThread().name}")
        "async is done!"
    }

    job.join()
    println(result.await())
    delay(1000)
    println("ended on ${Thread.currentThread().name}")
}