package com.e444er.flowtest.lesson.lesson2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.e444er.flowtest.databinding.ActivityUser2Binding

class User2Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUser2Binding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        viewModel.users.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }
}