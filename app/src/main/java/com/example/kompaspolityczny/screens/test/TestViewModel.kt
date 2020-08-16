package com.example.kompaspolityczny.screens.test

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {

    // The current question
    private val _question = MutableLiveData<String>()
    val question: LiveData<String>
        get() = _question

    // The current question number
    private val _questionNumber = MutableLiveData<Int>()
    val questionNumber: LiveData<Int>
        get() = _questionNumber

    // Event which triggers the end of the test
    private val _eventTestFinish = MutableLiveData<Boolean>()
    val eventTestFinish: LiveData<Boolean>
        get() = _eventTestFinish

    private var qN: Int = -1

    private lateinit var questionList: MutableList<String>

    var testAnalizer = TestAnalizer()

    init {
        resetQuestionList()
        nextQuestion(3)
        _questionNumber.value = 1
    }

    private fun resetQuestionList() {
        questionList = mutableListOf(
            "Katerogia nr 1",
            "Katerogia nr 1 v2",
            "Katerogia nr 1 v3 ",
            "Katerogia nr 1 v4",

            "Kategoria nr 2",
            "Kategoria nr 2 v2",
            "Kategoria nr 2 v3",
            "Kategoria nr 2 v4",

            "Kategoria nr 3",
            "Kategoria nr 3 v2",
            "Kategoria nr 3 v3",
            "Kategoria nr 3 v4"
        )
    }

    private fun nextQuestion(qA: Int) {
        when (qA) {
            2 -> testAnalizer.updateResultList(2,qN)
            1 -> testAnalizer.updateResultList(1,qN)
            0 -> testAnalizer.updateResultList(0,qN)
            -1 -> testAnalizer.updateResultList(-1,qN)
            -2 -> testAnalizer.updateResultList(-2,qN)
        }
        if (qN >= 11) {
            _eventTestFinish.value = true
        }
        else{
            _questionNumber.value = _questionNumber.value?.plus(1)
            qN++
            _question.value = questionList.get(qN)
        }
    }

    private fun previousQuestion() {
        if (qN >= 1){
            _questionNumber.value = _questionNumber.value?.minus(1)
            qN--
            _question.value = questionList.get(qN)
        }
    }

    fun onButtonClickNext_agree2() {
        nextQuestion(2)
    }
    fun onButtonClickNext_agree1() {
        nextQuestion(1)
    }
    fun onButtonClickNext_neutral() {
        nextQuestion(0)
    }
    fun onButtonClickNext_disagree2() {
        nextQuestion(-2)
    }
    fun onButtonClickNext_disagree1() {
        nextQuestion(-1)
    }

    fun onButtonClickPrevius() {
        previousQuestion()
    }

    fun onTestFinishComplete() {
        _eventTestFinish.value = false
    }

}