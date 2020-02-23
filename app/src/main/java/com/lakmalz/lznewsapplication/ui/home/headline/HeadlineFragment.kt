package com.lakmalz.lznewsapplication.ui.home.headline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.data.models.Article
import com.lakmalz.lznewsapplication.ui.base.BaseFragment
import com.lakmalz.lznewsapplication.ui.home.headline.AdapterNewsList.ItemClickListener
import com.lakmalz.lznewsapplication.util.DEFAULT_COUNTRY
import com.lakmalz.lznewsapplication.util.getVisibility
import kotlinx.android.synthetic.main.fragment_headline.*


class HeadlineFragment : BaseFragment(), ItemClickListener {

    private var mLayoutmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private lateinit var mAdapter: AdapterNewsList

    private val mViewModel: HeadlinesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HeadlinesViewModel::class.java]
    }

    companion object {
        fun newInstance() = HeadlineFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_headline, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        mViewModel.fetchHeadlines(DEFAULT_COUNTRY)
        observerProgress()
        observerNoInternet()
        observerError()
        observeHeadline()
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

        /*rv_list!!.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = mLayoutmanager.childCount
                    totalItemCount = mLayoutmanager.itemCount
                    pastVisibleItemCount = (rv_list.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    if (!isLoading) {
                        if (visibleItemCount + pastVisibleItemCount >= totalItemCount) {
                            isLoading = true
                            pageId ++
                            mViewModel.fetchHeadlines()
                        }
                    }
                }
            }
        })*/

    }

    private fun observerProgress() {
        mViewModel.getProgress().observe(viewLifecycleOwner, Observer {
            progress.visibility = it.getVisibility()
        })
    }

    private fun observerNoInternet() {
        mViewModel.noInternetError().observe(viewLifecycleOwner, Observer {
            showMessage("No Internet.", "Please check your internet settings.")
        })
    }

    private fun observerError() {
        mViewModel.getErrors().observe(viewLifecycleOwner, Observer {
            showMessage()
        })
    }

    private fun observeHeadline() {
        mViewModel.getHeadlinesLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mAdapter.addList(it.articles)
            }
        })
    }

    override fun onItemClick(position: Int, item: Article) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }
}