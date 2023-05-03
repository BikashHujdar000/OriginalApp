package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerOtpVerificationBinding


class OwnerOtpVerificationFragment : Fragment() {

lateinit var binding :FragmentOwnerOtpVerificationBinding;
    val otp = "123456";
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOwnerOtpVerificationBinding.inflate(layoutInflater,container,false);
        setOtp();
        binding.continueBtn.setOnClickListener {
            verifyOtp()

        }
        return binding.root;
    }
     private fun setOtp(){
        binding.otp1.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.toString().trim().isEmpty()){
                    binding.otp2.requestFocus();
                    }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.otp2.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.toString().trim().isEmpty()){
                    binding.otp3.requestFocus();
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.otp3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty())
                {
                    binding.otp4.requestFocus()
                }
            }
        })
        binding.otp4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty())
                {
                    binding.otp5.requestFocus()
                }
            }
        })
        binding.otp5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty())
                {
                    binding.otp6.requestFocus()
                }
            }
        })
    }
     private fun verifyOtp(){
       val otpValue:String = binding.otp1.text.toString()+binding.otp2.text.toString()+binding.otp3.text.toString()+
        binding.otp4.text.toString()+binding.otp5.text.toString()+binding.otp6.text.toString()

        if (otpValue.length !=6){
            Toast.makeText(requireContext(),"please enter 6 digit",Toast.LENGTH_SHORT).show();
        }
        if(otpValue.length == otp.length && otpValue ==otp){
            Navigation.findNavController(requireView()).navigate(R.id.action_ownerOtpVerificationFragment_to_ownerOtpConfirmationFragment)
        }
        else{

            Toast.makeText(requireContext(),"Please! Enter valid OTP",Toast.LENGTH_SHORT).show()
        }

    }


}