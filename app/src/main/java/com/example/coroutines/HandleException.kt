package com.example.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("program starts at ${Thread.currentThread().name}")
    val job = launch {
        try{
            for (i in 1..500){
                println("$i")
                delay(1)
            }
        }catch (e:CancellationException){
            println("catch exception safely: ${e.message}")
        }finally {
            withContext(NonCancellable) {
                delay(500)
                println("finally executed!")
            }
        }
    }
    delay(50)
    job.cancel(CancellationException("My Custom Exception"))
    job.join()
    println("program ends at ${Thread.currentThread().name}")
}