package com.raywenderlich.android.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {

  private var tipPercentDouble = 0.0
  var billAmountDouble = 0.0
  private var count = 0
  //lateinit var textWatcher : TextWatcher


  private val textWatcher = object : TextWatcher {

    override fun afterTextChanged(s: Editable) {
      calculateTipPerPerson()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setDefaults()


    peopleCount.addTextChangedListener(textWatcher)
    tipPercent.addTextChangedListener(textWatcher)
    billAmount.addTextChangedListener(textWatcher)

  }

  private fun setDefaults() {
    peopleCount.setText((4).toString())
    tipPercent.setText((20).toString())
  }

  private fun calculateTipPerPerson() {

    if (tipPercent.text.isNotEmpty()) {
      tipPercentDouble = tipPercent.text.toString().toDouble()
    }

   if (billAmount.text.isNotEmpty()) {
      billAmountDouble = billAmount.text.toString().toDouble()
    }

    if (peopleCount.text.isNotEmpty()) {
      count = peopleCount.text.toString().toInt()
    }

    val tipAmount = (tipPercentDouble / 100) * billAmountDouble
    val totalIncludingTip = billAmountDouble + tipAmount
    totalTipPerPerson.text = (totalIncludingTip / count).toString()

  }

}
