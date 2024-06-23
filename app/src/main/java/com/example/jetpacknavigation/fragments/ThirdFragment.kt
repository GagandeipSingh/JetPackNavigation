package com.example.jetpacknavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigation.R
import com.example.jetpacknavigation.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
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
        binding = FragmentThirdBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.update.setOnClickListener {
            if(binding.password.text.toString().trim().isEmpty()){
                binding.passwordLayout.error = "Enter Password.."
            }
            else if(binding.rPassword.text.toString().trim().isEmpty()){
                binding.rPasswordLayout.error = "Re-Enter the Password.."
            }
            else if(binding.password.text.toString().length<8){
                binding.passwordLayout.error = "Password should be of at-least 8 digits.. "
            }
            else if(binding.password.text.toString().trim() != binding.rPassword.text.toString().trim()){
                binding.passwordLayout.error = "Passwords Not Match.."
                binding.rPasswordLayout.error = "Passwords Not Match.."
            }
            else{
                findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
                binding.password.text = null
                binding.rPassword.text = null
                binding.password.clearFocus()
                binding.rPassword.clearFocus()
                Toast.makeText(requireContext(),"Password Updated..",Toast.LENGTH_SHORT).show()
            }
        }
        binding.password.doOnTextChanged { _,_,_,_ ->
            binding.passwordLayout.error = null
        }
        binding.rPassword.doOnTextChanged { _,_,_,_ ->
            binding.rPasswordLayout.error = null
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}