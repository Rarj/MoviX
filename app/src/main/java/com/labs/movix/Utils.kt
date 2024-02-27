package com.labs.movix

import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.annotation.CheckResult
import com.google.android.material.textfield.TextInputEditText
import com.labs.data.BuildConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlin.math.roundToInt

fun getRating(rating: Double = 0.0) = buildString {
    append(rating.times(10.0).roundToInt() / 10.0)
    append("/10")
}

fun getPosterUrl(path: String?) = buildString {
    append(BuildConfig.IMAGE_BASE_URL)
    append(path)
}.ifEmpty { "" }

fun TextView.setUnderline(text: String) {
    val spannableString = SpannableString(text)
    spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
    this.text = spannableString
}

@ExperimentalCoroutinesApi
fun TextInputEditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}