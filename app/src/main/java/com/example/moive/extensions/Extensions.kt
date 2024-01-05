package com.example.moive.extensions

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun String.appendAsterisk(): SpannableString? {
    val text = SpannableString("$this *")
    text.setSpan(
        ForegroundColorSpan(Color.RED),
        this.length + 1,
        text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return text
}