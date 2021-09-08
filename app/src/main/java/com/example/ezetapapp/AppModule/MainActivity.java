package com.example.ezetapapp.AppModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ezetapapp.NetworkModule.ApiNetwork;
import com.example.ezetapapp.NetworkModule.RetrofitClient;
import com.example.ezetapapp.PojoClasses.Response;
import com.example.ezetapapp.PojoClasses.Uidata;
import com.example.ezetapapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Context context;
    ArrayList<Uidata>dataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        initViews();
    }

    private void initViews() {
        loadJSON();
    }

    private void loadJSON() {


        pDialog = new ProgressDialog(context);
        pDialog.setTitle("Loading...");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.style));
        pDialog.setCancelable(false);
        pDialog.show();

        dataArrayList = new ArrayList<>();/*
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://makeup-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiNetwork api=retrofit.create(ApiNetwork.class);*/

        ApiNetwork api = RetrofitClient.getClient().create(ApiNetwork.class);
        Call<Response> call=api.getJSON();


        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                pDialog.dismiss();
                assert response.body() != null;
                try {

                    dataArrayList=response.body().getUidata();
                }
                catch (NullPointerException exception){
                    Toast.makeText(context, exception.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}