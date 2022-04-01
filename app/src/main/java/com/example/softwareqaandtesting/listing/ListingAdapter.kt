package com.example.softwareqaandtesting.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.softwareqaandtesting.calculator.CalculationType
import com.example.softwareqaandtesting.database.CalculationEntity
import com.example.softwareqaandtesting.databinding.ItemListingBinding

class ListingAdapter(private val calculationList: List<CalculationEntity>): RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {


    inner class ListingViewHolder(val binding: ItemListingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val binding = ItemListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val number1 = calculationList[position].firstNumber
        val number2 = calculationList[position].secondNumber
        holder.binding.item.text = when (calculationList[position].calculationType) {
            CalculationType.SUM.name -> {
                "${position+1}. $number1 + $number2 = ${number1 + number2}"
            }
            CalculationType.MINUS.name -> {
                "${position+1}. $number1 - $number2 = ${number1 - number2}"
            }
            CalculationType.DIVIDE.name -> {
                "${position+1}. $number1 / $number2 = ${number1 / number2}"
            }
            CalculationType.MULTIPLE.name -> {
                "${position+1}. $number1 * $number2 = ${number1 * number2}"
            }
            else -> {
                "0"
            }
        }
    }

    override fun getItemCount(): Int = calculationList.size
}