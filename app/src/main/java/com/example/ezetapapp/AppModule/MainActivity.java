package com.example.ezetapapp.AppModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezetapapp.NetworkModule.ApiNetwork;
import com.example.ezetapapp.NetworkModule.RetrofitClient;
import com.example.ezetapapp.PojoClasses.Response;
import com.example.ezetapapp.PojoClasses.Uidata;
import com.example.ezetapapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name,mobile,city;
    TextView header;
    ImageView img;
    ProgressDialog pDialog;
    Context context;
    Button submit;
    ArrayList<Uidata>dataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        initViews();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().trim().isEmpty()){
                    Toast.makeText(context, "Please enter your name !!", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }

                if (mobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(context, "Please enter your mobile number !!", Toast.LENGTH_SHORT).show();
                    mobile.requestFocus();
                    return;
                }

                if (city.getText().toString().trim().isEmpty()){
                    Toast.makeText(context, "Please enter your city !!", Toast.LENGTH_SHORT).show();
                    city.requestFocus();
                    return;
                }

                Intent i=new Intent(context,SecondActivity.class);
                i.putExtra("name",name.getText().toString().trim());
                i.putExtra("mobile",mobile.getText().toString().trim());
                i.putExtra("city",city.getText().toString().trim());
                startActivity(i);
            }
        });
    }

    private void initViews() {
        img=findViewById(R.id.img);
        header=findViewById(R.id.heading);
        name=findViewById(R.id.full_name);
        mobile=findViewById(R.id.mobile);
        city=findViewById(R.id.city);
        submit=findViewById(R.id.submit);
        loadJSON();
    }

    private void loadJSON() {


        pDialog = new ProgressDialog(context);
        pDialog.setTitle("Loading...");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.style));
        pDialog.setCancelable(false);
        pDialog.show();

        /*Retrofit retrofit=new Retrofit.Builder().baseUrl("https://makeup-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiNetwork api=retrofit.create(ApiNetwork.class);*/

        ApiNetwork api = RetrofitClient.getClient().create(ApiNetwork.class);
        Call<Response> call=api.getJSON();


        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                pDialog.dismiss();
                assert response.body() != null;
                try {

                    Uri uri1 = Uri.parse(response.body().logoUrl);
                    context = img.getContext();


                    Picasso.with(context)
                            .load(uri1)
                            .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
                            .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                            .into(img);

                    header.setText(response.body().getHeadingText());
                    name.setHint(response.body().getUidata().get(1).getHint());
                    mobile.setHint(response.body().getUidata().get(3).getHint());
                    city.setHint(response.body().getUidata().get(5).getHint());

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