package com.ashwin.examples.retrofitcaching

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ashwin.examples.retrofitcaching.model.Employee
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        fetchData()
    }

    private fun fetchData() {
        Log.d("debug-log", "fetching data...")
        text_view.text = "loading..."

        val apiInterface = ApiClient.getClient(applicationContext, ApiClient.getCacheControl(CachePolicy.NETWORK_ELSE_CACHE)).create(ApiInterface::class.java)
        val call = apiInterface.listEmployees()
        call.enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>?, response: retrofit2.Response<List<Employee>>?) {
                Log.d("debug-log", "success! network-response: ${response?.raw()?.networkResponse()}, cache-response: ${response?.raw()?.cacheResponse()}")
                val list: List<Employee>? = response?.body()
                val sb: StringBuilder = StringBuilder("")
                list?.forEach {
                    sb.append("\n\n $it")
                }
                text_view.text = sb.toString()
            }
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.e("debug-log", "failure!", t)
                text_view.text = "Error"
            }
        })
    }

}
