package com.paul_alarcon.nytimesapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.paul_alarcon.nytimesapi.models.article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=Sports&api-key=g7LVstKd7fsJilQAsdfgjInDmXRSco54";
    List<article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_URL, new JsonHttpResponseHandler(){ //<-- callback method to the API


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject results = response.getJSONObject("response");
                    JSONArray articlesArray = results.getJSONArray("docs");
                    //need to pass the articles JSONArray over to the fromJSONArray Method.

                    articles =  article.fromJsonArray(articlesArray);

                    Log.d("hey8", articles.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
