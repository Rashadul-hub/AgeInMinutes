package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnDatePicker).setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view : View){

        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)

        //Date Picking For calculating the age in minutes

        val pickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The Chosen year is $selectedYear, The Month is $selectedMonth" +
                    "& Day is $selectedDayOfMonth",Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = simpleDateFormat.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvSelectedDateInMinutes.text = differenceInMinutes.toString()

        },year,month,day )

        pickerDialog.datePicker.maxDate = Date().time - 86400000 //86400000 is the milli seconds of 1 day
        pickerDialog.show()


    }

}