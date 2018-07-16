package com.simi.codestrokealert.activity.paramedics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.model.CaseHospitals;
import com.simi.codestrokealert.model.Cases;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.simi.codestrokealert.Constants.API_KEY;


public class DestinationActivity extends AppCompatActivity {

    private Button btn_next;
    private NumberPicker hospitalPicker;
    private Cases cases;
    private String hospital_name;
    private CaseHospitals caseHospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_destination);
        initViews();

        cases = (Cases) getIntent().getSerializableExtra("cases");

        final String[] hospitalList = new String[]{"Austin hospital", "Monash hospital",
                                "Royal Melbourne hospital", "St. Vincent’s Hospital"};
        final int[] hospitalIds = new int[]{1, 2, 3, 4};
        hospitalPicker.setMinValue(0);
        hospitalPicker.setMaxValue(hospitalList.length-1);
        hospitalPicker.setDisplayedValues(hospitalList);
        int pos = hospitalPicker.getValue();
        hospital_name = hospitalList[pos];
        caseHospitals.setHospital_id(hospitalIds[pos]);
       cases.setHopspital_id(hospitalIds[pos]);

        hospitalPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                  hospital_name = hospitalList[newValue];
                  cases.setHopspital_id(hospitalIds[newValue]);
            }
        });

        //Go to Clinical History activity
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ClinicalHistoryActivity.class));
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                RequestInterface casesApi = retrofit.create(RequestInterface.class);

             //   Call<Cases> response = casesApi.addCase(cases, API_KEY);
                Call<Cases> response = casesApi.addCase(cases);
                response.enqueue(new Callback<Cases>() {
                    @Override
                    public void onResponse(Call<Cases> call, Response<Cases> response) {
                        Cases casesRes = response.body();
                        if (casesRes != null) {
                            Log.i("id",casesRes.getCase_id()+"" );
                            SharedPref.write(SharedPref.PATIANT_ID, casesRes.getCase_id());
                            startActivity(new Intent(getBaseContext(), ClinicalHistoryActivity.class));
                        } else {

                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();

                            reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                            String line;
                            try {
                                while ((line = reader.readLine()) != null) {
                                    sb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String finallyError = sb.toString();
                            Log.i("Error", finallyError);
                        }

                    }

                    @Override
                    public void onFailure(Call<Cases> call, Throwable t) {
                        Log.d("failure", "failed");
                        Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    protected void initViews(){
        hospitalPicker = (NumberPicker) findViewById(R.id.choose_hospital);
        btn_next = (Button)findViewById(R.id.btn_next);
        caseHospitals = new CaseHospitals();
    }
}

