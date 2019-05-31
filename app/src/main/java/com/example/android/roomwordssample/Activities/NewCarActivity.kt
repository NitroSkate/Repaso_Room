package com.example.android.roomwordssample.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.ViewModel.CarViewModel

/**
 * Activity for entering a word.
 */

class NewCarActivity : AppCompatActivity() {

    //private lateinit var editWordView: EditText
    //private lateinit var editWordView: EditText
    private lateinit var carViewModel: CarViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car)
        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
        val editBrandView = findViewById<EditText>(R.id.edit_brand)
        val editModelView = findViewById<EditText>(R.id.edit_model)
        val editYearView = findViewById<EditText>(R.id.edit_year)
        val editInfoView = findViewById<EditText>(R.id.edit_info)
        val editPriceView = findViewById<EditText>(R.id.edit_price)
        val editSpecView = findViewById<EditText>(R.id.edit_specs)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editBrandView.text) || TextUtils.isEmpty(editModelView.text) || TextUtils.isEmpty(editPriceView.text)
                    ||  TextUtils.isEmpty(editInfoView.text) || TextUtils.isEmpty(editYearView.text) || TextUtils.isEmpty(editSpecView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this,"Car not saved", Toast.LENGTH_SHORT).show()
            }
            else if (editPriceView.text.toString().toInt() < 0 || (editYearView.text.toString().toInt() < 0) || editYearView.text.toString().toInt() > 2021 ){
                Toast.makeText(this,"Car not saved", Toast.LENGTH_SHORT).show()
            }
            else {
                val car = Car(editBrandView.text.toString(), editModelView.text.toString(),
                        editYearView.text.toString().toInt(), editInfoView.text.toString(),
                        editPriceView.text.toString().toInt(), editSpecView.text.toString())
                carViewModel.insert(car)
                /*val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)*/

            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}