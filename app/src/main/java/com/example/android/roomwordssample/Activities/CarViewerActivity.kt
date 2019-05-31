package com.example.android.roomwordssample.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.R
import kotlinx.android.synthetic.main.activity_car_viewer.*
import kotlinx.android.synthetic.main.activity_car_viewer.brandc
import kotlinx.android.synthetic.main.recyclercars.*

class CarViewerActivity : AppCompatActivity() {

    //var carInfo = Car(,"","",0,"", 0 , "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_viewer)

        val dlc: Bundle = this.intent.extras.getBundle("bundle")

        var car : Car = dlc.getParcelable("object")



        brandc.text = "Brand: " + car.brand
        modelc.text = "Model: " + car.model
        yearc.text = "Year of manufacturing: " + car.year
        infoc.text = "Information about the car: " + car.Info
        pricec.text = "Price: $" + car.price
        specsc.text = "Specs: " + car.specs


    }

}
