package com.first.myapplication.mht.fragments.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.first.myapplication.mht.R
import kotlinx.android.synthetic.main.fragment_activities_home.*

class ActivitiesHomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_activities_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llMeditation.setOnClickListener {
            view.findNavController().navigate(R.id.action_activitiesHomeFragment_to_meditationFragment)
        }
    }

}