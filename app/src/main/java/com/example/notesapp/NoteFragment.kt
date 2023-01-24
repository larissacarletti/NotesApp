package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    // problema do seu crash está aqui também
    private lateinit var note: Note
    private lateinit var oldNote: Note
    private var isUpdated = false
    private lateinit var viewModel: NoteViewModel
    private lateinit var notesAdapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.run {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            val title = etTitle.text.toString()
            val note = etNote.text.toString()
            val action = NoteFragmentDirections.actionNoteFragmentToHomeFragment2(title, note)
            findNavController().navigate(action)
            viewModel = ViewModelProvider(this@NoteFragment)[NoteViewModel::class.java]
            viewModel.insertNote(Note(title = title, note = note))
        }


        binding.imgDelete.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.ThemeOverlay_App_MaterialAlertDialog
            ).setTitle(resources.getString(R.string.alert_title))
                .setMessage(resources.getString(R.string.alert))
                .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                    // problema do crash está no lateinit que comentei acima
                    viewModel.deleteNote(note)
                }
                .setNegativeButton(resources.getString(R.string.decline)) { _, _ -> }
                .setIcon(R.drawable.delete)
                .show()
        }
    }
}