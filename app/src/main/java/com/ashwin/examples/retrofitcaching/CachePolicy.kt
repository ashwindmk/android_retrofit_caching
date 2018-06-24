package com.ashwin.examples.retrofitcaching

/**
 * Created by Ashwin on 6/24/2018.
 */

enum class CachePolicy {
    CACHE_ONLY,
    NETWORK_ONLY,
    NETWORK_ELSE_CACHE,
    FRESH_CASHE_ELSE_NETWORK,
    CACHE_ELSE_NETWORK
}
