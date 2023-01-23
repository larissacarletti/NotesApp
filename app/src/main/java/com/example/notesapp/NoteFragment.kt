package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var note : Note
    private lateinit var oldNote : Note
    private var isUpdated = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater,container,false)

        return binding.root



    }


}