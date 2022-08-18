package com.nasa.demo.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.databinding.FragmentFavoriteListBinding
import com.nasa.demo.assignment.ui.adapter.FavoriteListAdapter
import com.nasa.demo.assignment.ui.adapter.FavoriteListCallbackListener
import com.nasa.demo.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment : Fragment(), FavoriteListCallbackListener {
    private lateinit var binding: FragmentFavoriteListBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteListAdapter = FavoriteListAdapter(this)
        setupRecycleView()
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModel.getFavoriteList().observe(viewLifecycleOwner) {
            favoriteListAdapter.submitList(it)
        }
    }

    private fun setupRecycleView() {
        with(binding) {
            recycleView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = favoriteListAdapter
            }
        }
    }

    override fun onDeleteFavoriteItem(imageResponse: ImageResponse) {
        imageResponse.isFavorite = 0
        viewModel.updateFavoriteStatus(imageResponse)
    }

}