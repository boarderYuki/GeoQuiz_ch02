package io.indexpath.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private var mCurrentIndex : Int = 0
    private var mIsCheater: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle) called")
        setContentView(R.layout.activity_quiz)


        val mTrueButton : Button = findViewById(R.id.true_button)
        val mFalseButton : Button = findViewById(R.id.false_button)
        val mNextButton : Button = findViewById(R.id.next_button)
        val mCheatButton : Button = findViewById(R.id.cheat_button)

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
            Log.d(TAG, "onCreate(Bundle) called")
        }

        mTrueButton.setOnClickListener { view ->
            checkAnswer(true)

        }

        mFalseButton.setOnClickListener { view ->
            checkAnswer(false)
        }

        mNextButton.setOnClickListener { view ->
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            mIsCheater = false
            updateQuestion()
        }

        mCheatButton.setOnClickListener {
            // 인텐트
            val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue
            val i = CheatActivity.newIntent(this, answerIsTrue)
            startActivity(i)
            startActivityForResult(i, REQUEST_CODE_CHEAT)
        }
        updateQuestion()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return
            }
            mIsCheater = CheatActivity.wasAnswerShown(data)
        }
    }


    private val mQuestionBank = arrayOf(
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )



    private fun updateQuestion() {
        val mQuestionTextView : TextView = findViewById(R.id.question_text_view)
        var question : Int = mQuestionBank[mCurrentIndex].getTextResId()
        mQuestionTextView.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue

        var messageResId = 0
        if (mIsCheater) {
            messageResId = R.string.judgment_toast
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast
            } else {
                messageResId = R.string.incorrect_toast
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    companion object {
        private val TAG = "QuizActivity"
        private val KEY_INDEX = "index"
        private val REQUEST_CODE_CHEAT = 0
    }

}
