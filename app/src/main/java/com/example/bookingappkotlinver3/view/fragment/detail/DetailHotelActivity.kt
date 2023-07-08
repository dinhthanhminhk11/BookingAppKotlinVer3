package com.example.bookingappkotlinver3.view.fragment.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bookingappkotlinver3.MainActivity
import com.example.bookingappkotlinver3.R
import com.example.bookingappkotlinver3.base.BaseViewModelFragment
import com.example.bookingappkotlinver3.databinding.FragmentDetailHotelActivityBinding
import com.example.bookingappkotlinver3.viewmodel.MainViewModel

class DetailHotelActivity :
    BaseViewModelFragment<FragmentDetailHotelActivityBinding, MainViewModel>() {
    override val viewModel: MainViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun setupViews() {
        initToolbar()
    }

    override fun initOnClickListener() {

    }

    override fun observeLiveData() {

    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailHotelActivityBinding {
        return FragmentDetailHotelActivityBinding.inflate(inflater, container, false)
    }

    private fun initToolbar() {
        binding.toolBar.navigationIcon = ContextCompat.getDrawable(
            requireActivity(),
            R.drawable.ic_baseline_arrow_back_ios_new_24
        )
        binding.toolBar.background = null
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolBar.title = ""
    }

}