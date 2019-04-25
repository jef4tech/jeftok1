package com.example.jeftok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;


public class user3 extends Fragment {

    private WebView wv1;
    TextView a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent us = getActivity().getIntent();
        final String str = us.getStringExtra("name");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user3, container, false);
        wv1 = (WebView)rootView.findViewById(R.id.webview);
        a=rootView.findViewById(R.id.textView2);
        a.setText(str);
        wv1.setWebViewClient(new MyBrowser());


        String url = "https://jeftik.000webhostapp.com/tiktok/getimage.php?id="+str;

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);
        return rootView;
    }


    static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}