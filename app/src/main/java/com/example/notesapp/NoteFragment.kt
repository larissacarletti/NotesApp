package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            TODO("Aqui em baixo que vai acontecer a lógica que quer realizar")
            val action = NoteFragmentDirections.actionNoteFragmentToHomeFragment2("título aqui", "nota aqui")
            findNavController().navigate(action)
        }
    }
}