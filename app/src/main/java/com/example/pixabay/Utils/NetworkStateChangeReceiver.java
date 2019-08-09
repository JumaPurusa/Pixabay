package com.example.pixabay.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;

public class NetworkStateChangeReceiver extends BroadcastReceiver {

    private final static String TAG = NetworkStateChangeReceiver.class.getSimpleName();
    public final static String NETWORK_AVAILABLE_ACTION = "network_available_action";
    public final static String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(NETWORK_AVAILABLE_ACTION);
        intent.putExtra(IS_NETWORK_AVAILABLE, isConnected(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null & networkInfo.isConnected();
    }


}
