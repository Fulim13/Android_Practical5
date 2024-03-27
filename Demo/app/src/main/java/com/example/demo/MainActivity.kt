package com.example.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val nav by lazy { supportFragmentManager.findFragmentById(R.id.host)!!.findNavController() }
    private lateinit var abc: AppBarConfiguration

    // TODO(A): Load view models


    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO(B): Initialize view models


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        abc = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.listFragment
            ),
            binding.root
        )

        setupActionBarWithNavController(nav, abc)
        binding.bv.setupWithNavController(nav)
        binding.nv.setupWithNavController(nav)

        binding.bv.menu.findItem(R.id.exit).setOnMenuItemClickListener {
            finish()
            true
        }

        binding.nv.menu.findItem(R.id.exit).setOnMenuItemClickListener {
            finish()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return nav.navigateUp(abc)
    }

}