package com.user.pushnotificationapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ELGHAMRY on 18/06/2016.
 */
public class DetailsFragment extends Fragment {
    String att="att",time="att",date="att",message="att";
    TextView dateTV,timeTV,messageTV,attTV;
    ProgressDialog mProgressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Log.d("test name ", "onCreateView: "+getActivity().getIntent().getStringExtra("name"));




        View rootView = inflater.inflate(R.layout.details_fragment, container, false);
         dateTV = (TextView) rootView.findViewById(R.id.timeStampD);
         timeTV = (TextView) rootView.findViewById(R.id.timeStampH);
       messageTV = (TextView) rootView.findViewById(R.id.message);
        // Font path
        String fontPath = "fonts/century-gothic.ttf";



        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        //
        messageTV.setTypeface(tf);
        TextView msg = (TextView) rootView.findViewById(R.id.msg);
        msg.setTypeface(tf);
        attTV = (TextView ) rootView.findViewById(R.id.att);
        boolean dire=true;
        try {
            String test = getArguments().getString("date");
        }
        catch (NullPointerException e){
            dire=false;

        }


//get information from src
        if(dire==true){


         date = getArguments().getString("date");
         time = getArguments().getString("time");
         message = getArguments().getString("message");
         att = getArguments().getString("att");
        /////////////////

            dateTV.setText(date);
            timeTV.setText(time);
            messageTV.setText(message);
            if(!att.equals("no file"))
            {
                attTV.setText(date+time+"_attachment");
                attTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = att;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                // ...
            }


        }
        else
         {
             DatabaseReference mDatabase;
// ...
            mDatabase = FirebaseDatabase.getInstance().getReference().child("supermeNotification");
             mDatabase.addValueEventListener(
                     new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             // Get user value
                             Post post = dataSnapshot.getValue(Post.class);
                             date=post.getTimeStampD();
                             time=post.getTimeStampH();
                             message=post.getMessage();
                             att=post.getAurl();
                           //  Log.d("here", "onDataChange: "+date);
                             // ...
                             dateTV.setText(date);
                             timeTV.setText(time);
                             messageTV.setText(message);
    if(!att.equals("no file"))
    {
                             attTV.setText(date+time+"_attachment");
                             attTV.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     String url = att;
                                     Intent i = new Intent(Intent.ACTION_VIEW);
                                     i.setData(Uri.parse(url));
                                     startActivity(i);
                                 }
                             });
                             // ...
                         }}

                         @Override
                         public void onCancelled(DatabaseError databaseError) {
                          //   Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                         }
                     });




        }


//        }


        return rootView;
    }


}
