package com.example.notesapp

import android.app.Activity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
        private var _binding : FragmentHomeBinding? = null
        private val binding get() = _binding!!
        lateinit var adapter : NotesAdapter
        lateinit var viewModel : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root

        initUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
            findNavController().navigate(action)
        }

    }

    private fun initUI() = binding.run{
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = NotesAdapter(this, this@HomeFragment)
        recyclerView.adapter = adapter

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK){
                    val note = result.data?.getSerializableExtra("note") as? Note
                    if(note != null) viewModel.insertNote(note)
                }
            }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}

