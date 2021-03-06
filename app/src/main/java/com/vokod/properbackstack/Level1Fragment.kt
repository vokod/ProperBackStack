package com.vokod.properbackstack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class Level1Fragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val bundle by lazy { Level1FragmentArgs.fromBundle(requireArguments()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_level1, container, false)

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        view.findViewById<Button>(R.id.btn)?.setOnClickListener {
            findNavController().navigate(
                Level1FragmentDirections.actionLevel1FragmentToLevel2Fragment(
                    word = "Greetings_from_level_1",
                    number = bundle.number
                )
            )
        }

        view.findViewById<TextView>(R.id.tvNumber).text = bundle.number.toString()

        return view
    }
}