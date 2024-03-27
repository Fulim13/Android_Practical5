package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demo.data.Friend
import com.example.demo.data.FriendVM
import com.example.demo.databinding.FragmentInsertBinding
import com.example.demo.util.cropToBlob
import com.example.demo.util.errorDialog
import com.example.demo.util.setImageBlob

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
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imgPhoto.setImageURI(it)
    }

    private fun select() {
        // TODO: Select file
        getContent.launch("image/*")
    }

    private fun submit() {
        // TODO: Friend object
        val f = Friend(
            id    = binding.edtId.text.toString().trim(),
            name  = binding.edtName.text.toString().trim(),
            age   = binding.edtAge.text.toString().toIntOrNull() ?: 0,
            photo = binding.imgPhoto.cropToBlob(300, 300)
        )


        // TODO: Validation
        val e = vm.validate(f)
        if (e != "") {
            errorDialog(e)
            return
        }


        // TODO: Set (insert) and back
        vm.set(f)
        nav.navigateUp()

    }

}