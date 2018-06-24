package com.ashwin.examples.retrofitcaching

import android.content.Context
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ashwin on 6/23/2018.
 */

class ApiClient {

    companion object {
        fun getCacheControl(cachePolicy: CachePolicy): CacheControl {
            return when (cachePolicy) {
                CachePolicy.CACHE_ONLY -> CacheControl.FORCE_CACHE
                CachePolicy.NETWORK_ONLY -> CacheControl.FORCE_NETWORK
                CachePolicy.NETWORK_ELSE_CACHE -> CacheControl.Builder().maxAge(1, TimeUnit.MINUTES).maxStale(4, TimeUnit.DAYS).build()
                CachePolicy.FRESH_CASHE_ELSE_NETWORK -> CacheControl.Builder().maxAge(1, TimeUnit.DAYS).minFresh(1, TimeUnit.DAYS).build()
                CachePolicy.CACHE_ELSE_NETWORK -> CacheControl.Builder().immutable().build()
            }
        }

        fun getClient(applicationContext : Context, cacheControl: CacheControl) : Retrofit {
            val baseUrl = "https://gist.githubusercontent.com/ashwindmk/6d2fc5633a248166b327a0d4b2758b38/raw/"
            val cacheSize: Long = 10 * 1024 * 1024  // 10 MB
            val cache = Cache(applicationContext.filesDir, cacheSize)

            val interceptor = Interceptor { chain ->
                val request: Request? = chain.request()?.newBuilder()?.cacheControl(cacheControl)?.build()
                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(interceptor)
                    .build()

            val builder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())

            return builder.build()
        }
    }

}
