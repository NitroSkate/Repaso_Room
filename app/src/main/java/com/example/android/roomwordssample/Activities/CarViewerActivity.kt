package com.example.android.roomwordssample.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.R
import kotlinx.android.synthetic.main.activity_car_viewer.*

class CarViewerActivity : AppCompatActivity() {

    //var carInfo = Car(,"","",0,"", 0 , "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_viewer)

        val dlc: Bundle = this.intent.extras.getBundle("bundle")

        var car : Car = dlc.getParcelable("object")



        brand.text = car.brand

    }

}
