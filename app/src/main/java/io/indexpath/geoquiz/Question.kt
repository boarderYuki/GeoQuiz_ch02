package io.indexpath.geoquiz

class Question(private var mTextResId: Int, var isAnswerTrue: Boolean) {

    fun getTextResId(): Int {
        return mTextResId
    }

    fun setTextResId() {
        this.mTextResId = mTextResId
    }

    fun getIsAnswerTrue() : Boolean {
        return isAnswerTrue
    }

    fun setIsAnswerTrue() {
        this.isAnswerTrue = isAnswerTrue
    }

}