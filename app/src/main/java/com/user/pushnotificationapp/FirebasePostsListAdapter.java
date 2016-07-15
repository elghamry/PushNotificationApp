package com.user.pushnotificationapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by ELGHAMRY on 09/06/2016.
 */
public class FirebasePostsListAdapter extends FirebaseListAdapter<Post> {

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public FirebasePostsListAdapter(Activity activity, Class<Post> modelClass, int modelLayout,

                                    Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View v, Post model, int position) {
        TextView TimeD,TimeH,Massage;
        TimeD=(TextView)v.findViewById(R.id.timeStampD);
        TimeH=(TextView)v.findViewById(R.id.timeStampH);
        Massage = (TextView)v.findViewById(R.id.message);

        // Font path
        String fontPath = "fonts/century-gothic.ttf";



        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(mActivity.getAssets(), fontPath);
        //
        Massage.setTypeface(tf);
        TextView msg = (TextView) v.findViewById(R.id.msg);
        msg.setTypeface(tf);
        TimeD.setText(model.getTimeStampD());
        TimeH.setText(model.getTimeStampH());
        Massage.setText(model.getMessage());
        Log.d("hey here", model.getMessage().toString());

    }

    /**
     * Protected method that populates the view attached to the adapter (list_view_active_lists)
     * with items inflated from single_active_list.xml
     * populateView also handles data changes and updates the listView accordingly
     */
//    @Override
//    protected void populateView(View view,Post item) {
//
//        ImageView image = (ImageView)view.findViewById(R.id.w_img) ;
//        TextView text1=(TextView)view.findViewById(R.id.w_title);
//        TextView text2=(TextView)view.findViewById(R.id.w_date);
//
//        image.setImageBitmap(Utils.decodeBase64(item.getImg()));
//        text1.setText(item.getTitle());
//        text2.setText(item.getDate());
//    }

}