package com.example.tips

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tips.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {

            if (binding.coastOfService.text.isEmpty()) {
                binding.coastOfService.error = "Заполните поле"
            } else {
                calculateTip()
                hideKeyboard(binding.coastOfService)
            }
        }
    }

    private fun calculateTip() {

        val coast = binding.coastOfService.text.toString().toDouble()
        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_ten_percent -> 0.1
            R.id.option_seven_percent -> 0.07
            else -> 0.05
        }
        var tip = coast * percentage
        val round = binding.roundSwitch.isChecked
        if (round) {
            tip = kotlin.math.ceil(tip)
        }
        val currencyTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, currencyTip)
    }

    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
