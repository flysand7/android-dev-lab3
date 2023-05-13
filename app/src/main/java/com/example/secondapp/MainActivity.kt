package com.example.secondapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import kotlin.math.roundToInt
import Question



class MainActivity : AppCompatActivity() {
    private lateinit var true_button: Button
    private lateinit var false_button: Button
    private lateinit var question_view: TextView
    private lateinit var prev_button: ImageButton
    private lateinit var next_button: ImageButton
    
    private lateinit var curQuestion: Question
    private var curQuestionIndex = 0
    private val questionArr = arrayOf(
        Question("1. Fujisan is the tallest mountain in japan", true),
        Question("2. Cats only live in egypt", false),
        Question("3. Colloseum is located in Rome, Italy", true)
    )
    
    fun updateCurrentQuestion() {
        question_view.setText(curQuestion.question)
        if (curQuestionIndex == 0) {
            prev_button.setEnabled(false)
        }
        else if (curQuestionIndex == questionArr.count()-1) {
            next_button.setEnabled(false)
        }
        else {
            prev_button.setEnabled(true)
            next_button.setEnabled(true)
        }        
        if (questionArr[curQuestionIndex].answered) {
            true_button.setEnabled(false)
            false_button.setEnabled(false)
        }
        else {
            true_button.setEnabled(true)
            false_button.setEnabled(true)
        }
    }
    
    fun prevQuestion() {
        curQuestionIndex -= 1
        curQuestionIndex += questionArr.count()
        curQuestionIndex %= questionArr.count()
        curQuestion = questionArr[curQuestionIndex]
    }
    
    fun nextQuestion() {
        curQuestionIndex += 1
        curQuestionIndex %= questionArr.count()
        curQuestion = questionArr[curQuestionIndex]
    }
    
    fun answer(correct: Boolean) {
        curQuestion.answered = true;
        curQuestion.correct = correct;
        if(allQuestionsAnswered()) {
            val ntotal: Int = questionArr.count()
            var ncorrect: Int = 0
            for(q in questionArr) {
                if(q.correct) {
                    ncorrect += 1;
                }
            }
            val percentage = (100.0 * ncorrect.toFloat() / ntotal.toFloat()).roundToInt().toString();
            val str = "You answered " + percentage + "% of questions correctly";
            val toast_value = Toast.makeText(this, str, Toast.LENGTH_SHORT)
            toast_value.setGravity(Gravity.BOTTOM, 0, 0)
            toast_value.show()
        } else {
            val toast_value: Toast;
            if(correct) {
                toast_value = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT)
            }
            else {
                toast_value = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT)
            }
            toast_value.setGravity(Gravity.BOTTOM, 0, 0)
            toast_value.show()
        }
    }
    
    fun allQuestionsAnswered(): Boolean {
        for(q in questionArr) {
            if(!q.answered) {
                return false;
            }
        }
        return true;
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        true_button = findViewById(R.id.true_button)
        false_button = findViewById(R.id.false_button)
        question_view = findViewById(R.id.textView)
        
        true_button.setOnClickListener {
            if(curQuestion.istrue) {
                answer(true)
            }
            else {
                answer(false)
            }
            questionArr[curQuestionIndex].answered = true;
            if(curQuestionIndex != questionArr.count()-1) {
                nextQuestion()
            }
            updateCurrentQuestion()
        }
        false_button.setOnClickListener {
            if(curQuestion.istrue) {
                answer(false)
            }
            else {
                answer(true)
            }
            questionArr[curQuestionIndex].answered = true;
            if(curQuestionIndex != questionArr.count()-1) {
                nextQuestion()
            }
            updateCurrentQuestion()
        }

        prev_button = findViewById(R.id.prev_button)
        next_button = findViewById(R.id.next_button)
        next_button.setOnClickListener {
            nextQuestion()
            updateCurrentQuestion()
        }
        prev_button.setOnClickListener {
            prevQuestion()
            updateCurrentQuestion()
        }
        question_view.setOnClickListener {
            nextQuestion()
            updateCurrentQuestion()
        }
        
        curQuestionIndex = 0
        curQuestion = questionArr[curQuestionIndex]
        updateCurrentQuestion()
    }
}