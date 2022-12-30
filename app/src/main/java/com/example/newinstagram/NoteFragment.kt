package com.example.newinstagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import com.example.newinstagram.databinding.ActivityMainBinding
import com.example.newinstagram.databinding.ActivityMainBinding.inflate
import com.example.newinstagram.databinding.FragmentNoteBinding
import com.example.newinstagram.databinding.FragmentNoteBinding.inflate


@Suppress("UNREACHABLE_CODE")
class NoteFragment : Fragment(), ItemClick{

    private lateinit var binding: FragmentNoteBinding
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NoteAdapter(this)

        binding.mainRecycler.adapter = adapter
        setAddListener()
        binding.mainRecycler.adapter = adapter
        binding.sort.setOnClickListener {
            adapter.sort()
        }

    }



    override fun delete(pos: Int) {
        val alert = AlertDialog.Builder(requireContext()).also {
            it.setTitle(getString(R.string.warning))
            it.setMessage(getString(R.string.message))
        }
        alert.setPositiveButton("ok"){_, _ ->
            adapter.delete(pos)

        }
        alert.setNeutralButton(getString(R.string.cancel), null)
        alert.show()
    }

    override fun edit(pos: Int) {
        val editNote = adapter.list[pos]
        binding.editNote.setText(editNote.title)
        binding.editNoteDesc.setText(editNote.desc)
        binding.addNote.setOnClickListener {
            if (binding.editNote.text.isEmpty()) {
                Toast.makeText(requireContext(), "Запоните поле!!!", Toast.LENGTH_SHORT).show()
            }else{
                val newNote = Note(binding.editNote.text.toString(),binding.editNoteDesc
                    .text.toString(),)
                adapter.edit(pos,newNote)
            }
            setAddListener()
        }
    }


    fun  setAddListener(){
        binding.addNote.text = getString(R.string.add)
        binding.addNote.setOnClickListener {
            if (binding.editNote.text.isEmpty()) {
                Toast.makeText(requireContext(),"Заполни поле!!!!", Toast.LENGTH_SHORT).show()
            } else {
                val note =
                    Note(binding.editNote.text.toString(), binding.editNoteDesc.text.toString())
                adapter.addNote(note)
                binding.editNote.setText("")
            }
        }

    }
}
