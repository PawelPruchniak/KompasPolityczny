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
    private lateinit var currentQuestion: Question
    var testAnalizer = TestAnalizer()


    lateinit var categoryResultList: FloatArray

    init {
        resetQuestionList()
        nextQuestion(3)
        _questionNumber.value = 1
    }

    private fun resetQuestionList() {
        questionList = mutableListOf(
            Question("Treść Pytanie", "Gospodarka", true),
            Question("Treść Pytanie2", "Społeczeństwo", false),
            Question("Treść Pytanie3", "Społeczeństwo", true),
            Question("Treść Pytanie4", "Gospodarka", true),
            Question("Treść Pytanie5", "Gospodarka", false),
            Question("Treść Pytanie6", "PolitykaW", true),
            Question("Treść Pytanie7", "PolitykaW", true),
            Question("Treść Pytanie8", "PolitykaZ", true),
            Question("Treść Pytanie9", "PolitykaW", false),
            Question("Treść Pytanie10", "Gospodarka", false),
            Question("Treść Pytanie11", "PolitykaZ", false),
            Question("Treść Pytanie12", "PolitykaZ", true),
            Question("Treść Pytanie13", "PolitykaZ", false),
            Question("Treść Pytanie14", "Społeczeństwo", false),
            Question("Treść Pytanie15", "Społeczeństwo", true),
            Question("Treść Pytanie16", "PolitykaW", false),
        )
    }


    private fun nextQuestion(questionAnswerValue: Int) {
        if(questionAnswerValue != 3){
            testAnalizer.aplayAnswerToList(currentQuestion, questionAnswerValue)
        }
        if (questionIndex >= NUMBERR_OF_QUESTIONS - 1) {
            // event that ends Test
            categoryResultList = testAnalizer.makeResultList()
            _eventTestFinish.value = true
        }
        else{
            _questionNumber.value = _questionNumber.value?.plus(1)
            questionIndex++
            currentQuestion = questionList.get(questionIndex)
            _question.value = questionList.get(questionIndex).questionText
        }
    }

    private fun previousQuestion() {
        if (questionIndex != 0){
            // Removing last value from resultList
            testAnalizer.undoAnswerToList()

            _questionNumber.value = _questionNumber.value?.minus(1)
            questionIndex--
            currentQuestion = questionList.get(questionIndex)
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