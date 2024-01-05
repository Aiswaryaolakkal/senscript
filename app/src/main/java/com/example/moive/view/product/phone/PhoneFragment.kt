package com.example.moive.view.product.phone

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.moive.BR
import com.example.moive.R
import com.example.moive.base.BaseFragment
import com.example.moive.databinding.FragmentPhoneBinding
import com.example.moive.view.model.Product
import com.example.moive.view.product.phone.adapter.ImageAdapter
import com.example.moive.view.product.phone.adapter.TrendAdapter
import com.example.moive.view.product.phone.adapter.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class PhoneFragment : BaseFragment<FragmentPhoneBinding, PhoneViewModel>(),
    TrendAdapter.ItemClickListener,TrailerAdapter.ItemClickListener {
    private var _binding: FragmentPhoneBinding? = null
    private val vm: PhoneViewModel by viewModels()
    lateinit var viewPagerAdapter: ImageAdapter
    var currentPage:Int = 0;
    lateinit var timer:Timer;
    val  DELAY_MS:Long = 500;//delay in milliseconds before task is to be executed
    val  PERIOD_MS :Long= 3000
    private val trendAdapter: TrendAdapter by lazy {
        TrendAdapter(requireContext(),pdtList,this)
    }
    private val trailerAdapter: TrailerAdapter by lazy {
        TrailerAdapter(requireContext(),pdtList,this)
    }
    var pdtList : ArrayList<Product> = ArrayList()
    var pdtListCopy : ArrayList<Product> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.fetchAlbumList()
//        vm.fetchMoiveList()
//        vm.fetchProductList()
        setupObservers()

    }
    override fun getBindingVariable() = BR.viewModel
    override fun getViewmodel(): PhoneViewModel = vm

    override fun getLayoutId(): Int = R.layout.fragment_phone

    override fun initLayout() {
        val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        viewPagerAdapter = ImageAdapter(requireContext(), images)

        // on below line we are setting
        // adapter to our view pager.
        binding.viewPager.adapter = viewPagerAdapter
        binding.adapter1 = trendAdapter
      binding.rvTrailerList.adapter = trailerAdapter
        rotateSlider()

        binding.searchvw.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    pdtList.clear()
                    pdtList.addAll(pdtListCopy.filter {
                        it.title.lowercase().contains(newText.lowercase())

                    })
                    trailerAdapter.notifyDataSetChanged()
                }
                return false
            }

        })

    }

    override fun setupObservers() {
        vm.productList.observeForever {
            pdtList.addAll(it)
            pdtListCopy.addAll(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun rotateSlider(){
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === 3 - 1) {
                currentPage = 0
            }
            binding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread

        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }
     override fun onItemclicked(item:Product) {
    }

    override fun onTrendItemclicked(item: Product) {
        TODO("Not yet implemented")
    }
}