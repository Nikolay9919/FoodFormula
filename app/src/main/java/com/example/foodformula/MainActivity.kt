package com.example.foodformula

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.foodformula.UI.RecipeFragment
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val bundle = Bundle()
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = RecipeFragment()
        transaction.add(R.id.fragment_holder, fragment)
        fragment.arguments = bundle
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when (item.itemId) {
            R.id.action_search -> openSearchFragment()
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun openSearchFragment(): Boolean {

        return true
    }


}
