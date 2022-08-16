package dehnavi.sajjad.topmoview.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.topmoview.R
import dehnavi.sajjad.topmoview.databinding.FragmentRegisterBinding
import dehnavi.sajjad.topmoview.databinding.FragmentSplashBinding
import dehnavi.sajjad.topmoview.model.register.BodyRegister
import dehnavi.sajjad.topmoview.utils.StoreUserData
import dehnavi.sajjad.topmoview.viewmodel.RegisterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentRegisterBinding

    //other
    @Inject
    lateinit var storeUserData: StoreUserData

    @Inject
    lateinit var bodyRegister: BodyRegister

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSubmit.setOnClickListener {
                val name = nameEdt.text.toString()
                val email = emilEdt.text.toString()
                val password = passwordEdt.text.toString()

                if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()) {
                    bodyRegister.name = name
                    bodyRegister.email = email
                    bodyRegister.password = password


                } else {
                    Snackbar.make(it, getString(R.string.fillAllFields), Snackbar.LENGTH_SHORT)
                        .show()
                }
                //send data
                viewModel.registerUser(bodyRegister)

                viewModel.loading.observe(viewLifecycleOwner) { isShow ->
                    if (isShow) {
                        binding.submitLoading.visibility = View.VISIBLE
                        binding.btnSubmit.visibility = View.INVISIBLE
                    } else {
                        binding.btnSubmit.visibility = View.VISIBLE
                        binding.submitLoading.visibility = View.GONE
                    }
                }

                viewModel.registerUser.observe(viewLifecycleOwner) { response ->
                    lifecycle.coroutineScope.launchWhenCreated {
                        storeUserData.saveUserToken(response.name.toString())
                    }
                }
            }
        }
    }

}