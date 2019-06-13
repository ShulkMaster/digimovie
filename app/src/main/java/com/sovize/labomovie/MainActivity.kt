package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.sovize.labomovie.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        vm.fetchMovie("clannad")
    }
}
