package com.example.newinstagram

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.newinstagram.databinding.ItemNoteBinding

class NoteAdapter(val listener: ItemClick): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var list: MutableList<Note> = ArrayList()

    fun addNote(note: Note){
        list.add(note)
        notifyItemInserted(list.size)
    }

    fun edit(pos:Int, note: Note) {
        list.set(pos,note)
        notifyItemChanged(pos)
    }

    fun delete(pos: Int){
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

     inner class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){

        fun  bind(note: Note){
            binding.itemText.text = note.title
            binding.itemTextDesc.text = note.desc
            binding.itemText.setOnClickListener{
               listener.delete(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return  list.size

    }
}

