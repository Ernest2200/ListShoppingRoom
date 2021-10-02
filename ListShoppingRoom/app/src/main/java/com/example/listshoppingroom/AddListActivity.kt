package com.example.listshoppingroom

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.example.listshoppingroom.databinding.ActivityAddListBinding
import com.example.listshoppingroom.entities.ListShop
import com.example.listshoppingroom.repository.ListShopRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class AddListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }
    private fun addListener() {


        val repository = ListShopRepository.getRepository(this)
        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etName.text.isNullOrBlank() || etPrice.text.isNullOrBlank() || etAmount.text.isNullOrBlank()) {
                    Snackbar.make(this.root, "Algunos campos estan vacios", Snackbar.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                ListShop(
                                    name = etName.text.toString(),
                                    price =  etPrice.text.toString().toDouble() ,
                                    amount = etAmount.text.toString().toDouble()
                                )
                            )
                        }
                        onBackPressed()
                    }
                }
            }
        }
    }
    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}