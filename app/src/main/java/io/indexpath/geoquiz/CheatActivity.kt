package io.indexpath.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    private var mAnswerIsTrue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        val mShowAnswer : Button = findViewById(R.id.show_answer_button)
        val mAnswerTextView : TextView = findViewById(R.id.answer_text_view)

        mShowAnswer.setOnClickListener {
            if (mAnswerIsTrue) {
                mAnswerTextView.setText(R.string.true_button)
            } else {
                mAnswerTextView.setText(R.string.false_button)
            }
            setAnswerShownResult(true)
        }
    }



    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }




    companion object {
        val EXTRA_ANSWER_IS_TRUE = "io.indexpath.geoquiz.answer_is_true"
        val EXTRA_ANSWER_SHOWN = "io.indexpath.geoquiz.answer_shown"

        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val i = Intent(context, CheatActivity::class.java)
            i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return i
        }

        fun wasAnswerShown(result: Intent): Boolean {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }

    }
}
