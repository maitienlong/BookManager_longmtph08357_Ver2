package com.example.bookmanager_longmtph08357;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_actitivity);
    }

    public void btnUser(View view) {
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }

    public void btnBill(View view) {
        Intent intent = new Intent(this, BillActivity.class);
        startActivity(intent);
    }

    public void btnTheLoai(View view) {



    }
}
