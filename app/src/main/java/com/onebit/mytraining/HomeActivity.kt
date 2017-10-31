package com.onebit.mytraining

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.onebit.mytraining.model.Exercise
import com.onebit.mytraining.model.FragComm
import com.onebit.mytraining.model.Program
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener, FragComm {

    companion object {
        lateinit var list: ArrayList<Program>
    }

    private lateinit var fragmentManager: FragmentManager

    override fun sendProgram(pos: Int) {
        val fragment = ProgramDetailFragment.newInstance()
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
            list = ArrayList()
            val program = Program(title = "My Training plan",date = Date(),trainer = "Dan",trainee = "Kuan",
                    mainGoal = "Lose weight", proGoal = "Correct movement patterns.",
                    restrains = "Overall Tightness." , exercises = exercises)
            for(i in 1 .. 5 step 1) {
                list.add(program)
            }

            return list
        }

        initPlan()
        //list = ArrayList()


        //Default fragment is planFragment. Shows all plans
        if(savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ProgramFragment.newInstance(), ProgramFragment.tag)
                    .commit()
        }



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

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

    fun confirm() {
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



}
