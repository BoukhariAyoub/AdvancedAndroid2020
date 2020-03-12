package com.boukharist.screens.info

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.boukharist.mvarchi.Color
import com.boukharist.mvarchi.Color.BLUE
import com.boukharist.mvarchi.Color.RED
import com.boukharist.presentation.R


@BindingAdapter("ageColor")
fun TextView.ageColor(color: Color?) {
    @ColorRes
    val col = when (color) {
        RED -> R.color.color_error
        BLUE -> R.color.color_secondary
        else -> R.color.color_on_primary
    }

    this.setTextColor(ContextCompat.getColor(context,col))
}