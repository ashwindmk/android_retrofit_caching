package com.ashwin.examples.retrofitcaching

import com.ashwin.examples.retrofitcaching.model.Employee
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Ashwin on 6/23/2018.
 */

interface ApiInterface {

    @GET("employees_array.json")
    fun listEmployees(): Call<List<Employee>>

}
