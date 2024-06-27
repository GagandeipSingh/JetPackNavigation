package com.example.jetpacknavigation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigation.MainActivity
import com.example.jetpacknavigation.R
import com.example.jetpacknavigation.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var mainAct : MainActivity
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAct = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainAct.supportActionBar?.title = "First Fragment"
        binding.gtOtp.setOnClickListener {
            if(binding.emailValue.text.trim().isEmpty()){
                binding.emailLayout.error = "Enter Email.."
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailValue.text.trim().toString()).matches()){
                binding.emailLayout.error = "Enter Correct Email.."
            }
            else{
                val randomNum = generateRandomFourDigitNumber().toString()
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL,arrayOf(binding.emailValue.text.toString()))
                    putExtra("subject", "OTP Request")
                    val bodyText = getString(R.string.bodyTxt, randomNum)
                    putExtra("body", bodyText)
                }
                startActivity(emailIntent)
                val bundle = Bundle()
                bundle.putString("otp",randomNum)
                bundle.putString("email",binding.emailValue.text.toString())
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
                binding.emailValue.text = null
            }
        }
        binding.emailValue.doOnTextChanged { _, _, _, _ ->
            binding.emailLayout.error = null
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun generateRandomFourDigitNumber(): Int {
        val random = java.util.Random()
        return random.nextInt(8889) + 1111
    }
}