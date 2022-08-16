package dehnavi.sajjad.topmoview.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.topmoview.R
import dehnavi.sajjad.topmoview.databinding.FragmentHomeBinding
import dehnavi.sajjad.topmoview.ui.home.adaters.GenreAdapter
import dehnavi.sajjad.topmoview.ui.home.adaters.MoviesAdapter
import dehnavi.sajjad.topmoview.ui.home.adaters.TopMoviesAdapter
import dehnavi.sajjad.topmoview.utils.initRecycler
import dehnavi.sajjad.topmoview.utils.showGone
import dehnavi.sajjad.topmoview.viewmodel.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentHomeBinding

    //other
    @Inject
    lateinit var adapterTopMovie: TopMoviesAdapter

    @Inject
    lateinit var adapterGenre: GenreAdapter

    @Inject
    lateinit var adapterLastMovies: MoviesAdapter
    private val viewModel: HomeViewModel by viewModels()

    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call api
        viewModel.getListMovies()
        viewModel.getTopMovieList(1)
        viewModel.getGenreList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //get data
            viewModel.topMoviesList.observe(viewLifecycleOwner) { response ->
                adapterTopMovie.differ.submitList(response.data)

                //RecycleView
                recycleTopMovies.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    adapterTopMovie
                )
                //Indicator
                pagerHelper.attachToRecyclerView(recycleTopMovies)
                indicatorTopMovie.attachToRecyclerView(recycleTopMovies, pagerHelper)
            }
            viewModel.genreList.observe(viewLifecycleOwner) { response ->
                adapterGenre.differ.submitList(response)

                recycleGenre.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    adapterGenre
                )
            }
            viewModel.listMovies.observe(viewLifecycleOwner) { response ->
                adapterLastMovies.setData(response.data)

                //click
                adapterLastMovies.setOnItemClickListener {
                    val directions = HomeFragmentDirections.actionToDetailFragment(it.id!!.toInt())
                    findNavController().navigate(directions)
                }

                recycleLastMovie.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
                    adapterLastMovies
                )
            }
            viewModel.loading.observe(viewLifecycleOwner) { isShow ->
                loading.showGone(isShow)
                scrollHome.showGone(!isShow)
            }

        }
    }
}