package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demo.data.FriendVM
import com.example.demo.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {

    private lateinit var binding: FragmentInsertBinding
    private val nav by lazy { findNavController() }
    private val vm: FriendVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInsertBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        reset()
        binding.imgPhoto.setOnClickListener  { select() }
        binding.btnReset.setOnClickListener  { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        binding.btnBack.setOnClickListener   { nav.navigateUp() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun reset() {
        binding.edtId.text.clear()
        binding.edtName.text.clear()
        binding.edtAge.text.clear()
        binding.imgPhoto.setImageDrawable(null)

        binding.edtId.requestFocus()
    }

    // TODO: Get-content launcher
    private val getContent = 0

    private fun select() {
        // TODO: Select file

    }

    private fun submit() {
        // TODO: Friend object
        val f = 0

        // TODO: Validation
        val e = 0

        // TODO: Set (insert) and back

    }

}