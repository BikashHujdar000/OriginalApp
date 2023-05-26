package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OwnerForgetPasswordFragment : Fragment() {

lateinit var binding: FragmentOwnerForgetPasswordBinding
 private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         // Inflate the layout for this fragment
        binding = FragmentOwnerForgetPasswordBinding.inflate(layoutInflater,container,false);
        binding.sendOtp.setOnClickListener {
            val email = binding.emailAddress.text.toString();

            //kumajaiswalanil85@gmail.com
            authViewModel.getOtp(otpRequest("hujdarbikash000@gmail.com"))


//            if (email.isNotEmpty()) {
////                if(isValidEmail(email)){
//             //   authViewModel.getOtp("hudarbikash000@gmail.com")
//                    authViewModel.otpResponseLiveData.observe(viewLifecycleOwner, Observer {
//                        when(it){
//                            is NetworkResult.Success ->{
//                                val response = it.data!!
//                                Log.d("Email","$response")
//                                findNavController().navigate(R.id.action_ownerForgetPasswordFragment_to_ownerOtpVerificationFragment)
//                            }
//                            is NetworkResult.Loading ->{}
//                            is NetworkResult.Error ->{}
//                        }
//                    })
//
//
//
//                }
//                //empty
//                else{
//                    Toast.makeText(requireContext(),"enter valid mail",Toast.LENGTH_SHORT).show()
//                }
                //validity of the email is performed;


            }
            binding.backToLogin.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_ownerForgetPasswordFragment_to_ownerLoginFragment);
            }





        return binding.root;
    }
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}