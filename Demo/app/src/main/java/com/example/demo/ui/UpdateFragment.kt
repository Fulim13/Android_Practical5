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
import com.example.demo.databinding.FragmentUpdateBinding
import com.example.demo.util.cropToBlob
import com.example.demo.util.errorDialog
import com.example.demo.util.setImageBlob

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val nav by lazy { findNavController() }
    private val vm: FriendVM by activityViewModels()
    private val friendId by lazy { requireArguments().getString("friendId", "") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        reset()
        binding.imgPhoto.setOnClickListener  { select() }
        binding.btnReset.setOnClickListener  { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        binding.btnDelete.setOnClickListener { delete() }
        binding.btnBack.setOnClickListener   { nav.navigateUp() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun reset() {
        // TODO: Get record by id
        val f = vm.get(friendId)
        if(f == null) {
            nav.navigateUp()
            return
        }

        binding.txtId.text = f.id
        binding.edtName.setText(f.name)
        binding.edtAge.setText(f.age.toString())
        binding.imgPhoto.setImageBlob(f.photo)

        binding.edtName.requestFocus()
    }

    // Get-content launcher
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imgPhoto.setImageURI(it)
    }

    private fun select() {
        // Select file
        getContent.launch("image/*")
    }

    private fun submit() {
        val f = Friend(
            id    = binding.txtId.text.toString().trim(),
            name  = binding.edtName.text.toString().trim(),
            age   = binding.edtAge.text.toString().toIntOrNull() ?: 0,
            photo = binding.imgPhoto.cropToBlob(300, 300)
        )

        val e = vm.validate(f, false)
        if (e != "") {
            errorDialog(e)
            return
        }

        vm.set(f)
        nav.navigateUp()
    }

    private fun delete() {
        // Delete and back
        vm.delete(friendId)
        nav.navigateUp()
    }

}