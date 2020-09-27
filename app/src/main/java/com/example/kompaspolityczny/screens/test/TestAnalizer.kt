package com.example.kompaspolityczny.screens.test

import kotlin.math.round

const val gospodarkaMAX = 56
const val spoleczenstwoMAX = 36
const val politykaWMAX = 42
const val politykaZMAX = 32

class TestAnalizer {

    private var gospodarkaResultList = mutableListOf<Int>()
    private var spoleczenstwoResultList = mutableListOf<Int>()
    private var politykaWResultList = mutableListOf<Int>()
    private var politykaZResultList = mutableListOf<Int>()

    private var gospodarkaResult: Float = 0F
    private var spoleczenstwoResult: Float = 0F
    private var politykaWResult: Float = 0F
    private var politykaZResult: Float = 0F

    private var questionAnswered = mutableListOf<Question>()

    fun aplayAnswerToList(question: Question, resultValue: Int) {
        println("Dodaje pytanie: ${question.questionText}")
        if (question.questionSide) {
            when (question.questionCategory) {
                "Gospodarka" -> gospodarkaResultList.add(resultValue)
                "Spoleczenstwo" -> spoleczenstwoResultList.add(resultValue)
                "PolitykaW" -> politykaWResultList.add(resultValue)
                "PolitykaZ" -> politykaZResultList.add(resultValue)
            }
        } else {
            when (question.questionCategory) {
                "Gospodarka" -> gospodarkaResultList.add((resultValue) - 2 * resultValue)
                "Spoleczenstwo" -> spoleczenstwoResultList.add((resultValue) - 2 * resultValue)
                "PolitykaW" -> politykaWResultList.add((resultValue) - 2 * resultValue)
                "PolitykaZ" -> politykaZResultList.add((resultValue) - 2 * resultValue)
            }
        }
        questionAnswered.add(question)
    }

    fun undoAnswerToList() {
        val question = questionAnswered.removeLast()
        println("Usuwam pytanie: ${question.questionText}")
        when (question.questionCategory) {
            "Gospodarka" -> gospodarkaResultList.removeLast()
            "Spoleczenstwo" -> spoleczenstwoResultList.removeLast()
            "PolitykaW" -> politykaWResultList.removeLast()
            "PolitykaZ" -> politykaZResultList.removeLast()
        }
    }

    fun makeResultList(): FloatArray {
        for (x in 0 until gospodarkaResultList.size) {
            gospodarkaResult += gospodarkaResultList[x]
        }
        for (x in 0 until spoleczenstwoResultList.size) {
            spoleczenstwoResult += spoleczenstwoResultList[x]
        }
        for (x in 0 until politykaWResultList.size) {
            politykaWResult += politykaWResultList[x]
        }
        for (x in 0 until politykaZResultList.size) {
            politykaZResult += politykaZResultList[x]
        }

        val gospodarkaResultRight = (gospodarkaResult / gospodarkaMAX) * 50 + 50
        val gospodarkaResultLeft = 100 - gospodarkaResultRight

        val spoleczenstwoResultRight = (spoleczenstwoResult / spoleczenstwoMAX) * 50 + 50
        val spoleczenstwoResultLeft = 100 - spoleczenstwoResultRight

        val politykaWResultRight = (politykaWResult / politykaWMAX) * 50 + 50
        val politykaWResultLeft = 100 - politykaWResultRight

        val politykaZResultRight = (politykaZResult / politykaZMAX) * 50 + 50
        val politykaZResultLeft = 100 - politykaZResultRight

        val osX = (gospodarkaResultRight - 50) / 5
        val osY1 = (politykaZResultLeft - 50) / 25
        val osY2 = (spoleczenstwoResultLeft - 50) / 25
        val osY3 = ((politykaWResultLeft - 50) / 25) * 3

        val osY = (osY1 + osY2 + osY3)

        println("osX: $osX")
        println("osY1: $osY1")
        println("osY2: $osY2")
        println("osY3: $osY3")
        println("osY: $osY")

        val categoryResultList = FloatArray(10)
        categoryResultList[0] = (round(gospodarkaResultLeft))
        categoryResultList[1] = (round(gospodarkaResultRight))
        categoryResultList[2] = (round(spoleczenstwoResultLeft))
        categoryResultList[3] = (round(spoleczenstwoResultRight))
        categoryResultList[4] = (round(politykaWResultLeft))
        categoryResultList[5] = (round(politykaWResultRight))
        categoryResultList[6] = (round(politykaZResultLeft))
        categoryResultList[7] = (round(politykaZResultRight))
        categoryResultList[8] = osX
        categoryResultList[9] = osY


        return categoryResultList
    }
}