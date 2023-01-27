package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomeFragment : Fragment(R.layout.fragment_home), NotesAdapter.NotesClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedNote: Note
    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
         ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        setupNoteList()
        binding.fabNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(null)
            findNavController().navigate(action)
        }
        binding.imgDeleteAll.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.ThemeOverlay_App_MaterialAlertDialog
            ).setIcon(R.drawable.delete)
                .setMessage(resources.getString(R.string.alert))
                .setTitle(resources.getString(R.string.alert_title))
                .setNegativeButton(resources.getString(R.string.decline)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                    note?.let { note -> viewModel.deleteAllNotes() }

                }.show()


        }
    }

    private fun setupNoteList() = binding.run {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotesAdapter(requireContext(), this@HomeFragment)
        recyclerView.adapter = adapter
        viewModel.allNotes.observe(viewLifecycleOwner) { noteList ->
            adapter.updateList(noteList)
            if (noteList.isEmpty()) {
                imgDeleteAll.visibility = View.GONE
            } else {
                imgDeleteAll.visibility = View.VISIBLE
            }
        }

    }

    override fun onItemClicked(note: Note) {
        selectedNote = note
        val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(note)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
