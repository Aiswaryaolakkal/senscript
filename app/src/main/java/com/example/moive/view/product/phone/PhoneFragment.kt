package com.example.moive.view.product.phone

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.moive.BR
import com.example.moive.R
import com.example.moive.base.BaseFragment
import com.example.moive.databinding.FragmentPhoneBinding
import com.example.moive.view.model.Product
import com.example.moive.view.product.phone.adapter.ImageAdapter
import com.example.moive.view.product.phone.adapter.PhoneListAdapter
import com.example.moive.view.product.phone.adapter.TendingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneFragment : BaseFragment<FragmentPhoneBinding, PhoneViewModel>(),
    PhoneListAdapter.ItemClickListener,TendingAdapter.ItemClickListener {
    private var _binding: FragmentPhoneBinding? = null
    private val vm: PhoneViewModel by viewModels()
    lateinit var viewPagerAdapter: ImageAdapter
    private val adapter: PhoneListAdapter by lazy {
        PhoneListAdapter(requireContext(),pdtList,this)
    }
    private val trendAdapter: TendingAdapter by lazy {
        TendingAdapter(requireContext(),pdtList,this)
    }
    var pdtList : ArrayList<Product> = ArrayList()
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
        binding.adapter1 = adapter
        binding.adapter2 = trendAdapter

    }

    override fun setupObservers() {
        vm.productList.observeForever {
            pdtList.addAll(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     override fun onItemclicked(item:Product) {
    }

    override fun onTrendItemclicked(item: Product) {
        TODO("Not yet implemented")
    }
}