package com.example.kompaspolityczny.screens.test

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val NUMBERR_OF_QUESTIONS = 83

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
            Question("Ucisk ze strony korporacji jest bardziej niepokojący, niż ten ze strony rządu.", "Gospodarka", false),
            Question("Interwencja państwa w celu ochrony konsumentów jest potrzebna.", "Gospodarka", false),
            Question("Im bardziej wolny jest rynek, tym bardziej wolni są ludzie.", "Gospodarka", true),
            Question("Utrzymanie zwrównoważonego budżetu jest ważne.", "Gospodarka", true),
            Question("Badania finansowane publiczne, są mniej skuteczne od tych finansowanych prywatnie.", "Gospodarka", true),
            Question("Od każdego według jego zdolności, każdemu według jego potrzeb, jest dobrą myślą.", "Gospodarka", false),
            Question("Lepiej byłoby, gdyby wszystkie programy socjalne zostały zniesione na rzecz prywatnej działalności charytatywnej.", "Gospodarka", true),
            Question("Podatki progresywne, to dobry pomysł. ", "Gospodarka", false),
            Question("Dziedziczenie jest uczciwą formą, wzbogacenia się.", "Gospodarka", true),
            Question("Własność prywatna powinna zostać zniesiona.", "Gospodarka", false),
            Question("Obiekty takie jak, elektrownie czy drogi, powinny być pozostać w rękach publicznych.", "Gospodarka", false),
            Question("Nadmierna ingerencja rządu jest zagrożeniem dla gospodarki.", "Gospodarka", true),
            Question("Przymus ubezpieczania się powinien zostać zniesiony.", "Gospodarka", true),
            Question("Powinniśmy zdecydowanie zwiększyć udział prywatnego sektora w służbie zdrowia.", "Gospodarka", true),
            Question("Studia powinny w dalszym ciągu być finansowane z budżetu państwa.", "Gospodarka", false),
            Question("Kapitaliści w dużym stopniu wyzyskują swoich pracowników.", "Gospodarka", false),
            Question("Niektóre sektory gospodarki powinny pozostać upaństwowione.", "Gospodarka", false),
            Question("Rząd powinien zwalczać róznice w płac kobiet i mężczyzn.", "Gospodarka", false),
            Question("Podatki powinny być minimalne.", "Gospodarka", true),
            Question("Licencje, zezwolenia i związki zawodowe są niepotrzebnymi mechanizmami blokującymi rozwój działaności gospodarczych.", "Gospodarka", true),
            Question("Rząd powinien za pomocą prawa powinien regulować istnienie monopoli.", "Gospodarka", false),
            Question("Transfery socjalne powinny zostać znacznie ograniczone.", "Gospodarka", true),
            Question("Własność prywatna jest podstawą gospodarki.", "Gospodarka", true),
            Question("Państwo powinno zapewniać pracę i mieszkanie dla najbiedniejszych.", "Gospodarka", false),
            Question("Podwyższenie zasiłków dla najbiedniejszych jest lepszym rozwiązaniem niż zwiększenie kwoty wolnej od podatku.", "Gospodarka", false),
            Question("Kontrola inflacji jest ważniejsza od kontroli bezrobocia.", "Gospodarka", true),
            Question("Państwo powinno kontrolować działania korporacji tak aby nie wpływały one negatywnie na środowisko naturalne.", "Gospodarka", false),
            Question("Protekcjonizm jest czasem konieczny w handlu.", "Gospodarka", false),


            Question("Popieram działania organizacji międzynarodowych takich jak ONZ, NATO etc.", "PolitykaZ", true),
            Question("W celu ochrony naszego kraju, czasem nieunikniona jest interwencja zbrojna.", "PolitykaZ", false),
            Question("Stworzenie wspólnej Armii Europejskiej to dobry pomysł.", "PolitykaZ", true),
            Question("Unia Europejska jest zagrożeniem dla naszej suwerenności.", "PolitykaZ", false),
            Question("Nikt nie wybiera sobie kraju urodzenia, więc to głupie być z niego dumnym.", "PolitykaZ", true),
            Question("Nasz naród jest lepszy od wielu innych narodów.", "PolitykaZ", false),
            Question("Globalizacja jest procesem do którego powinniśmy dążyć za wszelką cenę.", "PolitykaZ", true),
            Question("Zawsze popierałbym mój kraj, bez względu na to czy ma rację czy nie.", "PolitykaZ", false),
            Question("Powinniśmy dyplomatycznie rozwiązywać konflikty międzynarodowe, niżeli ograniczać się do używania siły.", "PolitykaZ", true),
            Question("Wojny nie muszą być usprawiedliwiane przed innym krajami.", "PolitykaZ", false),
            Question("Wydatki na obronność to starata pieniędzy.", "PolitykaZ", true),
            Question("Jeżeli jakieś państwo ucierpi w wyniku katastrofy, naszym moralnym obowiązkiem jest wsparcie ich, np finansowo.", "PolitykaZ", true),
            Question("Czuję się bardziej Europejczykiem, niżeli Polakiem.", "PolitykaZ", true),
            Question("Nie ma lepszych, czy gorszych kultur.", "PolitykaZ", true),
            Question("Wartości mojego narodu powinny być rozprzestrzenione na jak największą skalę.", "PolitykaZ", false),
            Question("Strefa Schengen jest bardzo dobrą inicjatywą", "PolitykaZ", true),


            Question("Moje wartości religijne powinny być rozprzestrzenione na jak największą skalę.", "Społeczeństwo", false),
            Question("Lepszy świat powstanie w wyniku rozwoju nauki, automatyzacji i technologii.", "Społeczeństwo", true),
            Question("Dzieci powinny być nauczane religijnych lub tradycyjnych wartości.", "Społeczeństwo", false),
            Question("Tradycja sama w sobie, nie ma żadnej wartości.", "Społeczeństwo", true),
            Question("Religia powinna stanowić nierozerwalny element polityki rządu.", "Społeczeństwo", false),
            Question("Związki wyznaniowe powinny być opodatkowane tak jak wszystkie inne instytucje.", "Społeczeństwo", true),
            Question("Całkowity rozdział kościołu od państwa w dzisiejszych czasach jest niezbędny.", "Społeczeństwo", true),
            Question("To ważne abyśmy pamiętali o tradycjach i historii naszych przodków.", "Społeczeństwo", false),
            Question("Małżeństwa jednopłciowe powinny być legalne.", "Społeczeństwo", true),
            Question("Małżeństwa jednopłciowe powinny mieć możliwość adopcji dzieci.", "Społeczeństwo", true),
            Question("W obecnych czasach obserwujemy niepokojące połączenie informacji z rozrywką.", "Społeczeństwo", false),
            Question("Seks pozamałżeński jest niemoralny.", "Społeczeństwo", false),
            Question("Istnieją tylko dwie płcie, mężczyzna i kobieta.", "Społeczeństwo", false),
            Question("Jeżeli mamy przyjąć imigrantów, ważnym jest, aby zasymilowali się z naszą kulturą.", "Społeczeństwo", false),
            Question("Aborcja w większości przypadków powinna być zakazana.", "Społeczeństwo", false),
            Question("Ślepe podążanie za postępem jest ryzykowne.", "Społeczeństwo", false),
            Question("Multikulturalizm ma dobry wpływ na społeczeństwo.", "Społeczeństwo", true),
            Question("Przywrócenie kary śmierci, to dobry pomysł.", "Społeczeństwo", false),

            Question("Przestrzeganie porządku i prawa jest ważne.", "PolitykaW", false),
            Question("Nie ma przestępstwa bez ofiary.", "PolitykaW", true),
            Question("Miękkie narkotyki, takie jak marichuana powinny być legalne.", "PolitykaW", true),
            Question("W celu zapewnienia nam bezpieczeństwa przed aktami terroryzmu, rząd powinień móc w pewnien sposób inwigilować obywateli.", "PolitykaW", false),
            Question("Samo istnienie państwa jest już ograniczeniem naszej wolności.", "PolitykaW", true),
            Question("Im silniejszy przywódca, tym lepiej.", "PolitykaW", false),
            Question("Prawo do posiadania broni powinno zostać zliberalizowane.", "PolitykaW", true),
            Question("Wszystko w państwie, nic poza państwem, nic przeciw państwu.", "PolitykaW", false),
            Question("Pornografia dorosłych dla dorosłych powinna być w pełni legalna.", "PolitykaW", true),
            Question("Prostytucja powinna być nielegalna.", "PolitykaW", false),
            Question("Kodeks pracy powinien zostać znacznie ograniczony.", "PolitykaW", true),
            Question("Praktycznie wszystkie lekarstwa powinny być dostępne bez recepty.", "PolitykaW", true),
            Question("Rola państwa powinna zostać znacznie ograniczona.", "PolitykaW", true),
            Question("Wolność nie jest wartością samą w sobie.", "PolitykaW", false),
            Question("Państwo powinno tworzyć program nauczania którego powinny trzymać się wszystkie szkoły.", "PolitykaW", false),
            Question("Służba wojskowa powinna zostać całkowicie zniesiona.", "PolitykaW", true),
            Question("Rząd powinien walczyć z handlem narkotykami.", "PolitykaW", false),
            Question("Patenty nie powinny istnieć.", "PolitykaW", true),
            Question("Eutanazja powinna być dostępna dla osób śmiertelnie chorych.", "PolitykaW", true),
            Question("Wiek zgody powinien zostać obniżony.", "PolitykaW", true),
            Question("Szkoły nie powinny wymagać obowiązkowej obecności na lekcjach.", "PolitykaW", true),
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