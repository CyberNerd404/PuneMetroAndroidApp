package com.cypherx.punemetro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cypherx.punemetro.R
import com.cypherx.punemetro.data.model.StationListDTO
import com.cypherx.punemetro.db.entities.Station

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val autoSource : AutoCompleteTextView = root.findViewById(R.id.sourceEditText)
        val autoDestination : AutoCompleteTextView = root.findViewById(R.id.destinationEditText)
        val searchMetro : Button = root.findViewById(R.id.searchmetroButtom)
        val recyclerView = root.findViewById(R.id.recycleViewSearchMetro) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val station = ArrayList<StationListDTO>()
        station.add(StationListDTO("Coming soon","Metro"))
        station.add(StationListDTO("Coming soon","Metro"))
        station.add(StationListDTO("Coming soon","Metro"))
        station.add(StationListDTO("Coming soon","Metro"))
        station.add(StationListDTO("Coming soon","Metro"))
        station.add(StationListDTO("Coming soon","Metro"))


        val stations = resources.getStringArray(R.array.Stations)

        val adapter= ArrayAdapter<String>(context!!.applicationContext,android.R.layout.simple_dropdown_item_1line,stations)

        autoSource.setAdapter(adapter)
        autoDestination.setAdapter(adapter)

        searchMetro.setOnClickListener {
            val sourceText : String = autoSource.text.toString()
            val destinationText : String = autoDestination.text.toString()

            val recycleAdapter = StationAdapter(station)
            recyclerView.adapter = recycleAdapter


        }



        return root
    }
}
