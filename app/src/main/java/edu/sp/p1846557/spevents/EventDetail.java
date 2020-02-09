package edu.sp.p1846557.spevents;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventDetail extends AppCompatActivity {

    private static final String TAG = "EventDetail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
                
    }

    private void getIncomingIntent() {

        if(getIntent().hasExtra("name") && getIntent().hasExtra("price")){
            String name = getIntent().getStringExtra("name");
            String price = getIntent().getStringExtra("price");
            setTextView(name, price);
        }
    }
    private void setTextView(String name, String price) {

        TextView product = findViewById(R.id.name);
        product.setText(name);
        TextView productprice = findViewById(R.id.price);
        productprice.setText(price);

    }
}
