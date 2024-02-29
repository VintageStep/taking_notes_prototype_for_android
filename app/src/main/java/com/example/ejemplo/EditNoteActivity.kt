package com.example.ejemplo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.ejemplo.databinding.ActivityEditNoteBinding

class EditNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val note = binding.editTextNote.text.toString()
            if (note.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("NOTE_EXTRA", note)
                setResult(RESULT_OK, resultIntent)
            }
            finish()
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private lateinit var binding: ActivityEditNoteBinding
}