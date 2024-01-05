package com.example.moive.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.moive.Utils.LoadingIndicator

abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment() {
    private var mViewModel: V? = null
    private var _binding: T? = null
    protected val binding get() = _binding!!
    lateinit var loader: LoadingIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewmodel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setVariable(getBindingVariable(), mViewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        loader = LoadingIndicator(requireContext())
        initLayout()
    }

    /**
     * Methord to get  binding variable for setting viewmodel
     */
    abstract fun getBindingVariable(): Int

    /**
     * Method for set viewmodel
     */
    abstract fun getViewmodel(): V

    /**
     * Method used to get layout ID of fragment
     */
    abstract fun getLayoutId(): Int

    /**
     * Method used to initialize UI
     * No need to call this Method from fragment,   this will call base fragment  onCreateView
     */
    abstract fun initLayout()

    /**
     *  Method should be used to setup the LiveData observer from the respective ViewModel of the  Fragment
     *  you should also call this method from onCreate method
     */

    abstract fun setupObservers()
}