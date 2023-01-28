package com.e444er.flowtest.crypto_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.e444er.flowtest.R
import com.e444er.flowtest.databinding.ActivityCryptoBinding

class CryptoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCryptoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CryptoViewModel::class.java]
    }

    private val adapter = CryptoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCurrencyPriceList.adapter = adapter
        binding.recyclerViewCurrencyPriceList.itemAnimator = null
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) {
            when (it) {
                is State.Initial -> {
                    binding.progressBarLoading.isVisible = false
                }
                is State.Loading -> {
                    binding.progressBarLoading.isVisible = true
                }
                is State.Content -> {
                    binding.progressBarLoading.isVisible = false
                    adapter.submitList(it.currencyList)
                }
            }
        }
    }
}