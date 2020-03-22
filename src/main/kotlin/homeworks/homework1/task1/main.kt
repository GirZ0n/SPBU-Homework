package homeworks.homework1.task1

import java.lang.RuntimeException
import java.util.Scanner
import kotlin.system.exitProcess

fun getNumber(): Int {
    val scan = Scanner(System.`in`)
    val number = scan.nextInt()
    require(number > 0) { "The number must be greater than 0" }
    return number
}

fun reverse(array: List<Int>, indexOfBegin: Int, indexOfEnd: Int): List<Int> {
    val begin = if (indexOfBegin < 0) 0 else indexOfBegin
    val end = if (indexOfEnd > array.size) array.size else indexOfEnd

    val beginSegment = array.subList(0, begin)
    val reversedSegment = array.subList(begin, end).reversed()
    val endSegment = array.subList(end, array.size)

    return beginSegment + reversedSegment + endSegment
}

fun swapStartAndEnd(array: List<Int>, beginLength: Int, endLength: Int): List<Int> {
    require(beginLength + endLength == array.size) { "The array should be long: ${beginLength + endLength}" }
    var answer = array
    answer = reverse(answer, 0, beginLength)
    answer = reverse(answer, beginLength, beginLength + endLength)
    answer = reverse(answer, 0, beginLength + endLength)
    return answer
}

fun main() {
    val beginSegmentLength: Int
    val endSegmentLength: Int

    try {
        println("Enter the value of N:")
        beginSegmentLength = getNumber()
        println("Enter the value of M:")
        endSegmentLength = getNumber()
    } catch (exception: RuntimeException) {
        println("You must enter a number")
        exitProcess(0)
    }

    var array: List<Int>
    try {
        println("Enter the array:")
        array = readLine()!!.split(' ').map { it.toInt() }
    } catch (exception: RuntimeException) {
        println("You need to enter an array of integers")
        exitProcess(0)
    }

    try {
        array = swapStartAndEnd(array, beginSegmentLength, endSegmentLength)
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        exitProcess(0)
    }

    println("Result:")
    print(array.subList(0, beginSegmentLength + endSegmentLength).joinToString(" "))
}