package com.example.bookingappkotlinver3.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.bookingappkotlinver3.MainActivity
import javax.inject.Inject

abstract class BaseViewModelFragment<Binding : ViewBinding, VM : ViewModel> :
    BaseFragment<Binding>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initOnClickListener()
        observeLiveData()
    }

    abstract fun setupViews()
    abstract fun initOnClickListener()
    abstract fun observeLiveData()

}