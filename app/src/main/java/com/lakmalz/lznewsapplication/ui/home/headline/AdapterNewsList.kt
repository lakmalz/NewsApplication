package com.lakmalz.lznewsapplication.ui.home.headline

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.data.models.Article
import com.lakmalz.lznewsapplication.util.Utils
import com.lakmalz.lznewsapplication.util.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_headline.view.*

class AdapterNewsList() : RecyclerView.Adapter<AdapterNewsList.HeadlineItemViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    private var data: ArrayList<Article> = ArrayList()

    fun addList(list: List<Article>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeadlineItemViewHolder(parent)

    override fun onBindViewHolder(holder: HeadlineItemViewHolder, position: Int) =
        holder.bind(data[position])

    inner class HeadlineItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_headline)) {
        fun bind(item: Article) = with(itemView) {
            Picasso.get()
                .load(item.urlToImage)
                .placeholder(R.drawable.placeholder_news)
                .error(R.drawable.placeholder_news)
                .into(img_article)

            txt_description.text = item.description
            txt_title.text = item.description
            if (!item.author.isNullOrEmpty()) {
                txt_author.text = "Written by : ${item.author}"
            }
            if (!item.publishedAt.isNullOrEmpty()) {
                txt_published.text = Utils.getFormatedDatewithTwoDecomalNormalForDashSeparatedFormat(item.publishedAt)
            }
            this.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition, item)
            }
        }
    }

    interface ItemClickListener{
        fun onItemClick(position: Int, item: Article)
    }
}