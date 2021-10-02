package com.example.listshoppingroom

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listshoppingroom.databinding.ActivityMainBinding
import com.example.listshoppingroom.repository.ListShopRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
    }
    private fun buildList() {
// Get Repository
        val repository = ListShopRepository.getRepository(this)
// Build Layout manager
        val layoutManager = LinearLayoutManager(this)
// Catch other thread
        lifecycleScope.launch {
            repository.allListShop.collect { product ->

                binding.rvList.apply {
                    adapter = ListShopAdapter(product)
                    setLayoutManager(layoutManager)


                         binding.result.text="$"+repository.getTotal().toString()



                }


            }

        }


    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }



    }
}