package com.example.kompaspolityczny.screens.test

import androidx.lifecycle.MutableLiveData
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

const val gospodarkaMAX = 56
const val społeczeństwoMAX = 36
const val politykaWMAX = 42
const val politykaZMAX = 32

class TestAnalizer {

    var gospodarkaResultList = mutableListOf<Int>()
    var społeczeństwoResultList = mutableListOf<Int>()
    var politykaWResultList = mutableListOf<Int>()
    var politykaZResultList = mutableListOf<Int>()

    var gospodarkaResult: Float = 0F
    var społeczeństwoResult: Float = 0F
    var politykaWResult: Float = 0F
    var politykaZResult: Float = 0F

    var questionAnswered = mutableListOf<Question>()

    fun aplayAnswerToList(question: Question, resultValue: Int){
        println("Dodaje pytanie: ${question.questionText}")
        if(question.questionSide == true){
            when(question.questionCategory){
                "Gospodarka" -> gospodarkaResultList.add(resultValue)
                "Społeczeństwo" -> społeczeństwoResultList.add(resultValue)
                "PolitykaW" -> politykaWResultList.add(resultValue)
                "PolitykaZ" -> politykaZResultList.add(resultValue)
            }
        }
        else{
            when(question.questionCategory){
                "Gospodarka" -> gospodarkaResultList.add((resultValue) - 2 * resultValue)
                "Społeczeństwo" -> społeczeństwoResultList.add((resultValue) - 2 * resultValue)
                "PolitykaW" -> politykaWResultList.add((resultValue) - 2 * resultValue)
                "PolitykaZ" -> politykaZResultList.add((resultValue) - 2 * resultValue)
            }
        }
        questionAnswered.add(question)
    }

    fun undoAnswerToList(){
        var question = questionAnswered.removeLast()
        println("Usuwam pytanie: ${question.questionText}")
        when(question.questionCategory){
            "Gospodarka" -> gospodarkaResultList.removeLast()
            "Społeczeństwo" -> społeczeństwoResultList.removeLast()
            "PolitykaW" -> politykaWResultList.removeLast()
            "PolitykaZ" -> politykaZResultList.removeLast()
        }
    }

    fun makeResultList(): FloatArray {
        for (x in 0 .. gospodarkaResultList.size - 1){
            gospodarkaResult += gospodarkaResultList[x]
        }
        for (x in 0 .. społeczeństwoResultList.size - 1){
            społeczeństwoResult += społeczeństwoResultList[x]
        }
        for (x in 0 .. politykaWResultList.size - 1){
            politykaWResult += politykaWResultList[x]
        }
        for (x in 0 .. politykaZResultList.size - 1){
            politykaZResult += politykaZResultList[x]
        }

        var gospodarkaResultRight = (gospodarkaResult / gospodarkaMAX) * 50 + 50
        var gospodarkaResultLeft = 100 - gospodarkaResultRight

        var społeczeństwoResultRight = (społeczeństwoResult / społeczeństwoMAX) * 50 + 50
        var społeczeństwoResultLeft = 100 - społeczeństwoResultRight

        var politykaWResultRight = (politykaWResult / politykaWMAX) * 50 + 50
        var politykaWResultLeft = 100 - politykaWResultRight

        var politykaZResultRight = (politykaZResult / politykaZMAX) * 50 + 50
        var politykaZResultLeft = 100 - politykaZResultRight

        println("Category 1 result Left: $gospodarkaResultLeft ||| result Right: $gospodarkaResultRight")
        println("Category 2 result Left: $społeczeństwoResultLeft ||| result Right: $społeczeństwoResultRight")
        println("Category 3 result Left: $politykaWResultLeft ||| result Right: $politykaWResultRight")
        println("Category 4 result Left: $politykaZResultLeft ||| result Right: $politykaZResultRight")

        val categoryResultList = FloatArray(8)
        categoryResultList[0] = (round(gospodarkaResultLeft))
        categoryResultList[1] = (round(gospodarkaResultRight))
        categoryResultList[2] =(round(społeczeństwoResultLeft))
        categoryResultList[3] =(round(społeczeństwoResultRight))
        categoryResultList[4] =(round(politykaWResultLeft))
        categoryResultList[5] =(round(politykaWResultRight))
        categoryResultList[6] =(round(politykaZResultLeft))
        categoryResultList[7] =(round(politykaZResultRight))

        return categoryResultList
    }
}