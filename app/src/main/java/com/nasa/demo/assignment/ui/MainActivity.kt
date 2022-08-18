package com.nasa.demo.assignment.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nasa.demo.assignment.R
import com.nasa.demo.assignment.ui.fragment.DatePickerFragment
import com.nasa.demo.assignment.ui.fragment.FavoriteListFragment
import com.nasa.demo.assignment.ui.fragment.ImageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ImageFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_date_picker -> {
                showDatePickerDialog()
                true
            }
            R.id.action_favorite -> {
                showFavoriteList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFavoriteList() {
        val existingFragment = supportFragmentManager.findFragmentByTag("favoriteList")
        if (existingFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, FavoriteListFragment(), "favoriteList")
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showDatePickerDialog() {
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }
}