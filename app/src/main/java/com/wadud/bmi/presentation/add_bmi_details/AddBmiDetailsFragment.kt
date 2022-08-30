package com.wadud.bmi.presentation.add_bmi_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.wadud.bmi.R
import com.wadud.bmi.databinding.FragmentAddBmiDetailsBinding
import com.wadud.bmi.domain.model.BmiResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddBmiDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAddBmiDetailsBinding
    private var mInterstitialAd: InterstitialAd? = null

    private val viewModel: AddBmiDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBmiDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPickers()
        binding.calculateButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            if (validateUserName(name)) showNameError() else {
                clearError()
                viewModel.userName = name
                viewModel.calculateUserBmi()
                initializeAds()
            }
        }
    }

    private fun validateUserName(name: String): Boolean {
        return name.isBlank()
    }

    private fun setUpPickers() {
        setUpGenderPicker()
        setUpWeightPicker()
        setUpHeightPicker()
    }


    private fun setUpGenderPicker() {
        val genderArray = resources.getStringArray(R.array.genders)
        binding.genderPicker.minValue = 0
        binding.genderPicker.maxValue = 1
        binding.genderPicker.displayedValues = genderArray
        binding.genderPicker.setOnValueChangedListener { _, _, newValue ->
            viewModel.setUserGender(genderArray[newValue])
        }
    }

    private fun setUpWeightPicker() {
        binding.weightPicker.minValue = 10
        binding.weightPicker.maxValue = 1000
        binding.weightPicker.setOnValueChangedListener { _, _, newValue ->
            viewModel.setUserWeight(newValue)
        }
    }

    private fun setUpHeightPicker() {
        binding.heightPicker.minValue = 10
        binding.heightPicker.maxValue = 1000
        binding.heightPicker.setOnValueChangedListener { _, _, newValue ->
            viewModel.setUserHeight(newValue)
        }
    }

    private fun showNameError() {
        binding.inputNameTextLayout.error = getString(R.string.enter_name)
    }

    private fun clearError() {
        binding.inputNameTextLayout.error = null
    }

    private fun initializeAds() {
        val adRequest: AdRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(), "ca-app-pub-9812157246251898/3826544578", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    mInterstitialAd = null
                }
            })

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                mInterstitialAd = null
                findNavController().navigate(AddBmiDetailsFragmentDirections.actionAddBmiDetailsFragmentToBmiDetailsFragment(
                    BmiResult(viewModel.userName,viewModel.userBmi)
                ))
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
            }
        }
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        } else {
            findNavController().navigate(AddBmiDetailsFragmentDirections.actionAddBmiDetailsFragmentToBmiDetailsFragment(
                BmiResult(viewModel.userName,viewModel.userBmi)
            ))
        }
    }


}