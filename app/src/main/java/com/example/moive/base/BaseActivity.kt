package com.example.moive.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivit<T : ViewDataBinding, V : ViewModel>():AppCompatActivity() {
    private var nViewModel: V? = null
    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,getLayoutId())
        nViewModel = getViewModel()

        binding.setVariable(getBindingVariable(),nViewModel)
        binding.lifecycleOwner= this
        binding.executePendingBindings()

        setupParameters()
        initLayout()
    }

    /**
    * Method to get Binding Variable for setting viewmodel
    */
    abstract fun getBindingVariable(): Int

    /**
    * Method for set viewmodel
    */
    abstract fun getViewModel(): V

    /**
    * Method used to get layout ID of Activiity
    *  You need to return the corresponding layout id in this method
    *
    */
    abstract fun getLayoutId(): Int

    /**
    * Method used to initialize the UI
    *   You need to call you this function on your Activity, this will call in the base Activities onCreateView Method
    */
    abstract fun initLayout()

    /**
    * Method used to get argument from incoming intent
    * You don't need to call this from your, it already called from base  activity
    */
    abstract fun setupParameters()

    /**
    * This method should be used to setup the LiveData observers from the respective ViewModel of the Fragment
    * You should also call this method from the onCreate method
    */
    abstract fun setupObserver()


}
