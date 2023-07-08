package com.example.bookingappkotlinver3.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<Binding : ViewBinding> : Fragment(), CoroutineScope {
    private lateinit var job: Job
    protected lateinit var binding: Binding

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
}