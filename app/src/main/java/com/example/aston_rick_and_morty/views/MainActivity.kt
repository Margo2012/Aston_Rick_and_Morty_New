package com.example.aston_rick_and_morty.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.aston_rick_and_morty.R
import com.example.aston_rick_and_morty.databinding.ActivityMainBinding

import com.example.aston_rick_and_morty.views.fragments.CharactersFragment
import com.example.aston_rick_and_morty.views.fragments.EpisodeFragment
import com.example.aston_rick_and_morty.views.fragments.LocationFragment
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener(navListener)

    }

    private val navListener = NavigationBarView.OnItemSelectedListener{ item ->
        when(item.itemId){
            R.id.nav_character ->fragment = CharactersFragment()
            R.id.nav_location -> fragment = LocationFragment()
            R.id.nav_episode -> fragment = EpisodeFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
        true
    }
}