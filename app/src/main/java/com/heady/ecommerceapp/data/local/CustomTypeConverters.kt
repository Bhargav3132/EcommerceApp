package com.heady.ecommerceapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heady.ecommerceapp.model.Tax
import com.heady.ecommerceapp.model.VariantsItem
import java.util.*


class CustomTypeConverters {

    var gson = Gson()

    @TypeConverter
    fun stringToListOfInt(data: String?): List<Int> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun intListToString(someObjects: List<Int>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToListOfVariant(data: String?): List<VariantsItem> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<VariantsItem>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun variantListToString(someObjects: List<VariantsItem>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToTax(data: String?): Tax {
        if (data == null) {
            return Tax()
        }

        val listType = object : TypeToken<Tax>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun taxToString(someObjects: Tax): String {
        return gson.toJson(someObjects)
    }

}