package com.booisajerk.moviemonster

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyController constructor(context: Context) {
    companion object {
        private var INSTANCE: VolleyController? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyController(context).also {
                    INSTANCE = it
                }
            }
    }
    val requestQueue: RequestQueue by lazy {

        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }
}