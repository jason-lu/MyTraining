package com.onebit.mytraining

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.onebit.mytraining.model.Exercise
import com.onebit.mytraining.model.Program
import com.onebit.mytraining.util.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.io.File
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener, FragComm {

    companion object {
        lateinit var list: ArrayList<Program>
    }

    private val programFile = "programs.json"

    private val OPEN_REQUEST_CODE = 1


    private lateinit var fragmentManager: FragmentManager

    override fun sendProgram(pos: Int) {
        val fragment = ProgramDetailFragment.newInstance(pos)
        val tag = "ProgramDetailFragment"
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment,tag)
                .commit()
    }

    override fun getProgram(): ArrayList<Program> {
        return list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fragmentManager = getSupportFragmentManager()

//        layoutImport = findViewById(R.id.layoutFabImport)

        fun initPlan() {
            Log.d("internal", filesDir.toString())
            list = ArrayList()
            readPrograms()
        }

        initPlan()



        //Default fragment is planFragment. Shows all plans
        if(savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ProgramFragment.newInstance(), ProgramFragment.tag)
                    .commit()

            val toggle = ActionBarDrawerToggle(
                    this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            drawer_layout.addDrawerListener(toggle)
            toggle.syncState()

            nav_view.setNavigationItemSelectedListener(this)
            val id = R.id.nav_workout
            val item = nav_view.menu.findItem(id)
            onNavigationItemSelected(item)
        }


        fab.setOnClickListener { view ->

            openFile()
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if(fragmentManager.findFragmentByTag("ProgramDetailFragment") != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,ProgramFragment.newInstance(),"ProgramFragment")
                        .commit()
            } else {
                confirm()
            }
        }
    }

    private fun confirm() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(R.string.exit_confirm)
                .setNegativeButton(R.string.no, {
                    d,_ -> d.cancel()
                })
                .setPositiveButton(R.string.yes, {
                    _,_ -> finish()
                }).show()
    }


    override fun onPause() {
        super.onPause()
        val outputStream =  openFileOutput(programFile, Context.MODE_PRIVATE)
        outputStream.write(parsePrograms(list).toByteArray())
        outputStream.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.isChecked) return false

        var fragment = Fragment()
        var tag = ""
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_plan -> {
                tag = ProgramFragment.tag
                fragment = fragmentManager.findFragmentByTag(tag) ?: ProgramFragment.newInstance()
            }
            R.id.nav_workout -> {
                tag = WorkoutFragment.tag
                fragment = fragmentManager.findFragmentByTag(tag) ?: WorkoutFragment.newInstance()
            }
            R.id.nav_achievements -> {
                tag = AchievementFragment.tag
                fragment = fragmentManager.findFragmentByTag(tag) ?: AchievementFragment.newInstance()
            }

        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment,tag)
                .commit()

        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //import programs from external json file
    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "application/*"
        startActivityForResult(intent,OPEN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == OPEN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var uri: Uri? = null
            if (data != null) {
                uri = data.getData()
                Log.i("file", "Uri: " + uri.toString());
                readProgram(uri)
            }
        }
    }

    private fun readProgram(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)
        val program = gObjParser(json)
        list.add(program)
        val programFrag = supportFragmentManager.findFragmentByTag("ProgramFragment")
        if(programFrag != null && programFrag is ProgramFragment) {
            programFrag.ProgramAdded()
        }
    }
    private fun readPrograms() {
        val file = File(filesDir,programFile)
        if(file.exists()) {
            val inputStream = file.inputStream()
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer)
            json.length
            Log.d("json", json)
            if(json.length > 2) {
                list.addAll(parseJsons(json))
            }
        }

    }

    override fun saveProgram(pos: Int, program: HashMap<Int, ArrayList<Exercise>>) {
        list[pos].exercises = program
    }
}
