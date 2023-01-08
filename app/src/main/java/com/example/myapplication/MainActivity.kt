package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var tvSelectedDate:TextView?=null
    private var tvSelectedMinute:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate=findViewById(R.id.Selected_date)
        tvSelectedMinute=findViewById(R.id.Select_minute)
        val btmselectDate :Button = findViewById(R.id.button)
        btmselectDate.setOnClickListener {

            onSlectedDate()
        }
    }
    fun onSlectedDate() {
//        get the current date
        val mycal = Calendar.getInstance()
        val cYear = mycal.get(Calendar.YEAR)
        val cMonth= mycal.get(Calendar.MONTH)
        val cDays = mycal.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog ( this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
                Toast.makeText(this," $dayofMonth, ${month+1} ,$year",Toast.LENGTH_LONG).show()
           val selectedDate="$dayofMonth/${month+1}/$year"
                tvSelectedDate?.text = selectedDate
                val sdf=SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val bDate = sdf.parse(selectedDate);
                bDate?.let {
                    val bDateinMinInMilli = bDate.time
                    val bDateInMin = bDateinMinInMilli / 60000

                    val currdate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currdate?.let {
                        val currDateInMin = currdate.time/ 60000
                        val ageInMin = currDateInMin - bDateInMin
                        tvSelectedMinute?.text = ageInMin.toString()
                    }
                }
            },
        cYear,cMonth,cDays )
   dpd.datePicker.maxDate=System.currentTimeMillis()- 86400000
       dpd.show()
    }
}
