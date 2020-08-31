package com.example.newtransition.ui

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newtransition.R
import com.example.newtransition.adapter.PosterListAdapter
import com.example.newtransition.databinding.ActivityMainBinding
import com.example.newtransition.model.Article
import com.example.newtransition.viewmodel.MainViewModel
import com.example.newtransition.viewmodel.ViewmodelFactory
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PosterListAdapter.OnClickNewsList {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var posterListAdapter: PosterListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //b0af0c0f85d54286a55e78d7f21cc9ca

        initbinding()
        setObseravable()
        btnOnclick()
    }

    private fun btnOnclick() {
        binding.fab.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                binding.itemContainer,
                getTransformdata(it, binding.card)
            )
            binding.card.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        binding.card.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                binding.itemContainer,
                getTransformdata(it, binding.fab)
            )
            binding.fab.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
    }

    private fun getTransformdata(mStartView: View, mEndView: View): Transition? {

        return MaterialContainerTransform().apply {
            startView = mStartView
            endView = mEndView
            addTarget(mEndView)
            pathMotion = MaterialArcMotion()
            duration = 550
            scrimColor = Color.TRANSPARENT
        }

    }

    private fun setObseravable() {
        viewModel.posterListLiveData().observe(this, Observer { postlist ->
            Log.d("TAG", "setObseravable: " + postlist)
            posterListAdapter = PosterListAdapter(this, postlist.getArticles())
            posterListAdapter.setListner(this)
            rv_newlist.adapter = posterListAdapter
        })

    }

    private fun initbinding() {
        viewModel = ViewModelProvider(this, ViewmodelFactory(this))[MainViewModel::class.java]
        binding.lifecycleOwner = this;
        viewModel.getpostlist()

    }

    override fun onitemlist(
        context: Context,
        itemContainer: CardView,
        article: Article
    ) {

        TransitionManager.beginDelayedTransition(
            binding.itemContainer,
            getTransform()
        )
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            itemContainer, article.getTitle()
        )
        this.startActivity(intent, options.toBundle())
        val intent = Intent(this, NewDetailsActvity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }

    private fun getTransform(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            /* startView = mStartView
             endView = mEndView
             addTarget(mEndView)*/
            pathMotion = MaterialArcMotion()
            duration = 550
            scrimColor = Color.TRANSPARENT
        }
    }
}