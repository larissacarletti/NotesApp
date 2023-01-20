package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
        private var _binding : FragmentHomeBinding? = null
        private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
            findNavController().navigate(action)
        }



    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}

