package com.wadud.bmi.util

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan


object TextUtil {

    fun spannableStringBuilder(
        text: String,
        afterChar: Char,
        reduceBy: Float
    ): SpannableStringBuilder {
        val smallSizeText = RelativeSizeSpan(reduceBy)
        val ssBuilder = SpannableStringBuilder(text)
        ssBuilder.setSpan(
            smallSizeText,
            text.indexOf(afterChar),
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return ssBuilder
    }
}