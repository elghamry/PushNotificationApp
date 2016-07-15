package com.user.pushnotificationapp;

/**
 * Created by ELGHAMRY on 16/06/2016.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token);

    }

    private void registerToken(final String token) {

        OkHttpClient client = new OkHttpClient();
       Log.d("ID", "onTokenRefresh: "+token);
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://pushnotificationserver.3eeweb.com/register.php")
                .post(body)
                .build();


        try {
            Response response = client.newCall(request).execute();
            Log.d("ID", "onTokenRefresh: "+response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
/////////////////////////////////////
        //Creating a string request
//        String REG_URL="http://192.168.1.3/register.php";
//        StringRequest req = new StringRequest(Request.Method.POST, REG_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", "onResponse: ");
//
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //adding parameters to post request as we need to send firebase id and email
//                params.put("loken", token);
//
//                return params;
//            }
//        };
//
//        //Adding the request to the queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(req);


    }
}