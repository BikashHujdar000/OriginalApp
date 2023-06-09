package com.example.hotelreservationsystem.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelreservationsystem.Models.BookRequest
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.GetAllHotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentUserBookingBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar


@AndroidEntryPoint
class UserBookingFragment : Fragment() {

    lateinit var binding: FragmentUserBookingBinding
    var calculatedAmount :Int? = null
    var roomPrice :Int? = null
    var startDate:String ? = null
    var endDate :String?=null

    private val getAllHotelViewModel by viewModels<GetAllHotelViewModel>()


    private val arg by navArgs<UserBookingFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBookingBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        // lets work now


        roomPrice = arg.roomDataa.price


        // seting ups views
        binding.pick1.setOnClickListener {
            val c = Calendar.getInstance()
            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
            // on below line we are creating a
            // variable for date picker dialog.

            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.checkInDate.text =
                        ((monthOfYear + 1).toString() + "/" + dayOfMonth + "/" + year)
                    val text =
                        ((dayOfWeek.toString() + monthOfYear + 1.toString() + dayOfMonth.toString() + year.toString()))

                    Log.d(constants.TAG, " date is $text")

                   // "2023-05-24"


                     startDate  = ((year).toString()+"-"+(monthOfYear+1).toString()+"-"+dayOfMonth)
                    Log.d(TAG,"okay So Start Date is ${startDate.toString()}")

                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding.pick2.setOnClickListener {
            val c = Calendar.getInstance()
            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val daysWeek = c.get(Calendar.DAY_OF_WEEK)


            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.checkOutDate.text =
                        ((monthOfYear + 1).toString() + "/" + dayOfMonth + "/" + year)

                     endDate  = ((year).toString()+"-"+(monthOfYear+1).toString()+"-"+dayOfMonth)
                    Log.d(TAG,"okay So End Date is ${endDate.toString()}")

                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding.confirm.setOnClickListener {

            try {

                var mDate1 = binding.checkInDate.text.toString()
                val mDate2 = binding.checkOutDate.text.toString()

                val mDateFormat = SimpleDateFormat("MM/dd/yyyy")

                // Converting the dates
                // from string to date format
                val mDate11 = mDateFormat.parse(mDate1)
                val mDate22 = mDateFormat.parse(mDate2)

                // Finding the absolute difference between
                // the two dates.time (in milli seconds)
                val mDifference = kotlin.math.abs(mDate11.time - mDate22.time)

                // Converting milli seconds to dates
                val mDifferenceDates = mDifference / (24 * 60 * 60 * 1000)

                // Converting the above integer to string
                val mDayDifference = mDifferenceDates.toString()

                Toast.makeText(
                    requireContext(),
                    "date Difference $mDayDifference",
                    Toast.LENGTH_SHORT
                ).show()

                //setting up every things


                binding.daysInterval.text = mDayDifference
                val intDate:Int = Integer.parseInt(mDayDifference)
                calculatedAmount = intDate* roomPrice!!
                Log.d(constants.TAG,"Calculated amount is $calculatedAmount")
                binding.payableAmount.text= calculatedAmount.toString()


                if (binding.payCard.visibility == View.GONE) {

                    binding.payCard.visibility = View.VISIBLE

                } else {

                    binding.payCard.visibility = View.GONE

                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Data failed", Toast.LENGTH_SHORT).show()

            }



        }

        // setting  the call for api
        binding.book.setOnClickListener{
            try {
                val userId = arg.userId
                val roomId = arg.roomDataa._id
                val hotelId = arg.roomDataa.hotel
                val checkinData = binding.checkInDate.toString()
                val checkOutData = binding.checkOutDate.toString()


                // setting book
                try {
                    Log.d(constants.TAG,"Datas are $userId  , $hotelId , $roomId  ,${startDate} ,${endDate}")
                    getAllHotelViewModel.bookRoom(userId,hotelId,roomId,BookRequest( startDate!!,endDate!!))

                    getAllHotelViewModel.bookNowResponseLiveData.observe(viewLifecycleOwner,
                        Observer {

                            binding.progressBar.isVisible = true

                            when(it)
                            {
                                is NetworkResult.Loading->{
                                 binding.progressBar.isVisible = true
                                }
                                is NetworkResult.Success->{
                                    Toast.makeText(requireContext(), "Booking Sucessfull View in Profile ", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_userBookingFragment_to_userHomeFragment,Bundle().apply {
                                        putString("userId",userId)
                                    })
                                }
                                is NetworkResult.Error->{}
                            }
                        })



                }catch (e:java.lang.Exception)
                {
                    Toast.makeText(requireContext(), "Booking Failed", Toast.LENGTH_SHORT).show()
                }



            }catch (e:java.lang.Exception)
            {
                Log.d(constants.TAG,"Error on booking call ii guess ")
            }

        }


        return binding.root

    }


}
