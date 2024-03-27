package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val nav by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        read()
        binding.btnRead.setOnClickListener   { read() }
        binding.btnInsert.setOnClickListener { insert() }
        binding.btnUpdate.setOnClickListener { update() }
        binding.btnDelete.setOnClickListener { delete() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun read() {
        // TODO: Read all records

    }

    private fun insert() {
        // TODO: Insert record A003 --> Carol, 20

    }

    private fun update() {
        // TODO: Update record A003 --> Cindy, 99

    }

    private fun delete() {
        // TODO: Delete record A003

    }

}