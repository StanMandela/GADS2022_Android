package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trialapp.DataManager
import com.example.trialapp.NoteActivity
import com.example.trialapp.NoteRecyclerAdapter
import com.example.trialapp.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_items.*

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val noteLayoutManager by lazy{ LinearLayoutManager(this)}

    private val noteRecyclerAdapter by lazy{ NoteRecyclerAdapter(this, DataManager.notes)}
    private val courseLayoutManager by lazy{
        GridLayoutManager(this,2)
    }
    private val courseRecyclerAdapter by lazy{
        CourseRecyclerAdapter(this,DataManager.courses.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))

        }

        displayNotes()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked=true
    }
    private fun displayCourses(){
        listItems.layoutManager=courseLayoutManager
        listItems.adapter =courseRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_courses).isChecked= true
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_courses -> {
                displayCourses()
            }
            R.id.nav_notes -> {
                displayNotes()

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {

        Snackbar.make(listItems,message,Snackbar.LENGTH_LONG).show()
    }

}