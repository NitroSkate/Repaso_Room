package com.example.android.roomwordssample.Fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.Adapter.CarListAdapter
import com.example.android.roomwordssample.Adapter.WordListAdapter
import com.example.android.roomwordssample.Entities.Car

import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.ViewModel.CarViewModel
import com.example.android.roomwordssample.ViewModel.WordViewModel
import kotlinx.android.synthetic.main.content_main.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Info_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Info_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Info_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var carViewModel: CarViewModel
    private lateinit var adapter : CarListAdapter
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info_, container, false)
        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
        initRecycler(resources.configuration.orientation, view)
        return view
    }

    fun initRecycler(orientation: Int, container: View){
        val linearlayoutmanager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            adapter = CarListAdapter({car : Car -> listener?.clickportrait(car)})
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            adapter = CarListAdapter({car : Car -> listener?.clicklandscape(car)})
        }

        container.recyclerview.adapter = adapter as CarListAdapter

        carViewModel.allCars.observe(this, Observer {cars ->
            cars?.let { adapter.setCars(it) }

        })

        container.recyclerview.apply{
            setHasFixedSize(true)
            layoutManager = linearlayoutmanager
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun clickportrait(car: Car)
        fun clicklandscape(car: Car)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Info_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() : Info_Fragment{
            var fragment = Info_Fragment()
            return fragment
        }
    }
}
