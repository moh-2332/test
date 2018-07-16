package com.simi.codestrokealert.activity.login;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;


public class ScanQrCodeActivity extends AppCompatActivity {

    private ImageView qr_code_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);
        SharedPref.init(getApplicationContext());

        qr_code_image = (ImageView)findViewById(R.id.qr_code_image);
        qr_code_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScanQrCodeActivity.this, LoginActivity.class));
            }
        });
    }
}
