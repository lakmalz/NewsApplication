package com.lakmalz.lznewsapplication.ui.home.customnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.data.models.Article
import com.lakmalz.lznewsapplication.data.models.User
import com.lakmalz.lznewsapplication.util.getVisibility
import com.lakmalz.lznewsapplication.ui.base.BaseFragment
import com.lakmalz.lznewsapplication.ui.home.headline.AdapterNewsList
import com.lakmalz.lznewsapplication.util.PaginationListener
import kotlinx.android.synthetic.main.fragment_custom_list.*
import kotlinx.android.synthetic.main.fragment_custom_list.progress
import kotlinx.android.synthetic.main.fragment_custom_list.rv_list
import kotlinx.android.synthetic.main.fragment_headline.*

class CustomNewsFragment : BaseFragment(), AdapterNewsList.ItemClickListener {

    private lateinit var mUser: User
    private var mLayoutmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private lateinit var mAdapter: AdapterNewsList

    private val mViewModel: CustomNewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CustomNewsViewModel::class.java]
    }

    companion object {
        fun newInstance(): CustomNewsFragment {
            val fragment = CustomNewsFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_custom_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        mUser = mViewModel.getUserData()
        if (mUser != null) {
            mViewModel.fetchCustomNewsList(mUser.selectedKeyword)
        }
        observerNoInternet()
        observerError()
        observerProgress()
        observeCustomNews()
    }

    private fun initUI() {
        mAdapter = AdapterNewsList()
        rv_list.adapter = mAdapter
        rv_list.layoutManager = mLayoutmanager
        mAdapter?.let {
            it.itemClickListener = this
        }
        val offsetPx = dpToPx(16)
        rv_list.addItemDecoration(StartOffsetItemDecoration(offsetPx))
        rv_list.addItemDecoration(EndOffsetItemDecoration(offsetPx))
        val dividerDrawable = ContextCompat.getDrawable(context!!, R.drawable.shap_divider)
        rv_list.addItemDecoration(DividerItemDecoration(dividerDrawable))

        /*rv_list.addOnScrollListener(PaginationListener(mLayoutmanager){

        })*/
    }

    private fun observerProgress() {
        mViewModel.getProgress().observe(viewLifecycleOwner, Observer {
            progress.visibility = it.getVisibility()
        })
    }

    private fun observerNoInternet() {
        mViewModel.noInternetError().observe(viewLifecycleOwner, Observer {
            showMessage(getString(R.string.no_internet), getString(R.string.no_internet_message))
        })
    }

    private fun observerError() {
        mViewModel.getErrors().observe(viewLifecycleOwner, Observer {
            showMessage()
        })
    }

    private fun observeCustomNews() {
        mViewModel.getCustomNewListLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mAdapter.addList(it.articles)
            }
        })
    }

    override fun onItemClick(position: Int, item: Article) {

    }

}