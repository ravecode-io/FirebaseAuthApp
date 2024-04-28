package io.ravecode.firebaseauthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.ravecode.firebaseauthapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Binding Variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting up binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}