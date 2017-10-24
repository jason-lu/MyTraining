package com.onebit.mytraining

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        //Defualt fragment is planFragment. Shows all plans
        if(savedInstanceState == null) {
            supportFragmentManager
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
        val id = R.id.nav_plan;
        val item = nav_view.menu.findItem(id)
        onNavigationItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            confirm()
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

        val fragmentManager = getSupportFragmentManager()
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
