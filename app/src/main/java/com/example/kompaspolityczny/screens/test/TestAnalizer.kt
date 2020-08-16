package com.example.kompaspolityczny.screens.test

class TestAnalizer {
    var resultList = MutableList<Int>(12) {index -> 0
    }

    fun updateResultList(resultValue: Int, index: Int){
        resultList[index] = resultValue
        println(resultList)
    }
}