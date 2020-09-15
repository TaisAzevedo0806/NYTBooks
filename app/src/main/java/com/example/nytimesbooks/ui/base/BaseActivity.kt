package com.example.nytimesbooks.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.nytimesbooks.R

// Open: define que esta classe pode ser pai de outras activities
open class BaseActivity : AppCompatActivity() {

    // Protected: só pode ser acessado pelas classes filhas
    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int, showBackButton: Boolean = true) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }

}