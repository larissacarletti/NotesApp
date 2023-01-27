package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val args: NoteFragmentArgs by navArgs()
    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null
    private val isUpdated = false
    private lateinit var oldNote : Note


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.run {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this@NoteFragment)[NoteViewModel::class.java]
        setupDialog()
        setupToolbar()
        setupOpenNote()
    }

    private fun setupDialog() = binding.run {
        imgDelete.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.ThemeOverlay_App_MaterialAlertDialog
            ).setIcon(R.drawable.delete)
                .setMessage(resources.getString(R.string.alert))
                .setTitle(resources.getString(R.string.alert_title))
                .setNegativeButton(resources.getString(R.string.decline)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                    note?.let { note -> viewModel.deleteNote(note) }
                    findNavController().popBackStack()
                }.show()
        }
    }

    private fun setupOpenNote() = binding.run {
        note = args.note
        etNote.setText(note?.note)
        etTitle.setText(note?.title)
    }

    private fun setupToolbar() = binding.run {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            val noteTitle = etTitle.text.toString()
            val noteDescription = etNote.text.toString()
            if (note == null) {
                viewModel.insertNote(
                    Note(
                        title = noteTitle,
                        note = noteDescription
                    )
                )
            } else {
                viewModel.updateNote(
                    Note(
                        id = oldNote.id,
                        title = noteTitle,
                        note = noteDescription
                    )
                )
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}