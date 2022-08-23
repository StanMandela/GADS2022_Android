package com.example.trialapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        list_notes.adapter= ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            DataManager.notes
        )
        list_notes.setOnItemClickListener{parent,view,position,id->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(EXTRA_NOTE_POSITION,position)
            startActivity(activityIntent)
        }
    }

}

