package com.example.newtransition.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Source: Serializable {
    @SerializedName("id")
    @Expose
    private var id: Any? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getId(): Any? {
        return id
    }

    fun setId(id: Any?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

}