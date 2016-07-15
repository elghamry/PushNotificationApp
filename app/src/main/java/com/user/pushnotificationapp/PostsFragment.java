package com.user.pushnotificationapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ELGHAMRY on 09/06/2016.
 */
public class PostsFragment extends Fragment {
    ListView posts_listViw ;
   FirebasePostsListAdapter mAdapter;
    DetailsListener mlistener;
    private DatabaseReference mDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.posts_container, container, false);
        posts_listViw = (ListView)rootView.findViewById(R.id.List_posts);


        //Firebase mref = new Firebase(Constants.FIREBASE_URL_WL).child(email);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase=mDatabase.child("superme").child("posts");
        mAdapter= new FirebasePostsListAdapter(getActivity(), Post.class,R.layout.post,mDatabase);

      posts_listViw.setAdapter(mAdapter);
        Log.d("test watchlist", "onCreateView: "+mDatabase.toString());
        posts_listViw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                WatchListItem item = mAdapter.getItem(position);
//                WatchListDialog.newInstance(item.getId()).show(getActivity().getFragmentManager(),"Delete");

                Post item = mAdapter.getItem(position);
                mlistener.setDetails(item.getTimeStampD(),item.getTimeStampH(),item.getMessage(),item.getAurl());
            }
        });


        return rootView;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    public void setDetailsListener(DetailsListener detailsListener) {
        mlistener=detailsListener;
    }
}
