package com.wysong70.vottery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wysong70.vottery.databinding.FragmentMyResultListBinding

class FragmentMyResultList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMyResultListBinding.inflate(
            inflater,
            container,
            false
        ).apply {

        }.root
    }
}