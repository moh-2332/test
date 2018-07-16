package com.simi.codestrokealert.activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.activity.clinicians.HomeScreenActivity;
import com.simi.codestrokealert.activity.paramedics.PatientDetailsActivity;


public class LoginUserActivity extends AppCompatActivity {

    private EditText username_edt, password_edt;
    private Button login_btn;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login_user);
        initViews();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = username_edt.getText().toString();
                password = password_edt.getText().toString();

                //Check whether username or password is empty or not
                if(!username.isEmpty() && !password.isEmpty() ) {
                    loginProcess(username, password);
                }else {
                    Toast.makeText(getBaseContext(), "Field is empty !" , Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    protected void initViews(){
        username_edt = (EditText)findViewById(R.id.username_edt);
        password_edt = (EditText)findViewById(R.id.password_edt);
        login_btn = (Button)findViewById(R.id.login_btn);
    }

    protected void loginProcess(String username, String password){
        //User validation
        if(username.equals("paramedics") && password.equals("123456")){
            startActivity(new Intent(getBaseContext(), PatientDetailsActivity.class));
        }else if(username.equals("clinicians") && password.equals("123456")){
            startActivity(new Intent(getBaseContext(), HomeScreenActivity.class));
        } else{
            Toast.makeText(getBaseContext(), "username or password incorrect." , Toast.LENGTH_SHORT).show();
        }
    }
}
