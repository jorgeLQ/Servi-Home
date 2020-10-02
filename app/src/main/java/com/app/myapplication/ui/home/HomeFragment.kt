package com.app.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.myapplication.MapsActivity
import com.app.myapplication.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //val tv: TextView = view.findViewById(R.id.textViewAmount)
        //val textView: TextView = root.findViewById(R.id.text_home)

       val btn: Button = view.findViewById(R.id.buttonMap)
        btn.setOnClickListener {
            val verMapa = Intent(activity, MapsActivity::class.java)
            startActivity(verMapa)
        }
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //0textView.text = it
        })
        return view

    }

}