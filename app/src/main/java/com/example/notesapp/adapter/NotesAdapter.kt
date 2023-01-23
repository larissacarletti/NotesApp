package com.example.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.HomeFragment
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.databinding.ListItemBinding
import com.example.notesapp.models.Note

class NotesAdapter(
    private val context: Context,
    private val listener: HomeFragment
    ) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.run {
            title.setText(currentNote.title)
            title.isSelected = true
            note.setText(currentNote.note)
        }
        //holder.notesLayout.setOnClickListener {
            //listener.onItemClicked()
        //}
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)
        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val notesLayout: CardView = binding.cardNote
        val title: EditText = binding.tVTitle as EditText
        val note: EditText = binding.tVNote as EditText

    }

    //interface NotesClickListener {

       // fun onItemClick(note: Note)

        //fun onLongItemClicked(note: Note, cardView: CardView)

    //}

}

