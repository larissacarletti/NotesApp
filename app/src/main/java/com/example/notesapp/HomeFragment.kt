package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home),NotesAdapter.NotesClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NoteViewModel
    private lateinit var selectedNote: Note

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
        initUI()
        binding.fabNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
            findNavController().navigate(action)
        }
    }
    private fun initUI() = binding.run {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotesAdapter(requireContext(), this@HomeFragment)
        recyclerView.adapter = adapter
        viewModel.allNotes.observe(viewLifecycleOwner) { noteList ->
            adapter.updateList(noteList)
        }
    }
    override fun onItemClicked(note: Note) {
        selectedNote = note
        if(selectedNote.id != null) {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
