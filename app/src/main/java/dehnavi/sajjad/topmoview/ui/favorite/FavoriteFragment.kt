package dehnavi.sajjad.topmoview.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.topmoview.databinding.FragmentFavoriteBinding
import dehnavi.sajjad.topmoview.ui.favorite.adapters.FavoriteAdapter
import dehnavi.sajjad.topmoview.ui.home.HomeFragmentDirections
import dehnavi.sajjad.topmoview.utils.initRecycler
import dehnavi.sajjad.topmoview.utils.showGone
import dehnavi.sajjad.topmoview.viewmodel.FavoriteViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentFavoriteBinding

    //other
    @Inject
    lateinit var adapter: FavoriteAdapter

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cal database
        viewModel.getAllMoise()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.favoriteMovies.observe(viewLifecycleOwner) { list ->
                adapter.setData(list)

                //click
                adapter.setOnItemClickListener {
                    val directions = FavoriteFragmentDirections.actionToDetailFragment(it.id)
                    findNavController().navigate(directions)
                }

                recycleFavorite.initRecycler(LinearLayoutManager(requireContext()), adapter)
            }

            viewModel.empty.observe(viewLifecycleOwner) { isEmpty ->
                lytEmpty.showGone(isEmpty)
            }

        }
    }
}