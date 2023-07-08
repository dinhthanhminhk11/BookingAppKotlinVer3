package com.example.bookingappkotlinver3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookingappkotlinver3.databinding.ActivityMainBinding
import com.example.bookingappkotlinver3.view.adapter.NearFromYouAdapter
import com.example.bookingappkotlinver3.viewmodel.MainViewModel
import com.example.bookingappkotlinver3.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: MainViewModelFactory

    @Inject
    lateinit var nearFromYouAdapter: NearFromYouAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
}