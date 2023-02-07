package com.e444er.flowtest.crypto_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.e444er.flowtest.R
import com.e444er.flowtest.databinding.ActivityCryptoBinding
import kotlinx.coroutines.launch

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
        binding.button.setOnClickListener {
            viewModel.refreshList()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCurrencyPriceList.adapter = adapter
        binding.recyclerViewCurrencyPriceList.itemAnimator = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state
                    .collect {
                        when (it) {
                            is State.Initial -> {
                                binding.progressBarLoading.isVisible = false
                                binding.button.isEnabled = false
                            }
                            is State.Loading -> {
                                binding.progressBarLoading.isVisible = true
                                binding.button.isEnabled = false
                            }
                            is State.Content -> {
                                binding.progressBarLoading.isVisible = false
                                adapter.submitList(it.currencyList)
                                binding.button.isEnabled = true
                            }
                        }
                    }
            }

        }

    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.loadData()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        viewModel.stopLoading()
//    }
}