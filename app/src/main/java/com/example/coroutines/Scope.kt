package com.example.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    println("blocking scope: $this")

    launch {
        println("launch scope: $this")
    }

    async {
        println("async scope: $this")
    }
    println("program ends...")
}