package io.indexpath.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        updateQuestion()

        val mTrueButton : Button = findViewById(R.id.true_button)
        val mFalseButton : Button = findViewById(R.id.false_button)
        val mNextButton : Button = findViewById(R.id.next_button)

        mTrueButton.setOnClickListener { view ->
            checkAnswer(true)

        }

        mFalseButton.setOnClickListener { view ->
            checkAnswer(false)
        }

        mNextButton.setOnClickListener { view ->
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }
    }


    private val mQuestionBank = arrayOf(
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    private var mCurrentIndex : Int = 0

    private fun updateQuestion() {
        val mQuestionTextView : TextView = findViewById(R.id.question_text_view)
        var question : Int = mQuestionBank[mCurrentIndex].getTextResId()
        mQuestionTextView.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue

        var messageResId = 0

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast
        } else {
            messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
    }

}
