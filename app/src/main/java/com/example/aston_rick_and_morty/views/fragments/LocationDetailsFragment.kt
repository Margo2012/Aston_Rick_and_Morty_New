package com.example.aston_rick_and_morty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_rick_and_morty.databinding.FragmentLocationDetailBinding
import com.example.aston_rick_and_morty.models.remote_dto.Location

class LocationDetailsFragment: Fragment() {
    private var _binding:FragmentLocationDetailBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.getParcelable<Location>(LOCATION_DETAIL_ARG)

        binding.apply {
            if (args != null) {
                locationDetailIdTextview.text = args.id.toString()
                locationDetailNameTextview.text = args.name
                locationDetailTypeTextview.text = args.type
                locationDetailDimensionTextview.text = args.dimension
                locationDetailUrlTextview.text = args.url
                locationDetailCreatedTextview.text = args.created
            }
        }
    }

    companion object {
        private const val LOCATION_DETAIL_ARG = "location_name"

        fun newInstance(location: Location) = LocationDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(LOCATION_DETAIL_ARG, location)
            }
        }
    }
}