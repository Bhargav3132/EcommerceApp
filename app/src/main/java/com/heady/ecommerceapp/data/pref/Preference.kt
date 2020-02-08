package com.heady.ecommerceapp.data.pref

interface Preference {

    fun isDataDownloaded(): Boolean

    fun setDataDownloaded(dataDownloaded: Boolean)

}
