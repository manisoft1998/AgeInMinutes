package com.org.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date_btn.setOnClickListener { view ->
            callDatePickerDialog(view)
        }
    }

    private fun callDatePickerDialog(view: View) {
        val myCalendar = Calendar.getInstance()
        val YEAR = myCalendar.get(Calendar.YEAR)
        val MONTH = myCalendar.get(Calendar.MONTH)
        val DAY = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { datePicker, year, month, day ->

            // selected date from date picker
            val selectedValue = "${day}/${month + 1}/${year}"
            selected_date_tv.text = selectedValue

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //convert selected date to minutes
            val dateObject = sdf.parse(selectedValue)
            val selectedDateInMinutes = dateObject!!.time / 60000

            //convert current date to minutes
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000

            //calculate the diffrs
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            age_in_min_tv.text = differenceInMinutes.toString()

        }, YEAR, MONTH, DAY)

        //disable future dates
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}