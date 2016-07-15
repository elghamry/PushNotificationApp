package com.user.pushnotificationapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements DetailsListener{
    static boolean isInitialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            if(!isInitialized){
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                isInitialized = true;
            }else {
                // Log.d(TAG,"Already Initialized");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        FirebaseMessaging.getInstance().subscribeToTopic("superme");
        FirebaseInstanceId.getInstance().getToken();


        if (savedInstanceState == null) {
            PostsFragment PLFragment = new PostsFragment();
            //WLFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.posts_container, PLFragment)


                    .commit();
            // Log.d("test watchlist", "onCreateView: ");
            PLFragment.setDetailsListener(this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);



    }






    @Override
    public void setDetails(String date, String time, String message, String att) {
        //calling detailsActivity
        Intent extras=new Intent(MainActivity.this, DetailsActivity.class);
        extras.putExtra("date",date);
        extras.putExtra("time",time);
        extras.putExtra("message",message);
        extras.putExtra("att",att);

        startActivity(extras);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
