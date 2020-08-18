package com.example.kompaspolityczny.screens.test

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val NUMBERR_OF_QUESTIONS = 16
const val AGREE2 = 2
const val AGREE = 1
const val NEUTRAL = 0
const val DISAGREE = -1
const val DISAGREE2 = -2

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

    private var questionIndex: Int = -1
    private lateinit var questionList: MutableList<Question>
    var testAnalizer = TestAnalizer()

    lateinit var categoryResultList: FloatArray

    init {
        resetQuestionList()
        nextQuestion(3)
        _questionNumber.value = 1
    }

    private fun resetQuestionList() {
        questionList = mutableListOf(
            Question("Treść Pytanie", "Kategoria", true),
            Question("Treść Pytanie2", "Kategoria2", false),
            Question("Treść Pytanie3", "Kategoria2", false),
            Question("Treść Pytanie4", "Kategoria2", false),
            Question("Treść Pytanie5", "Kategoria2", false),
            Question("Treść Pytanie6", "Kategoria2", false),
            Question("Treść Pytanie7", "Kategoria2", false),
            Question("Treść Pytanie8", "Kategoria2", false),
            Question("Treść Pytanie9", "Kategoria2", false),
            Question("Treść Pytanie10", "Kategoria2", false),
            Question("Treść Pytanie11", "Kategoria2", false),
            Question("Treść Pytanie12", "Kategoria2", false),
            Question("Treść Pytanie13", "Kategoria2", false),
            Question("Treść Pytanie14", "Kategoria2", false),
            Question("Treść Pytanie15", "Kategoria2", false),
            Question("Treść Pytanie16", "Kategoria2", false),
        )
    }


    private fun nextQuestion(questionAnswerValue: Int) {
        when (questionAnswerValue) {
            // Aplaying value to resultList
            AGREE2 -> testAnalizer.aplayAnswerToList(AGREE2)
            AGREE -> testAnalizer.aplayAnswerToList(AGREE)
            NEUTRAL -> testAnalizer.aplayAnswerToList(NEUTRAL)
            DISAGREE -> testAnalizer.aplayAnswerToList(DISAGREE)
            DISAGREE2 -> testAnalizer.aplayAnswerToList(DISAGREE2)
        }
        if (questionIndex >= NUMBERR_OF_QUESTIONS - 1) {
            // event that ends Test
            categoryResultList = testAnalizer.makeResultList()
            _eventTestFinish.value = true
        }
        else{
            _questionNumber.value = _questionNumber.value?.plus(1)
            questionIndex++
            _question.value = questionList.get(questionIndex).questionText
        }
    }

    private fun previousQuestion() {
        if (questionIndex != 0){
            // Removing last value from resultList
            testAnalizer.undoAnswerToList()

            _questionNumber.value = _questionNumber.value?.minus(1)
            questionIndex--
            _question.value = questionList.get(questionIndex).questionText
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