package com.example.myapplication

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    private var minute = 0
    private var hour = 0
    private var dayOfMonth = 0
    private var month = 0
    private var year = 0

    lateinit var alarmManager: AlarmManager
    lateinit var alarmIntent: PendingIntent



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmIntent = PendingIntent.getBroadcast(applicationContext,0,
        Intent(applicationContext,AlarmReceiver::class.java),0
        )

        timePicker.setOnClickListener {
            val dialog = MyTimePickerDialog()
            dialog.show(supportFragmentManager, "time_picker")
        }

        datePicker.setOnClickListener {
            val dialog = MyDatePickerDialog()
            dialog.show(supportFragmentManager, "date_picker")
        }


show.setOnClickListener {


    val date = java.util.Calendar.Builder()
        .setDate(year, month, dayOfMonth)
        .setTimeOfDay(hour, minute, 0)
        .build()

    Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT).show()

        alarmManager.set(AlarmManager.RTC_WAKEUP, date.timeInMillis, alarmIntent)
}
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.minute = minute
        this.hour=hourOfDay


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.dayOfMonth=dayOfMonth
        this.month=month
        this.year=year

    }
}
