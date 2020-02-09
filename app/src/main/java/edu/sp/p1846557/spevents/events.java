package edu.sp.p1846557.spevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.LinkedList;

public class events extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    RecyclerView recyclerView;
    ProductListAdapter pAdapter;
    final String TAG="MainActivity";
    JSONObject items = new JSONObject();
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        /*for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + mCount++);
            Log.d("WordList", mWordList.getLast());
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        queue = Volley.newRequestQueue(this);
        setupRecycler();
        connectToInternet();

    }

    void connectToInternet() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://events-8b198.firebaseio.com/products.json";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: " + response);
                        parseData(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    void parseData(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            // once data is loaded, update adapter for RecyclerView
            pAdapter.setItems(jsonObject);
            pAdapter.notifyDataSetChanged();
        }
        catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    void setupRecycler(){

        recyclerView = findViewById(R.id.recyclerview);
        pAdapter = new ProductListAdapter(this, items);
        recyclerView.setAdapter(pAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


