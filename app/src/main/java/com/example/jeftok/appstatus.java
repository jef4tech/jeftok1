package com.example.jeftok;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.PrintStream;

public class appstatus {
    static Context context;
    private static appstatus instance = new appstatus();
    boolean connected = false;
    ConnectivityManager connectivityManager;
    NetworkInfo mobileInfo;
    NetworkInfo wifiInfo;

    public static appstatus getInstance(Context context) {
        context = context.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
            this.connected = z;
            return this.connected;
        } catch (Exception e) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CheckConnectivity Exception: ");
            stringBuilder.append(e.getMessage());
            printStream.println(stringBuilder.toString());
            Log.v("connectivity", e.toString());
            return this.connected;
        }
    }
}


