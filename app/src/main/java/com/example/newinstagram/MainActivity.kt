package com.example.newinstagram


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newinstagram.databinding.ActivityMainBinding
import com.example.newinstagram.databinding.FragmentNoteBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frgSettingsMain = NoteFragment()
        noteFragment (frgSettingsMain)

    }


    private fun noteFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
}
