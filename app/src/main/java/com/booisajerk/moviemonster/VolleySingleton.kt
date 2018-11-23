package com.booisajerk.moviemonster

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context:Context) {
    val requestQueue:RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        private var instance:VolleySingleton? = null
      fun getInstance(context:Context)=
            instance ?: synchronized(this) {
                instance ?: VolleySingleton(context).also {
                    instance = it
                }
            }
    }
}