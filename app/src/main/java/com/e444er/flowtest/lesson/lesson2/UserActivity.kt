package com.e444er.flowtest.lesson.lesson2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.e444er.flowtest.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUserBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        binding.buttonNextScreen.setOnClickListener {
            val intent = Intent(this, User2Activity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }
}