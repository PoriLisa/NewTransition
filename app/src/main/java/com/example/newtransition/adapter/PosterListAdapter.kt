package com.example.newtransition.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newtransition.R
import com.example.newtransition.model.Article
import kotlinx.android.synthetic.main.item_poster.view.*

 class PosterListAdapter(
    var context: Context,
    private var postlist: List<Article?>?
) : RecyclerView.Adapter<PosterListAdapter.PosterviewHolder>() {


    class PosterviewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    lateinit var onClickNewsList: OnClickNewsList
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PosterListAdapter.PosterviewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false);
        return PosterviewHolder(view)
    }

    override fun getItemCount(): Int {
        return postlist!!.size
    }

    override fun onBindViewHolder(holder: PosterListAdapter.PosterviewHolder, position: Int) {
        val details = this.postlist?.get(holder.adapterPosition)

        holder.itemView.item_poster_title.text = details?.getTitle()
        holder.itemView.item_poster_running_time.text = details?.getAuthor()
        Log.d(TAG, "onBindViewHolder: "+details?.getUrl())
        //drawItemUI(holder,details,context)
        binddata(details,holder,context)

        /*fun bind(dog: Article) {
            with(holder.itemView) {
                Glide
                    .with(context)
                    .load(details?.getUrlToImage())
                    .into(holder.itemView.item_poster_post);
                ViewCompat.setTransitionName(holder.itemView.item_container, details?.getUrlToImage())

                holder.itemView.setOnClickListener {
                    if (details != null) {
                        onClickNewsList.onitemlist(details)
                    }
                }
            }
        }*/

    }

     private fun binddata(details: Article?, holder: PosterListAdapter.PosterviewHolder, context: Context) {
         with(holder.itemView) {
             Glide
                 .with(context)
                 .load(details?.getUrlToImage())
                 .into(holder.itemView.item_poster_post);
             ViewCompat.setTransitionName(holder.itemView.item_container, details?.getUrlToImage())

             holder.itemView.setOnClickListener {
                 if (details != null) {
                     //context(), binding.itemContainer, data
                     onClickNewsList.onitemlist(context,holder.itemView.item_container,details)
                 }
             }
         }
     }

     fun setListner(listner: OnClickNewsList) {
        this.onClickNewsList = listner

    }

    public interface OnClickNewsList {
        fun onitemlist(
            article: Context,
            itemContainer: CardView,
            details: Article
        )
    }
}