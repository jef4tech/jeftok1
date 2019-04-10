package com.example.jeftok;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class user1 extends Fragment {
    private Button booking1,cancel1;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_user1, container, false);
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(Policy);
        }
        booking1 = (Button)rootView.findViewById(R.id.check_availability_button);
        cancel1=rootView.findViewById(R.id.button6);
        booking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(),addlive.class);
                startActivity(i);

            }
        });
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(),home.class);
                startActivity(i);

            }
        });

        return rootView;
    }

}
