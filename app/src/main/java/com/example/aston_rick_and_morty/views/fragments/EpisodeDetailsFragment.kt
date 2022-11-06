package com.example.aston_rick_and_morty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_rick_and_morty.databinding.FragmentEpisodeDetailBinding
import com.example.aston_rick_and_morty.models.remote_dto.Episode

class EpisodeDetailsFragment: Fragment() {
    private var _binding:FragmentEpisodeDetailBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.getParcelable<Episode>(EPISODE_DETAIL_ARG)

        binding.apply {
            if (args != null) {
                episodeDetailIdTextview.text = args.id.toString()
                episodeDetailNameTextview.text = args.name
                episodeDetailAirDateTextview.text = args.air_date
                episodeDetailEpisodeTextview.text = args.episode
                episodeDetailUrlTextview.text = args.url
                episodeDetailCreatedTextview.text = args.created
            }
        }
    }


    companion object {
        private const val EPISODE_DETAIL_ARG = "episode_name"

        fun newInstance(episode: Episode) = EpisodeDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EPISODE_DETAIL_ARG, episode)
            }
        }
    }
}