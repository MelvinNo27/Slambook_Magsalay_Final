package com.example.my_slambook1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.my_slambook1.databinding.Form1Binding

class Form1 : AppCompatActivity() {

    private lateinit var binding: Form1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = Form1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext1.setOnClickListener {
            val intent = Intent(this, Form2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
