package com.onebit.mytraining

import android.app.Activity
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
import android.widget.Toast
import com.onebit.mytraining.model.Exercise
import com.onebit.mytraining.util.FragComm
import com.onebit.mytraining.model.Program
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener, FragComm {

    companion object {
        lateinit var list: ArrayList<Program>
    }

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

        fun initPlan(): ArrayList<Program> {
            val exercises = ArrayList<Exercise>()
            val exercise = Exercise("a1","QL walk",1,1)
            val exercise2 = Exercise("a2","QL walk2",2,2)
            exercises.add(exercise)
            exercises.add(exercise2)
            val days = HashMap<Int, ArrayList<Exercise>>()
            days.put(0,exercises)
            days.put(1,exercises)
            list = ArrayList()
            val program = Program(title = "My Training plan",date = Date(),trainer = "Dan",trainee = "Kuan",
                    mainGoal = "Lose weight", proGoal = "Correct movement patterns.",
                    restrains = "Overall Tightness." , exercises = days )
            for(i in 1 .. 5 step 1) {
                list.add(program)
            }

            return list
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
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
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
        var buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)
        Toast.makeText(this,json,Toast.LENGTH_SHORT).show()
    }
}
