package com.nasa.demo.assignment.ui.fragment

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.databinding.FragmentImageBinding
import com.nasa.demo.assignment.ui.viewmodel.MainViewModel
import com.nasa.demo.assignment.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        viewModel.getImage().observe(viewLifecycleOwner) {
            it?.let {
                bindData(it)
            }
        }
    }

    private fun bindData(imageResponse: ImageResponse) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        with(binding) {
            titleTextView.text = imageResponse.title ?: ""
            dateTextView.text = DateUtils.formatDate(imageResponse.date) ?: ""
            explanationTextView.text = imageResponse.explanation ?: ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                explanationTextView.justificationMode = LineBreaker.JUSTIFICATION_MODE_NONE
            }
            val copyrightSymbolCodePoint = 169
            val s = Character.toString(copyrightSymbolCodePoint.toChar())
            copyrightTextView.text = "Copyright $s ${imageResponse.copyright ?: "Unknown"}"
            Glide.with(imageView).load(imageResponse.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .into(imageView)
        }
    }

}