package dehnavi.sajjad.topmoview.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.topmoview.databinding.FragmentSearchBinding
import dehnavi.sajjad.topmoview.ui.home.adaters.MoviesAdapter
import dehnavi.sajjad.topmoview.utils.initRecycler
import dehnavi.sajjad.topmoview.utils.showGone
import dehnavi.sajjad.topmoview.viewmodel.SearchViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSearchBinding

    //other
    @Inject
    lateinit var adapter: MoviesAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            searchEdt.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    viewModel.searchMovie(search)
                }
            }

            //get Search
            viewModel.search.observe(viewLifecycleOwner) { response ->
                adapter.setData(response.data)

                //click
                adapter.setOnItemClickListener {
                    val directions =
                        SearchFragmentDirections.actionToDetailFragment(it.id!!.toInt())
                    findNavController().navigate(directions)
                }

                recycleSearch.initRecycler(
                    LinearLayoutManager(requireContext()), adapter
                )
            }

            viewModel.loading.observe(viewLifecycleOwner) { isShow ->
                recycleSearch.showGone(!isShow)
                loading.showGone(isShow)
            }

            viewModel.empty.observe(viewLifecycleOwner) { isEmpty ->
                lytEmpty.showGone(isEmpty)
                loading.showGone(false)
            }
        }
    }

}