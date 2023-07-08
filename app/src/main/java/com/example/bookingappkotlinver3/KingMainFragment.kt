package com.example.bookingappkotlinver3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookingappkotlinver3.databinding.FragmentKingMainBinding
import com.example.bookingappkotlinver3.view.fragment.home.DiscoverFragment
import com.example.bookingappkotlinver3.view.fragment.home.FavoritesFragment
import com.example.bookingappkotlinver3.view.fragment.home.MessageFragment
import com.example.bookingappkotlinver3.view.fragment.home.PersonFragment
import com.example.bookingappkotlinver3.view.fragment.home.TravelFragment

class KingMainFragment : Fragment() {
    private lateinit var binding: FragmentKingMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKingMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(DiscoverFragment())

        binding.bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_discover -> {
                    loadFragment(DiscoverFragment())
                    true
                }

                R.id.navigation_favorites -> {
                    loadFragment(FavoritesFragment())
                    true
                }

                R.id.navigation_trips -> {
                    loadFragment(TravelFragment())
                    true
                }

                R.id.navigation_inbox -> {
                    loadFragment(MessageFragment())
                    true
                }

                R.id.navigation_profile -> {
                    loadFragment(PersonFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }
}
