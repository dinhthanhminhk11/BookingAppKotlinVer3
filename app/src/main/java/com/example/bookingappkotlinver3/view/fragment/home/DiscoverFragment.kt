package com.example.bookingappkotlinver3.view.fragment.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingappkotlinver3.MainActivity
import com.example.bookingappkotlinver3.R
import com.example.bookingappkotlinver3.base.BaseViewModelFragment
import com.example.bookingappkotlinver3.constant.AppConstant
import com.example.bookingappkotlinver3.constant.MySharedPreferences
import com.example.bookingappkotlinver3.constant.Resource
import com.example.bookingappkotlinver3.databinding.FragmentDiscoverBinding
import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.view.adapter.NearFromYouAdapter
import com.example.bookingappkotlinver3.view.bottomsheet.BottomSheetPersonHome
import com.example.bookingappkotlinver3.viewmodel.MainViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


class DiscoverFragment : BaseViewModelFragment<FragmentDiscoverBinding, MainViewModel>() {

    private var currentTimeNow: Date? = null
    private var currentTimeTomorrow: Date? = null
    private var daysDiffPrivate: Int = 1
    private var checkStartDate = "";
    private var checkEndDate = "";
    private var daysDiff = 1;

    private var countRoom = 2
    private var countPerson = 2
    private var countChildren = 2
    private var ageChildren = 1
    private var isLoading = false
    private var materialDatePicker: MaterialDatePicker<Pair<Long, Long>>? = null
    private lateinit var nearFromYouAdapter: NearFromYouAdapter
    override val viewModel: MainViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun initOnClickListener() {
        binding.contentPerson.setOnClickListener {
            val bottomSheetPersonHome =
                BottomSheetPersonHome(requireActivity(), object : BottomSheetPersonHome.Callback {
                    override fun onCLickSum(person: Int, children: Int, room: Int, age: Int) {
                        binding.countRoom.text = "$room ${getString(R.string.Room)}"
                        binding.countChildren.text = "$children ${getString(R.string.Children)}"
                        binding.countPerson.text = "$person ${getString(R.string.Adult)}"

                        countPerson = person;
                        countChildren = children;
                        countRoom = room;
                        ageChildren = age;
                    }
                })
            bottomSheetPersonHome.show()
            bottomSheetPersonHome.setCanceledOnTouchOutside(false)
        }

        binding.contentDate.setOnClickListener {
            if (!materialDatePicker!!.isAdded) {
                materialDatePicker!!.show(requireActivity().supportFragmentManager, "DATE_PICKER")
            }

            materialDatePicker!!.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {}

                override fun onStart(owner: LifecycleOwner) {
                    val root: View = materialDatePicker!!.requireView()
                }

                override fun onResume(owner: LifecycleOwner) {}

                override fun onDestroy(owner: LifecycleOwner) {
                    materialDatePicker!!.lifecycle.removeObserver(this)
                }
            })

            materialDatePicker!!.addOnPositiveButtonClickListener { selection ->
                val startDate: Long? = selection.first
                val endDate: Long? = selection.second

                SimpleDateFormat("EEE", Locale.getDefault()).format(startDate).apply {
                    binding.tvTimeNhanPhong.text = this
                }
                SimpleDateFormat("EEE", Locale.getDefault()).format(endDate).apply {
                    binding.dayEnd.text = this
                }

                SimpleDateFormat("dd", Locale.getDefault()).format(startDate).apply {
                    binding.startDate.text = this
                }
                SimpleDateFormat("dd", Locale.getDefault()).format(endDate).apply {
                    binding.endDate.text = this
                }

                SimpleDateFormat("MM", Locale.getDefault()).format(startDate).apply {
                    binding.monthDate.text = "${getString(R.string.Month)} $this"
                }
                SimpleDateFormat("MM", Locale.getDefault()).format(endDate).apply {
                    binding.monthEnd.text = "${getString(R.string.Month)} $this"
                }

                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(startDate).apply {
                    checkStartDate = this
                }
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(endDate).apply {
                    checkEndDate = this
                }

                if (startDate != null && endDate != null) {
                    val msDiff: Long = endDate - startDate
                    val daysDiff: Long = TimeUnit.MILLISECONDS.toDays(msDiff)

                    binding.payDay.text = daysDiff.toString()
                    daysDiffPrivate = daysDiff.toInt()
                }
            }
        }
    }

    override fun setupViews() {
        if (checkLocationPermission()) {
            viewModel.getCurrentLocation(requireActivity())
        } else {
            requestLocationPermission()
        }

        nearFromYouAdapter = (activity as MainActivity).nearFromYouAdapter
        nearFromYouAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(AppConstant.HOTEL_EXTRA, it)
            }
            findNavController().navigate(
                R.id.action_kingMainFragment_to_detailHotelActivity, bundle
            )
        }

        initRecyclerView()

        val builder: MaterialDatePicker.Builder<Pair<Long, Long>> =
            MaterialDatePicker.Builder.dateRangePicker()

        val constraintBuilder: CalendarConstraints.Builder = CalendarConstraints.Builder()
        constraintBuilder.setValidator(DateValidatorPointForward.now())

        val constraintsBuilder: CalendarConstraints.Builder = CalendarConstraints.Builder()

        builder.setCalendarConstraints(constraintsBuilder.build())
        builder.setTheme(R.style.ThemeOverlay_App_DatePicker)
        materialDatePicker = builder.setTitleText(getString(R.string.Select_a_date))
            .setPositiveButtonText(getString(R.string.SAVE))
            .setNegativeButtonText(getString(R.string.Thoat)).setSelection(
                androidx.core.util.Pair(
                    MaterialDatePicker.todayInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            ).setCalendarConstraints(constraintBuilder.build()).build()

        binding.payDay.text = daysDiffPrivate.toString()

        countRoom = MySharedPreferences.getInstance(requireActivity())
            .getInt(AppConstant.SHAREDPREFERENCES_USER_COUNT_ROOM, 2)
        countPerson = MySharedPreferences.getInstance(requireActivity())
            .getInt(AppConstant.SHAREDPREFERENCES_USER_COUNT_PERSON, 2)
        countChildren = MySharedPreferences.getInstance(requireActivity())
            .getInt(AppConstant.SHAREDPREFERENCES_USER_COUNT_CHILDREN, 2)
        ageChildren = MySharedPreferences.getInstance(requireActivity())
            .getInt(AppConstant.SHAREDPREFERENCES_USER_AGE_CHILDREN, 1)
        var textSearch = MySharedPreferences.getInstance(requireActivity())
            .getString(AppConstant.SHAREDPREFERENCES_USER_TEXT_SEARCH, "Khách sạn gần nhất")

        binding.countRoom.text = "$countRoom ${getString(R.string.Room)}"
        binding.countChildren.text = "$countChildren ${getString(R.string.Children)}"
        binding.countPerson.text = "$countPerson ${getString(R.string.Adult)}"
        binding.textSearch.text = textSearch

        initDate()
    }

    override fun observeLiveData() {
        viewModel.ctyData.observe(viewLifecycleOwner) {
            binding.nameAddress.text = it.toString()
        }
        viewModel.locationYouSelfMutableLiveData.observe(viewLifecycleOwner) {
            viewModel.getListNearByHotel(LocationNearByRequest(it.longitude, it.latitude, 10000))
        }
        viewModel.getAllListHotel()

        viewModel.resourceMutableLiveDataHotelNearBy.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        Log.i("MYTAG", "came here ${it.data.toList().size}")
                        nearFromYouAdapter.differ.submitList(it.data.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Log.i("MYTAG", "An error occurred : $it")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
        viewModel.listAllHotelMutableLiveData.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        Log.i("MYTAG", " datapros came here ${it.datapros.toList().size}")
//                        nearFromYouAdapter.differ.submitList(it.data.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Log.i("MYTAG", "An error occurred : $it")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentDiscoverBinding {
        return FragmentDiscoverBinding.inflate(inflater, container, false)
    }

    @SuppressLint("SetTextI18n")
    private fun initDate() {
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        currentTimeNow = Calendar.getInstance().time
        currentTimeTomorrow = calendar.time

        SimpleDateFormat("EEE", Locale.getDefault()).format(currentTimeNow).apply {
            binding.tvTimeNhanPhong.text = this
        }
        SimpleDateFormat("EEE", Locale.getDefault()).format(currentTimeTomorrow).apply {
            binding.dayEnd.text = this
        }

        SimpleDateFormat("dd", Locale.getDefault()).format(currentTimeNow).apply {
            binding.startDate.text = this
        }
        SimpleDateFormat("dd", Locale.getDefault()).format(currentTimeTomorrow).apply {
            binding.endDate.text = this
        }

        SimpleDateFormat("MM", Locale.getDefault()).format(currentTimeNow).apply {
            binding.monthDate.text = "${getString(R.string.Month)} $this"
        }
        SimpleDateFormat("MM", Locale.getDefault()).format(currentTimeTomorrow).apply {
            binding.monthEnd.text = "${getString(R.string.Month)} $this"
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        binding.recyclerviewNearFromYouHomeFragment.apply {
            adapter = nearFromYouAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun checkLocationPermission(): Boolean {
        val permissionResult = ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionResult == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            Companion.LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun handlePermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.getCurrentLocation(requireActivity())
            } else {
                binding.nameAddress.visibility = View.GONE
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}