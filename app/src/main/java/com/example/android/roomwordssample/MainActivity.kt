package com.example.android.roomwordssample

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android.roomwordssample.Activities.CarViewerActivity
import com.example.android.roomwordssample.Activities.NewCarActivity
import com.example.android.roomwordssample.Activities.NewWordActivity
import com.example.android.roomwordssample.Adapter.WordListAdapter
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.Entities.Word
import com.example.android.roomwordssample.Fragments.Info_Fragment
import com.example.android.roomwordssample.Fragments.List_Fragment
import com.example.android.roomwordssample.ViewModel.CarViewModel
import com.example.android.roomwordssample.ViewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_car_viewer.*
import kotlinx.android.synthetic.main.fragment_list_.*

class MainActivity : AppCompatActivity(), Info_Fragment.OnFragmentInteractionListener {



    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel
    private lateinit var carViewModel: CarViewModel
    private lateinit var listfragment : Info_Fragment
    private lateinit var contentfragment : List_Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)

        /*val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })*/



        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            /*val intent = Intent(this@MainActivity, NewCarActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)*/
            val intent = Intent(this@MainActivity, NewCarActivity::class.java)
            startActivity(intent)
        }

        initfragment()
    }
    override fun clicklandscape(car: Car) {
        Toast.makeText(this, car.model.toString(), Toast.LENGTH_SHORT).show()
        //contentfragment = List_Fragment.newInstance() Existe doble instacia y lo toma por nulo
        //changefragment(R.id.info, contentfragment)
        carViewModel.allCars.observe(this, Observer { cars ->
            cars?.let { contentfragment.apply {
                tv_brand.text = "Brand: " + it[car.id].brand
                tv_model.text = "Model: " + it[car.id].model
                tv_year.text =  "Year of manufacturing: " + it[car.id].year.toString()
                tv_info.text = "Info about the car: " + it[car.id].Info
                tv_price.text ="Price: $" + it[car.id].price.toString()
                tv_specs.text = "Specs: " + it[car.id].specs
            } }
        })


    }

    override fun clickportrait(car: Car) {
        var carro = Car(0,"","",0,"",0,"")
        carViewModel.allCars.observe(this, Observer { cars ->
            cars?.let { carro = Car(it[car.id].id, it[car.id].brand, it[car.id].model, it[car.id].year,
                    it[car.id].Info, it[car.id].price,it[car.id].specs)
            }
        })

        var content = Bundle()

        content.putParcelable("object", carro)
        if(carro.model == null){
            Log.d("alv", carro.model)
            //Toast.makeText(this, carro.model, Toast.LENGTH_SHORT).show()
        }
        else {
            startActivity(Intent(this, CarViewerActivity::class.java).putExtra("bundle", content))
        }


    }
    fun initfragment(){
        listfragment = Info_Fragment.newInstance()

        val id = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
         R.id.lmain
        }
        else {
            contentfragment = List_Fragment.newInstance()
            changefragment(R.id.info, contentfragment)

            R.id.smain
        }
        changefragment(id, listfragment)

    }

    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }*/
}
