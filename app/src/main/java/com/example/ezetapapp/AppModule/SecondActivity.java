package com.example.ezetapapp.AppModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.TextView;

import com.example.ezetapapp.R;

public class SecondActivity extends AppCompatActivity {

    Context context;
    TextView name,mobile,city;
    String naMe,moBile,ciTy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context=this;

        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        city=findViewById(R.id.city);

        naMe=getIntent().getStringExtra("name");
        moBile=getIntent().getStringExtra("mobile");
        ciTy=getIntent().getStringExtra("city");

        name.setText(naMe);
        mobile.setText(moBile);
        city.setText(ciTy);

    }
}