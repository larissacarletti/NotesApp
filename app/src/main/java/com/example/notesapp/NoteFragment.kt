package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var note : Note
    private lateinit var oldNote : Note
    private var isUpdated = false

    private lateinit var viewModel : NoteViewModel


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
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()
            val action = NoteFragmentDirections.actionNoteFragmentToHomeFragment2(title,note)
            findNavController().navigate(action)
        }


        binding.imgDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(),R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(resources.getString(R.string.alert_title))
                .setMessage(resources.getString(R.string.alert))
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->

                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    viewModel.deleteNote(note)
                }
                .setIcon(R.drawable.delete)
                .show()



        }
    }
}