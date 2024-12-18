package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.data.Friend
import com.example.demo.databinding.FragmentHomeBinding
import com.example.demo.util.toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val nav by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------
        //every time the activity run, it will read
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
        Firebase.firestore.collection("friends")
            .get()
            .addOnSuccessListener {
                val list = it.toObjects<Friend>()
                val s = list.joinToString("\n") { "${it.id} ${it.name} ${it.age}" }
                binding.txtResult.text = s
            }
//        .get() // All Record
//         .document("A001").get() // Single Record
    }

    private fun insert() {
        // TODO: Insert record A003 --> Carol, 20
        val f = Friend("A003","Carol",20)

        Firebase.firestore.collection("friends")
            .document(f.id)
            .set(f) // insert or replace(entire fields)
            .addOnSuccessListener {
                toast("Inserted")
                read()
            }

    }

    private fun update() {
        // TODO: Update record A003 --> Cindy, 99
        Firebase.firestore.collection("friends")
            .document("A003")
            .update("name", "Cindy", "age", 99) //update single field
            .addOnSuccessListener {
                toast("Updated")
                read()
            }
    }

    private fun delete() {
        // TODO: Delete record A003
        Firebase.firestore.collection("friends")
            .document("A003")
            .delete()
            .addOnSuccessListener {
                toast("Deleted")
                read()
            }

    }

}