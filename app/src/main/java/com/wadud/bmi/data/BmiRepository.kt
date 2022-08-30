package com.wadud.bmi.data

import com.wadud.bmi.domain.model.Gender

interface BmiRepository {

    fun calculateBmi(weight : Int, height : Int, gender: Gender) : Float
}