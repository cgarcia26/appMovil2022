package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends ArrayAdapter {

    private static final String URL = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    RequestQueue requestQueue;
    List<AppModel> itemsApp;

    public AppAdapter(Context context){
        super(context,0);

        requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                itemsApp = parseJSON(response);
                notifyDataSetChanged();
                Log.e("AppAdapter Response: ",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("App Adapter ERROR: ",error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public int getCount() {
        return itemsApp!=null?itemsApp.size():0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItemView;

        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.app_adapter,
                parent,
                false ) : convertView;

        AppModel item = itemsApp.get(position);

        TextView textName = listItemView.findViewById(R.id.textName);
        TextView textRights = listItemView.findViewById(R.id.textRights);
        final ImageView image = listItemView.findViewById(R.id.image);
        TextView textSummary = listItemView.findViewById(R.id.textSummary);

        textName.setText(item.getName());
        textRights.setText(item.getRights());
        textSummary.setText(item.getSummary());

        ImageRequest request = new ImageRequest(
                item.getImage(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                image.setImageBitmap(response);
            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Image AppAdapter: ",error.toString());
            }
        });
        requestQueue.add(request);

        return listItemView;
    }

    public List<AppModel> parseJSON(JSONObject jsonObject){

        List<AppModel> applist = new ArrayList<>();

        try{
            JSONObject feedJSON = jsonObject.getJSONObject("feed");
            JSONArray entryJSON = feedJSON.getJSONArray("entry");

            for(int i=0;i<entryJSON.length();i++){
                try{
                    JSONObject entryObject = entryJSON.getJSONObject(i);
                    JSONObject nameJSON = entryObject.getJSONObject("im:name");
                    JSONObject summaryJSON = entryObject.getJSONObject("summary");
                    JSONObject rightsJSON = entryObject.getJSONObject("rights");

                    JSONArray imageJSON = entryObject.getJSONArray("im:image");
                    JSONObject imageObject = imageJSON.getJSONObject(2);

                    AppModel app = new AppModel(
                            nameJSON.getString("label"),
                            imageObject.getString("label"),
                            summaryJSON.getString("label"),
                            rightsJSON.getString("label")
                    );

                    applist.add(app);

                }catch (JSONException e) {
                    Log.e("Error parseando array: ",e.getMessage());
                }
            }

        }catch (JSONException e) {
            Log.e("Error parseando datos: ",e.getMessage());
        }

        return applist;
    }

}