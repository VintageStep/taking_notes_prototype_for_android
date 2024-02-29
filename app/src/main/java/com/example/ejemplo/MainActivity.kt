package com.example.ejemplo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemplo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lista de notas de prueba
    private val notes = mutableListOf("Nota 1", "Nota 2", "Nota 3")

    // Se agregan al adaptador
    private val notesAdapter = NotesAdapter(notes)

    // Declaración de ActivityResultLauncher
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        // Inicializa el ActivityResultLauncher
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val note = result.data?.getStringExtra("NOTE_EXTRA")
                note?.let {
                    addNote(it)
                }
            }
        }

        binding.addNoteButton.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter
    }

    // Método para agregar una nota y actualizar el RecyclerView
    private fun addNote(note: String) {
        notes.add(note)
        notesAdapter.notifyItemInserted(notes.size - 1)
    }

    // Manejar el resultado de EditNoteActivity
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.getStringExtra("NOTE_EXTRA")?.let {
                addNote(it)
            }
        }
    }

    companion object {
        private const val NEW_NOTE_REQUEST_CODE = 1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_note -> {
                val intent = Intent(this, EditNoteActivity::class.java)
                resultLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}