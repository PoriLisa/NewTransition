package com.example.newtransition.ui

import android.graphics.Color
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newtransition.R
import com.example.newtransition.databinding.ActivityNewDetailsActvityBinding
import com.example.newtransition.model.Article
import com.example.newtransition.utill.applyMaterialTransform
import com.example.newtransition.viewmodel.NewsDetailsViewModelFactory
import com.example.newtransition.viewmodel.NewsDetailsViewmodel
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialArcMotion
import kotlinx.android.synthetic.main.activity_new_details_actvity.*


class NewDetailsActvity : AppCompatActivity() {
    lateinit var detailsActvityBinding: ActivityNewDetailsActvityBinding
    lateinit var viewModel: NewsDetailsViewmodel
    lateinit var article: Article
    lateinit var sharedElementEnterTransition: MaterialContainerTransform
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*   sharedElementEnterTransition = MaterialContainerTransform().apply {

               duration = 300L
               isElevationShadowEnabled = true
               //setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
           }
          */
        article = (intent.getSerializableExtra("article") as? Article)!!
        applyMaterialTransform(article.getAuthor()!!)
        detailsActvityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_new_details_actvity)
        initbinding()
        getextradata()
        btnonClick()
    }

    private fun btnonClick() {

        detailsActvityBinding.fabMore.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                detailsActvityBinding.coDetails,
                getTransformdata(it, detailsActvityBinding.detailPlot)
            )
            detailsActvityBinding.detailPlot.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        detailsActvityBinding.detailPlot.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                detailsActvityBinding.coDetails,
                getTransformdata(it, detailsActvityBinding.fabMore)
            )
            detailsActvityBinding.fabMore.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

    }

    private fun getTransformdata(mStartView: View, mEndView: View): Transition? {

        return com.google.android.material.transition.platform.MaterialContainerTransform().apply {
            startView = mStartView
            endView = mEndView
            addTarget(mEndView)
            pathMotion = MaterialArcMotion()
            duration = 550
            scrimColor = Color.TRANSPARENT
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getextradata() {


        Glide
            .with(this)
            .load(article.getUrlToImage())
            .into(iv_news_details);
        detail_plot_title.text = article.getTitle()
        tv_details.text = article.getDescription()
        detail_plot_description.text = article.getContent()


    }

    private fun initbinding() {
        viewModel = ViewModelProvider(
            this,
            NewsDetailsViewModelFactory(this)
        )[NewsDetailsViewmodel::class.java]
        detailsActvityBinding.lifecycleOwner = this;
        val toolbar: Toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }
}