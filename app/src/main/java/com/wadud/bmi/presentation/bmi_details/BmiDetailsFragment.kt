package com.wadud.bmi.presentation.bmi_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.wadud.bmi.R
import com.wadud.bmi.databinding.FragmentBmiDetailsBinding
import com.wadud.bmi.util.ImageUtil
import com.wadud.bmi.util.TextUtil
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class BmiDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBmiDetailsBinding
    private val args: BmiDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBmiDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bmiResultTextView.text =
            TextUtil.spannableStringBuilder(args.bmiResult.bmi.toString(), '.', 0.6f)
        binding.userName.text =
            if (args.bmiResult.bmi.toInt() in 18..25) getString(R.string.normal_bmi_result).format(
                args.bmiResult.userName
            ) else getString(R.string.not_normal_result_format).format(args.bmiResult.userName)


        binding.shareButton.setOnClickListener {
            val sdcard: File = Environment.getExternalStorageDirectory()
            val path = "$sdcard/bmi.jpg"
            ImageUtil.captureView(it,path,false,requireContext())
        }

        binding.rateButton.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps")
                    )
                )
            }
        }
    }

}