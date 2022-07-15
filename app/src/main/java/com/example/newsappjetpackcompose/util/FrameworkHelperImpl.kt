package com.example.newsappjetpackcompose.util

import android.content.Context
import android.net.ConnectivityManager
import com.example.newsappjetpackcompose.R
import javax.inject.Inject

class FrameworkHelperImpl @Inject constructor(val context: Context): FrameworkHelper {

    override fun isOnline(): Boolean {
        //Verify if there is internet connection, if so then update the screen with the news articles
        //Otherwise show the message there is no internet connection
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo;
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting;
    }

    override fun getNoInternetMessage(): String {
        return context.getString(R.string.no_internet_connection)
    }
}