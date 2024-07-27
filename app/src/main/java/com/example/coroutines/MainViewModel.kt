package com.example.coroutines

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class MainViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)
        private set

    var state = mutableStateOf("")
        private set

    fun calculate(number: Long) {
        viewModelScope.launch {
            isLoading.value = true
            var result: BigInteger = BigInteger.ONE
            val time = measureTimeMillis {
                result = calculateFactorial(number)
            }
            val resultToString = withContext(Dispatchers.Default) {
                "It takes $time ms to compute!\n" +
                        "result is: $result"
            }
            state.value = resultToString
            isLoading.value = false
        }
    }

    fun calculateParallel(number: Int, coroutineNumber: Int) {
        viewModelScope.launch {
            isLoading.value = true
            val rangeNumber = number / coroutineNumber
            val subNumbers = listOf(
                Pair(1, rangeNumber),
                Pair(rangeNumber + 1, rangeNumber + rangeNumber),
                Pair(rangeNumber + rangeNumber + 1, number)
            )
            var result: BigInteger = BigInteger.ONE
            val times = measureTimeMillis {
                result = withContext(Dispatchers.Default) {
                    subNumbers.map { (start, end) ->
                        async { calculateFactorialParallel(start, end) }
                    }.awaitAll()
                        .fold(BigInteger.ONE) { acc, el ->
                            acc.times(el)
                        }
                }
            }

            val stringResult = "it takes $times ms \n result: ${convertToString(result)}"

            state.value = stringResult
            isLoading.value = false
        }
    }

    private suspend fun calculateFactorial(number: Long): BigInteger {
        return withContext(Dispatchers.Default + CoroutineName("Factorial Calculation")) {
            var result = BigInteger.ONE
            for (i in number downTo result.toInt())
                result = result.times(BigInteger.valueOf(i))
            result
        }
    }

    private suspend fun calculateFactorialParallel(startNumber: Int, endNumber: Int): BigInteger {
        var result: BigInteger = BigInteger.ONE
        for (i in startNumber..endNumber)
            result *= BigInteger.valueOf(i.toLong())
        return result
    }

    private suspend fun convertToString(number: BigInteger) =
        withContext(Dispatchers.Default) { number.toString() }
}