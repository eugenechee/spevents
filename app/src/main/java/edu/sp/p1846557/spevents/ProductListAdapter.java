package edu.sp.p1846557.spevents;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private JSONObject items;
    LayoutInflater inflater;
    private JSONArray names;
    private Context mContext;

    public ProductListAdapter(Context ctx, JSONObject obj){
        //store a reference to the loaded JSONArray from internet
        //items = obj;
        setItems(obj);
        mContext = ctx;

        //get inflater for later use
        inflater = LayoutInflater.from(ctx);

    }

    public JSONObject getItems() {
        return items;
    }

    public void setItems(JSONObject items){
        this.items = items;
        //store the key names for easy use later to lessen processing load
        names = items.names();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ProductViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //retrieve data from array
        try{
            //get the json object at this row position
            JSONObject obj = items.getJSONObject(names.getString(position));
            //display the name in textview
            holder.tv.setText(obj.getString("name"));
        }
        catch (Exception e) {
            Log.e("ProductViewHolder", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return items.length();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv;
        ProductListAdapter pAdapter;
        public ProductViewHolder(View itemView, ProductListAdapter adapter){
            super(itemView);
            //get textview for later
            tv = (TextView)itemView.findViewById(R.id.word);
            pAdapter = adapter;
            //set click listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            try{
                //get the json object at the clicked position
                JSONObject obj = items.getJSONObject(names.getString(this.getAdapterPosition()));
                //print details from json object in Logcat
                //add intent to a different activity
                Intent intent = new Intent(mContext, EventDetail.class);
                intent.putExtra("name", obj.getString("name"));
                intent.putExtra("price", obj.getString("price"));
                mContext.startActivity(intent);


                Log.d("ProductViewHolder", "Clicked: " + this.getAdapterPosition() + "-" +
                        obj.getString("name") + " $" + obj.getString("price"));
            }
            catch (Exception e) {
                Log.e("ProductViewHolder", e.getMessage());
            }
        }

    }
}
