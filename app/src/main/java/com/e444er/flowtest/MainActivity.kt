package com.e444er.flowtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e444er.flowtest.crypto_app.CryptoActivity
import com.e444er.flowtest.databinding.ActivityMainBinding
import com.e444er.flowtest.lesson.lesson2.UserActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
        binding.buttonCrypto.setOnClickListener {
            val intent = Intent(this, CryptoActivity::class.java)
            startActivity(intent)
        }
    }
}