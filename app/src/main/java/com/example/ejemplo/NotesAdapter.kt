package com.example.ejemplo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.databinding.NoteItemBinding

class NotesAdapter(private val notes: List<String>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.noteTextView.text = note
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)
}
