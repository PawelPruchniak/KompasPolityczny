package com.example.kompaspolityczny.screens.test

import androidx.lifecycle.MutableLiveData

const val NUMBER_OF_Q_IN_CATEGORY1 = 4
const val NUMBER_OF_Q_IN_CATEGORY2 = 8
const val NUMBER_OF_Q_IN_CATEGORY3 = 12
const val NUMBER_OF_Q_IN_CATEGORY4 = 16

class TestAnalizer {
    val resultList = mutableListOf<Int>()

    var category1Result: Float = 0F
    var category2Result: Float = 0F
    var category3Result: Float = 0F
    var category4Result: Float = 0F

    fun aplayAnswerToList(resultValue: Int){
        resultList.add(resultValue)
        println(resultList)
    }

    fun undoAnswerToList(){
        resultList.removeLast()
        println(resultList)
    }

    fun makeResultList(): FloatArray {
        for (x in 0 .. resultList.size){
            if (x < NUMBER_OF_Q_IN_CATEGORY1 ){
                category1Result += resultList[x]
            }
            else if (x < NUMBER_OF_Q_IN_CATEGORY2 ){
                category2Result += resultList[x]
            }
            else if (x < NUMBER_OF_Q_IN_CATEGORY3 ){
                category3Result += resultList[x]
            }
            else if (x < NUMBER_OF_Q_IN_CATEGORY4 ){
                category4Result += resultList[x]
            }
        }
        var category1ResultRight = (category1Result / 8) * 50 + 50
        var category1ResultLeft = 100 - category1ResultRight

        var category2ResultRight = (category2Result / 8) * 50 + 50
        var category2ResultLeft = 100 - category2ResultRight

        var category3ResultRight = (category3Result / 8) * 50 + 50
        var category3ResultLeft = 100 - category3ResultRight

        var category4ResultRight = (category4Result / 8) * 50 + 50
        var category4ResultLeft = 100 - category4ResultRight

        println("Category 1 result Left: $category1ResultLeft ||| result Right: $category1ResultRight")
        println("Category 2 result Left: $category2ResultLeft ||| result Right: $category2ResultRight")
        println("Category 3 result Left: $category3ResultLeft ||| result Right: $category3ResultRight")
        println("Category 4 result Left: $category4ResultLeft ||| result Right: $category4ResultRight")

        val categoryResultList = FloatArray(8)
        categoryResultList[0] = (category1ResultLeft)
        categoryResultList[1] = (category1ResultRight)
        categoryResultList[2] =(category2ResultLeft)
        categoryResultList[3] =(category2ResultRight)
        categoryResultList[4] =(category3ResultLeft)
        categoryResultList[5] =(category3ResultRight)
        categoryResultList[6] =(category4ResultLeft)
        categoryResultList[7] =(category4ResultRight)

        println(categoryResultList[0])

        return categoryResultList
    }
}