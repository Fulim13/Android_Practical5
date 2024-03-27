package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo.R
import com.example.demo.data.FriendVM
import com.example.demo.databinding.FragmentListBinding
import com.example.demo.util.FriendAdapter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val nav by lazy { findNavController() }
    private val vm: FriendVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnInsert.setOnClickListener { nav.navigate(R.id.insertFragment) }

        val adapter = FriendAdapter { h, f ->
            h.binding.btnUpdate.setOnClickListener { update(f.id) }
            h.binding.btnDelete.setOnClickListener { vm.delete(f.id) }
        }

        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        vm.friendsLD.observe(viewLifecycleOwner) {
            binding.txtCount.text = "${it.size} friend(s)"
            adapter.submitList(it)
        }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun update(friendId: String) {
        nav.navigate(R.id.updateFragment, bundleOf(
            "friendId" to friendId
        ))
    }

}