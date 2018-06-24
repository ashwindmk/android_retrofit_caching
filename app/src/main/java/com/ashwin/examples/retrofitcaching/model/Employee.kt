package com.ashwin.examples.retrofitcaching.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ashwin on 6/18/2018.
 */

class Employee {

    @SerializedName("id")
    private val id: Int? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("age")
    private val age: Int? = null

    @SerializedName("company")
    private val company: String? = null

    @SerializedName("salary")
    private val salary: Double? = null

    override fun toString(): String {
        return "[$id. $name ($age), $company with INR $salary]"
    }

}
