package com.example.kompaspolityczny.screens.test

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

    init {
        resetQuestionList()
        nextQuestion()
        _questionNumber.value = 1
    }

    private fun resetQuestionList() {
        questionList = mutableListOf(
            "Im bardziej wolny rynek, tym bardziej wolni są ludzie",
            "Dla każdego według jego potrzeb, od każdego według jego zdolności, to w zasadzie dobry pomysł",
            "Państwo powinno dążyć do neutralności klimatycznej"
        )
    }

    private fun nextQuestion() {
        if (qN >= 2) {
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

    fun onButtonClickNext() {
        nextQuestion()
    }

    fun onButtonClickPrevius() {
        previousQuestion()
    }

    fun onTestFinishComplete() {
        _eventTestFinish.value = false
    }

}