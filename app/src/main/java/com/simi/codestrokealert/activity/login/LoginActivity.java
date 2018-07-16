package com.simi.codestrokealert.activity.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.simi.codestrokealert.R;

public class LoginActivity extends AppCompatActivity {

    private ImageButton btn_login_with_google,
            btn_login_with_facebook,
            btn_login_with_twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login);
        initViews();


        btn_login_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 goToLoginUser();
            }
        });

        btn_login_with_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 goToLoginUser();
            }
        });

        btn_login_with_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 goToLoginUser();
            }
        });

    }

    protected void initViews(){
        btn_login_with_google = (ImageButton)findViewById(R.id.login_with_google);
        btn_login_with_facebook = (ImageButton)findViewById(R.id.login_with_facebook);
        btn_login_with_twitter = (ImageButton)findViewById(R.id.login_with_twitter);
    }

    protected void goToLoginUser(){
        startActivity(new Intent(getBaseContext(), LoginUserActivity.class));
    }
}
