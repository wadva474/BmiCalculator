package com.wadud.bmi.presentation.add_bmi_details

import androidx.lifecycle.ViewModel
import com.wadud.bmi.data.BmiRepository
import com.wadud.bmi.domain.model.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddBmiDetailsViewModel @Inject constructor(
    private val bmiRepository: BmiRepository
) : ViewModel() {

    private var userWeight: Int = 0
    private var userHeight: Int = 0
    private var userGender: Gender = Gender.MALE

     var userName: String = ""

    var userBmi: Float = 0.0f


    fun setUserWeight(weight: Int) {
        userWeight = weight
    }

    fun setUserHeight(height: Int) {
        userHeight = height
    }

    fun setUserGender(gender: String) {
        userGender = if (gender.lowercase(Locale.ROOT) == "male") Gender.MALE else Gender.FEMALE
    }


    fun calculateUserBmi() {
        userBmi = bmiRepository.calculateBmi(userWeight, userHeight, userGender)
    }


}