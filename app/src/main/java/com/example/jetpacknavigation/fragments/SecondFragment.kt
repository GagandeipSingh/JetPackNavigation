package com.example.jetpacknavigation.fragments

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigation.R
import com.example.jetpacknavigation.databinding.FragmentSecondBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private var otp : String? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            otp = it.getString("otp")
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.et1.doOnTextChanged { text, _, _, _ ->
            if(text.toString().length == 4){
                binding.et1.setText(text?.substring(0,1))
                binding.et2.setText(text?.substring(1,2))
                binding.et3.setText(text?.substring(2,3))
                binding.et4.setText(text?.substring(3,4))
                binding.et1.clearFocus()
                binding.chkOtp.performClick()
            }
        }

        val digitMap = mapOf(
            KeyEvent.KEYCODE_0 to '0',
            KeyEvent.KEYCODE_1 to '1',
            KeyEvent.KEYCODE_2 to '2',
            KeyEvent.KEYCODE_3 to '3',
            KeyEvent.KEYCODE_4 to '4',
            KeyEvent.KEYCODE_5 to '5',
            KeyEvent.KEYCODE_6 to '6',
            KeyEvent.KEYCODE_7 to '7',
            KeyEvent.KEYCODE_8 to '8',
            KeyEvent.KEYCODE_9 to '9'
        )

        // Assuming you have two EditTexts named editText1 and editText2
        binding.et1.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if(binding.et1.text.isEmpty()) {
                    binding.et1.setText(digitMap[keyCode].toString())
                    binding.et2.requestFocus()
                    binding.et2.setSelection(binding.et2.text.length)
                    return@setOnKeyListener true // Consumes the key event
                }
                else if(binding.et1.text.length == 1){
                    binding.et2.requestFocus()
                    binding.et2.setText(digitMap[keyCode].toString())
                    binding.et2.setSelection(binding.et2.text.length)
                    return@setOnKeyListener true
                }
            }
            false // Let the key event propagate
        }
        binding.et2.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if(binding.et2.text.isEmpty()) {
                    binding.et2.setText(digitMap[keyCode].toString())
                    binding.et3.requestFocus()
                    binding.et3.setSelection(binding.et3.text.length)
                    return@setOnKeyListener true // Consumes the key event
                }
                else if(binding.et2.text.length == 1){
                    binding.et3.requestFocus()
                    binding.et3.setText(digitMap[keyCode].toString())
                    binding.et3.setSelection(binding.et3.text.length)
                    return@setOnKeyListener true
                }
            }
            else if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                binding.et2.text = null
                binding.et1.requestFocus()
                binding.et1.setSelection(binding.et1.text.length)
            }
            false // Let the key event propagate
        }
        binding.et3.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if(binding.et3.text.isEmpty()) {
                    binding.et3.setText(digitMap[keyCode].toString())
                    binding.et4.requestFocus()
                    binding.et4.setSelection(binding.et4.text.length)
                    return@setOnKeyListener true // Consumes the key event
                }
                else if(binding.et3.text.length == 1){
                    binding.et4.requestFocus()
                    binding.et4.setText(digitMap[keyCode].toString())
                    binding.et4.setSelection(binding.et4.text.length)
                    return@setOnKeyListener true
                }
            }
            else if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                binding.et3.text = null
                binding.et2.requestFocus()
                binding.et2.setSelection(binding.et2.text.length)
            }
            false // Let the key event propagate
        }
        binding.et4.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if(binding.et4.text.isEmpty()) {
                    binding.et4.setText(digitMap[keyCode].toString())
                    binding.et4.clearFocus()
                    binding.chkOtp.performClick()
                    return@setOnKeyListener true // Consumes the key event
                }
                else if(binding.et4.text.length == 1){
                    binding.et4.clearFocus()
                    binding.chkOtp.performClick()
                    return@setOnKeyListener true
                }
            }
            else if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                binding.et4.text = null
                binding.et3.requestFocus()
                binding.et3.setSelection(binding.et3.text.length)
            }
            false // Let the key event propagate
        }
        binding.chkOtp.setOnClickListener {
            if(binding.et1.text.toString().isBlank()||binding.et2.text.toString().isBlank()||binding.et3.text.toString().isBlank()||binding.et4.text.toString().isBlank()){
                binding.tempOtp.error = " "
                binding.tempOtp.text = getString(R.string.enter_otp)
                binding.tempOtp.visibility = View.VISIBLE
            }
            else{
                val enteredOtp = binding.et1.text.toString() + binding.et2.text.toString() + binding.et3.text.toString() + binding.et4.text.toString()
                if(enteredOtp == otp){
                    //show correct gif
                    binding.animationView.visibility = View.VISIBLE
                    Log.e(otp,enteredOtp)
                    binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator) {
                        }
                        override fun onAnimationEnd(p0: Animator) {
                        }
                        override fun onAnimationCancel(p0: Animator) {
                        }
                        override fun onAnimationRepeat(p0: Animator) {
                            binding.animationView.visibility = View.GONE
                            binding.et1.text = null
                            binding.et2.text = null
                            binding.et3.text = null
                            binding.et4.text = null
                            binding.et4.clearFocus()
                            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment3)
                        }
                    })
                }
                else{
                    //show try again gif
                    Log.e(otp,enteredOtp)
                    binding.animationWView.visibility = View.VISIBLE
                    binding.animationWView.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator) {
                        }
                        override fun onAnimationEnd(p0: Animator) {
                        }
                        override fun onAnimationCancel(p0: Animator) {
                        }
                        override fun onAnimationRepeat(p0: Animator) {
                            binding.animationWView.visibility = View.GONE
                            binding.et1.text = null
                            binding.et2.text = null
                            binding.et3.text = null
                            binding.et4.text = null
                            binding.chkOtp.performClick()
                        }
                    })
                }
            }
        }
        binding.et1.doOnTextChanged { _,_,_,_ ->
            binding.tempOtp.error = null
            binding.tempOtp.visibility = View.GONE
        }
        binding.et2.doOnTextChanged { _,_,_,_ ->
            binding.tempOtp.error = null
            binding.tempOtp.visibility = View.GONE
        }
        binding.et3.doOnTextChanged { _,_,_,_ ->
            binding.tempOtp.error = null
            binding.tempOtp.visibility = View.GONE
        }
        binding.et4.doOnTextChanged { _,_,_,_ ->
            binding.tempOtp.error = null
            binding.tempOtp.visibility = View.GONE
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}