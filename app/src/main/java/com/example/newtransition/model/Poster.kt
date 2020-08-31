package com.example.newtransition.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Poster {

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("release")
    @Expose
    private var release: String? = null

    @SerializedName("playtime")
    @Expose
    private var playtime: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("plot")
    @Expose
    private var plot: String? = null

    @SerializedName("poster")
    @Expose
    private var poster: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getRelease(): String? {
        return release
    }

    fun setRelease(release: String?) {
        this.release = release
    }

    fun getPlaytime(): String? {
        return playtime
    }

    fun setPlaytime(playtime: String?) {
        this.playtime = playtime
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getPlot(): String? {
        return plot
    }

    fun setPlot(plot: String?) {
        this.plot = plot
    }

    fun getPoster(): String? {
        return poster
    }

    fun setPoster(poster: String?) {
        this.poster = poster
    }
}