package com.wadud.bmi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BmiResult(
    val userName : String,
    val bmi : Float
): Parcelable