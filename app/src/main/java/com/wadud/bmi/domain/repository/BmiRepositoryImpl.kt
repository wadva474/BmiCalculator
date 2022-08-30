package com.wadud.bmi.domain.repository

import com.wadud.bmi.data.BmiRepository
import com.wadud.bmi.domain.model.Gender
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BmiRepositoryImpl @Inject constructor() : BmiRepository {

    override fun calculateBmi(weight: Int, height: Int, gender: Gender): Float {
       return weight.toFloat()/(height*height)
    }
}