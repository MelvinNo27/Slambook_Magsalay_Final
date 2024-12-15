package com.example.my_slambook1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.my_slambook1.databinding.Form2Binding

class Form2 : AppCompatActivity() {

    private lateinit var binding: Form2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = Form2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnList.setOnClickListener {
            val intent = Intent(this, List::class.java)
            startActivity(intent)
            finish()
        }
    }
}
