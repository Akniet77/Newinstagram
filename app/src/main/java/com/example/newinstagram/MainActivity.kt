package com.example.newinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newinstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemClick {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter(this)

        binding.mainRecycler.adapter = adapter

        binding.addNote.setOnClickListener {
            if (binding.editNote.text.isEmpty()) {
                Toast.makeText(this, "Заполни поле!!!!", Toast.LENGTH_SHORT).show()
            } else {
                val note =
                    Note(binding.editNote.text.toString(), binding.editNoteDesc.text.toString())
                adapter.addNote(note)
                binding.editNote.setText("")
            }

        }
    }

    override fun delete(pos: Int) {
        adapter.delete(pos)
    }

    override fun edit(pos: Int) {
        val editNote = adapter.list[pos]
        binding.editNote.setText(editNote.title)
        binding.editNoteDesc.setText(editNote.desc)
        binding.addNote.setOnClickListener {
            val newNote = Note(binding.editNote.text.toString(),binding.editNoteDesc.text.toString(),)
            adapter.edit(pos,newNote)
        }

    }
}
