package com.example.jetpacknavigation.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.et1.doOnTextChanged { _, _, _, _ ->
            if(binding.et1.text.toString().length == 1){
                binding.et2.requestFocus()
            }
        }
        binding.et2.doOnTextChanged { _, _, _, _ ->
            if(binding.et2.text.toString().length == 1){
                binding.et3.requestFocus()
            }
        }
        binding.et2.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                // Handle backspace key press
                binding.et2.text = null
                binding.et1.requestFocus()
                true // Return true to consume the event
            } else {
                false // Return false to allow normal key handling
            }
        }
        binding.et3.doOnTextChanged { _, _, _, _ ->
            if(binding.et3.text.toString().length == 1){
                binding.et4.requestFocus()
            }
        }
        binding.et3.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                // Handle backspace key press
                binding.et3.text = null
                binding.et2.requestFocus()
                true // Return true to consume the event
            } else {
                false // Return false to allow normal key handling
            }
        }
        binding.et4.doOnTextChanged { _, _, _, _ ->
            if(binding.et4.text.toString().length == 1){
                binding.et4.clearFocus()
                binding.chkOtp.performClick()
            }
        }
        binding.et4.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                // Handle backspace key press
                binding.et4.text = null
                binding.et3.requestFocus()
                true // Return true to consume the event
            } else {
                false // Return false to allow normal key handling
            }
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