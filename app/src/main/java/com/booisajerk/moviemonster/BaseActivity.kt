package com.booisajerk.moviemonster

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
