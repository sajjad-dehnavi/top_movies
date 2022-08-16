package dehnavi.sajjad.topmoview.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.topmoview.R
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.databinding.FragmentDetailBinding
import dehnavi.sajjad.topmoview.ui.detail.adapters.ImagesAdapter
import dehnavi.sajjad.topmoview.utils.initRecycler
import dehnavi.sajjad.topmoview.utils.showGone
import dehnavi.sajjad.topmoview.viewmodel.DetailViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentDetailBinding

    //other
    @Inject
    lateinit var adapter: ImagesAdapter

    @Inject
    lateinit var entity: MovieEntity

    private val viewModel: DetailViewModel by viewModels()


    private var movieId: Int = 0
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get data
        movieId = args.id
        //call api
        if (movieId > 0)
            viewModel.getMovieDetail(movieId)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.movieDetail.observe(viewLifecycleOwner) { response ->
                nameMovie.text = response.title
                txtRate.text = response.imdbRating
                txtYear.text = response.year
                txtTimeMovie.text = response.runtime
                txtSummery.text = response.plot
                txtActors.text = response.actors

                bigPoster.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }

                imgPoster.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }


                adapter.differ.submitList(response.images)

                recycleActors.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    adapter
                )


                btnFavorite.setOnClickListener {
                    entity.id = movieId
                    entity.title = response.title.toString()
                    entity.country = response.country.toString()
                    entity.year = response.year.toString()
                    entity.poster = response.poster.toString()
                    entity.genres = response.genres.toString()

                    viewModel.favoriteMovie(movieId, entity)
                }
            }

            viewModel.loading.observe(viewLifecycleOwner) { isShow ->
                loading.showGone(isShow)
                scrollDetail.showGone(!isShow)
            }

            lifecycleScope.launchWhenCreated {
                if (viewModel.existFavoriteMovie(movieId)) {
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.scarlet
                        )
                    )
                } else {
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.philippineSilver
                        )
                    )
                }
            }

            viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
                if (isFavorite) {
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.scarlet
                        )
                    )
                } else {
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.philippineSilver
                        )
                    )
                }
            }

            btnBack.setOnClickListener { findNavController().navigateUp() }

        }
    }
}