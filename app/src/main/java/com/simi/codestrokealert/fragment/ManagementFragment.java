package com.simi.codestrokealert.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.Utility;
import com.simi.codestrokealert.model.CaseManagements;
import com.simi.codestrokealert.model.CaseManagmentsResponse;
import com.simi.codestrokealert.model.CaseRadiologies;
import com.simi.codestrokealert.model.CaseRadiologiesResponse;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManagementFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private TextView g_18_old, txt_large_vessel_occlusion, txt_ich_on_noncontrast, last_well;
    private CaseManagements caseManagements;
    private int id = SharedPref.read(SharedPref.PATIANT_ID, -1);
    private RadioButton yes_conservative_management, no_conservative_management, yes_neu, no_neu,
            yes_uncontrolled_htn, no_uncontrolled_htn, yes_history_of_ich, no_history_of_ich,
            yes_known_intracranial, no_known_intracranial, yes_active_bleeding, no_active_bleeding,
            yes_endocarditis, no_endocarditis, yes_known_bleeding_diathesis, no_known_bleeding_diathesis,
            yes_abnormal_blood_glucose, no_abnormal_blood_glucose, yes_rapidly_improving, no_rapidly_improving,
            yes_major_trauma, no_major_trauma, yes_active_bleeding_in_last, no_active_bleeding_in_last,
            yes_seizure_at_onset, no_seizure_at_onset, yes_recent_arterial_puncture, no_recent_arterial_puncture,
            yes_recent_lumbar_puncture, no_recent_lumbar_puncture, yes_post_acs_pericarditis, no_post_acs_pericarditis,
            yes_abnormal_blood_glucose_relative, no_abnormal_blood_glucose_relative, yes_pregnant, no_pregnant,
            yes_time_when_thrombolysis_given, no_time_when_thrombolysis_given, yes_ecr, no_ecr,
            yes_surgical_management, no_surgical_management;
    private Button case_completed_btn, submit;
    private byte thrombolysis, new_trauma_haemorrhage, uncontrolled_htn, history_ich, known_intracranial, active_bleed,
                    endocarditis, bleeding_diathesis, abnormal_blood_glucose, rapidly_improving, recent_trauma_surgery,
                    recent_active_bleed, seizure_onset, recent_arterial_puncture, recent_lumbar_puncture, post_acs_pericarditis, pregnant,
                    ecr, surgical_rx, conservative_rx;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_management, container, false);
        initViews();
        getCaseManagementsInfo();


        return rootView;
    }

    protected void initViews(){
        caseManagements = new CaseManagements();
        submit = (Button)rootView.findViewById(R.id.submit);
        case_completed_btn = (Button)rootView.findViewById(R.id.case_completed_btn);
        yes_conservative_management = (RadioButton)rootView.findViewById(R.id.yes_cannula_in_cub_fossa);
        no_conservative_management = (RadioButton)rootView.findViewById(R.id.no_cannula_in_cub_fossa);
        yes_neu = (RadioButton)rootView.findViewById(R.id.yes_neu);
        no_neu = (RadioButton)rootView.findViewById(R.id.no_neu);
        yes_uncontrolled_htn = (RadioButton)rootView.findViewById(R.id.yes_uncontrolled_htn);
        no_uncontrolled_htn = (RadioButton)rootView.findViewById(R.id.no_uncontrolled_htn);
        yes_history_of_ich = (RadioButton)rootView.findViewById(R.id.yes_neu);
        no_history_of_ich = (RadioButton)rootView.findViewById(R.id.no_history_of_ich);
        yes_known_intracranial = (RadioButton)rootView.findViewById(R.id.yes_known_intracranial);
        no_known_intracranial = (RadioButton)rootView.findViewById(R.id.no_known_intracranial);
        yes_active_bleeding = (RadioButton)rootView.findViewById(R.id.yes_active_bleeding);
        no_active_bleeding = (RadioButton)rootView.findViewById(R.id.no_active_bleeding);
        yes_endocarditis = (RadioButton)rootView.findViewById(R.id.yes_endocarditis);
        no_endocarditis = (RadioButton)rootView.findViewById(R.id.no_endocarditis);
        yes_known_bleeding_diathesis = (RadioButton)rootView.findViewById(R.id.yes_known_bleeding_diathesis);
        no_known_bleeding_diathesis = (RadioButton)rootView.findViewById(R.id.no_known_bleeding_diathesis);
        yes_abnormal_blood_glucose = (RadioButton)rootView.findViewById(R.id.yes_abnormal_blood_glucose);
        no_abnormal_blood_glucose = (RadioButton)rootView.findViewById(R.id.no_abnormal_blood_glucose);
        yes_rapidly_improving = (RadioButton)rootView.findViewById(R.id.yes_rapidly_improving);
        no_rapidly_improving = (RadioButton)rootView.findViewById(R.id.no_rapidly_improving);
        yes_major_trauma = (RadioButton)rootView.findViewById(R.id.yes_major_trauma);
        no_major_trauma = (RadioButton)rootView.findViewById(R.id.no_major_trauma);
        yes_active_bleeding_in_last = (RadioButton)rootView.findViewById(R.id.yes_active_bleeding_in_last);
        no_active_bleeding_in_last = (RadioButton)rootView.findViewById(R.id.no_active_bleeding_in_last);
        yes_seizure_at_onset = (RadioButton)rootView.findViewById(R.id.yes_seizure_at_onset);
        no_seizure_at_onset = (RadioButton)rootView.findViewById(R.id.no_seizure_at_onset);
        yes_recent_arterial_puncture = (RadioButton)rootView.findViewById(R.id.yes_recent_arterial_puncture);
        no_recent_arterial_puncture = (RadioButton)rootView.findViewById(R.id.no_recent_arterial_puncture);
        yes_recent_lumbar_puncture = (RadioButton)rootView.findViewById(R.id.yes_recent_lumbar_puncture);
        no_recent_lumbar_puncture = (RadioButton)rootView.findViewById(R.id.no_recent_lumbar_puncture);
        yes_post_acs_pericarditis = (RadioButton)rootView.findViewById(R.id.yes_post_acs_pericarditis);
        no_post_acs_pericarditis = (RadioButton)rootView.findViewById(R.id.no_post_acs_pericarditis);
        yes_abnormal_blood_glucose_relative = (RadioButton)rootView.findViewById(R.id.yes_abnormal_blood_glucose_relative);
        no_abnormal_blood_glucose_relative = (RadioButton)rootView.findViewById(R.id.no_abnormal_blood_glucose_relative);
        yes_pregnant = (RadioButton)rootView.findViewById(R.id.yes_pregnant);
        no_pregnant = (RadioButton)rootView.findViewById(R.id.no_pregnant);
        yes_time_when_thrombolysis_given = (RadioButton)rootView.findViewById(R.id.yes_time_when_thrombolysis_given);
        no_time_when_thrombolysis_given = (RadioButton)rootView.findViewById(R.id.no_time_when_thrombolysis_given);
        yes_ecr = (RadioButton)rootView.findViewById(R.id.yes_ecr);
        no_ecr = (RadioButton)rootView.findViewById(R.id.no_ecr);
        yes_surgical_management = (RadioButton)rootView.findViewById(R.id.yes_surgical_management);
        no_surgical_management = (RadioButton)rootView.findViewById(R.id.no_surgical_management);
        yes_conservative_management = (RadioButton)rootView.findViewById(R.id.yes_conservative_management);
        no_conservative_management = (RadioButton)rootView.findViewById(R.id.no_conservative_management);
        last_well = (TextView) rootView.findViewById(R.id.last_well);
        g_18_old = (TextView) rootView.findViewById(R.id.g_18_old);
        txt_ich_on_noncontrast = (TextView) rootView.findViewById(R.id.txt_ich_on_noncontrast);
        txt_large_vessel_occlusion = (TextView) rootView.findViewById(R.id.txt_large_vessel_occlusion);
        submit.setOnClickListener(this);
        case_completed_btn.setOnClickListener(this);
        yes_conservative_management.setOnClickListener(this);
        no_conservative_management.setOnClickListener(this);
        yes_neu.setOnClickListener(this);
        no_neu.setOnClickListener(this);
        yes_uncontrolled_htn.setOnClickListener(this);
        no_uncontrolled_htn.setOnClickListener(this);
        yes_history_of_ich.setOnClickListener(this);
        no_history_of_ich.setOnClickListener(this);
        yes_known_intracranial.setOnClickListener(this);
        no_known_intracranial.setOnClickListener(this);
        yes_active_bleeding.setOnClickListener(this);
        no_active_bleeding.setOnClickListener(this);
        yes_endocarditis.setOnClickListener(this);
        no_endocarditis.setOnClickListener(this);
        yes_known_bleeding_diathesis.setOnClickListener(this);
        no_known_bleeding_diathesis.setOnClickListener(this);
        yes_abnormal_blood_glucose.setOnClickListener(this);
        no_abnormal_blood_glucose.setOnClickListener(this);
        yes_rapidly_improving.setOnClickListener(this);
        no_rapidly_improving.setOnClickListener(this);
        yes_major_trauma.setOnClickListener(this);
        no_major_trauma.setOnClickListener(this);
        yes_active_bleeding_in_last.setOnClickListener(this);
        no_active_bleeding_in_last.setOnClickListener(this);
        yes_seizure_at_onset.setOnClickListener(this);
        no_seizure_at_onset.setOnClickListener(this);
        yes_recent_arterial_puncture.setOnClickListener(this);
        no_recent_arterial_puncture.setOnClickListener(this);
        yes_recent_lumbar_puncture.setOnClickListener(this);
        no_recent_lumbar_puncture.setOnClickListener(this);
        yes_post_acs_pericarditis.setOnClickListener(this);
        no_post_acs_pericarditis.setOnClickListener(this);
        yes_abnormal_blood_glucose_relative.setOnClickListener(this);
        no_abnormal_blood_glucose_relative.setOnClickListener(this);
        yes_pregnant.setOnClickListener(this);
        no_pregnant.setOnClickListener(this);
        yes_time_when_thrombolysis_given.setOnClickListener(this);
        no_time_when_thrombolysis_given.setOnClickListener(this);
        yes_ecr.setOnClickListener(this);
        no_ecr.setOnClickListener(this);
        yes_surgical_management.setOnClickListener(this);
        no_surgical_management.setOnClickListener(this);
        yes_conservative_management.setOnClickListener(this);
        no_conservative_management.setOnClickListener(this);

    }

    protected void getCaseManagementsInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface getCaseManagementsApi = retrofit.create(RequestInterface.class);
        Call<CaseManagmentsResponse> responseCaseManagments =  getCaseManagementsApi.getCaseManagments(id);
        responseCaseManagments.enqueue(new Callback<CaseManagmentsResponse>() {
            @Override
            public void onResponse(Call<CaseManagmentsResponse> call, Response<CaseManagmentsResponse> response) {
                List<CaseManagements> resp = response.body().getResult();
                if(resp != null) {
                    caseManagements = resp.get(0);
                    updateViews(caseManagements);
                }else {
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
            public void onFailure(Call<CaseManagmentsResponse> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void updateViews(CaseManagements managements){

        String dateOfBirth = managements.getDob();
        int age = new Utility().convertDobToAge(dateOfBirth);
        if(age > 18){
            g_18_old.setText("Yes");
        }else {
            g_18_old.setText("No");
        }
        String last_well = managements.getLast_well();
        if(managements.getLarge_vessel_occlusion() == 1){
            txt_large_vessel_occlusion.setText("Yes");
        }else{
            txt_large_vessel_occlusion.setText("No");
        }
        if(managements.getIch_found() == 1){
            txt_ich_on_noncontrast.setText("Yes");
        }else{
            txt_ich_on_noncontrast.setText("No");
        }
        if (managements.getNew_trauma_haemorrhage() == 1){
            yes_neu.setChecked(true);
        }else {
            no_neu.setChecked(true);
        }

        if (managements.getUncontrolled_htn() == 1){
            yes_uncontrolled_htn.setChecked(true);
        }else {
            no_uncontrolled_htn.setChecked(true);
        }

        if (managements.getHistory_ich() == 1){
            yes_history_of_ich.setChecked(true);
        }else {
            no_history_of_ich.setChecked(true);
        }

        if (managements.getKnown_intracranial() == 1){
            yes_known_intracranial.setChecked(true);
        }else {
            no_known_intracranial.setChecked(true);
        }

        if (managements.isActive_bleed() == 1){
            yes_active_bleeding.setChecked(true);
        }else {
            no_active_bleeding.setChecked(true);
        }

        if (managements.getEndocarditis() == 1){
            yes_endocarditis.setChecked(true);
        }else {
            no_endocarditis.setChecked(true);
        }

        if (managements.getBleeding_diathesis() == 1){
            yes_known_bleeding_diathesis.setChecked(true);
        }else {
            no_known_bleeding_diathesis.setChecked(true);
        }

        if (managements.getAbnormal_blood_glucose() == 1){
            yes_abnormal_blood_glucose.setChecked(true);
        }else {
            no_abnormal_blood_glucose.setChecked(true);
        }

        if (managements.getRapidly_improving() == 1){
            yes_rapidly_improving.setChecked(true);
        }else {
            no_rapidly_improving.setChecked(true);
        }

        if(managements.getRecent_trauma_surgery() == 1){
            yes_major_trauma.setChecked(true);
        }else {
            no_major_trauma.setChecked(true);
        }

        if(managements.getRecent_active_bleed() == 1){
            yes_active_bleeding_in_last.setChecked(true);
        }else {
            no_active_bleeding_in_last.setChecked(true);
        }

        if(managements.getSeizure_onset() == 1){
            yes_seizure_at_onset.setChecked(true);
        }else {
            no_seizure_at_onset.setChecked(true);
        }

        if(managements.getRecent_arterial_puncture() == 1){
            yes_recent_arterial_puncture.setChecked(true);
        }else {
            no_recent_arterial_puncture.setChecked(true);
        }

        if(managements.getRecent_lumbar_puncture() == 1){
            yes_recent_lumbar_puncture.setChecked(true);
        }else {
            no_recent_lumbar_puncture.setChecked(true);
        }

        if(managements.getPost_acs_pericarditis() == 1){
            yes_post_acs_pericarditis.setChecked(true);
        }else {
            no_post_acs_pericarditis.setChecked(true);
        }

        if(managements.getAbnormal_blood_glucose() == 1){
            yes_abnormal_blood_glucose_relative.setChecked(true);
        }else {
            no_abnormal_blood_glucose_relative.setChecked(true);
        }

        if(managements.getPregnant() == 1){
            yes_pregnant.setChecked(true);
        }else {
            no_pregnant.setChecked(true);
        }

        if(managements.getEcr() == 1){
            yes_ecr.setChecked(true);
        }else {
            no_ecr.setChecked(true);
        }

        if(managements.getSurgical_rx() == 1){
            yes_surgical_management.setChecked(true);
        }else {
            no_surgical_management.setChecked(true);
        }

        if(managements.getConservative_rx() == 1){
            yes_conservative_management.setChecked(true);
        }else {
            no_conservative_management.setChecked(true);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                submitCaseManagements();
                break;
        }
    }

    protected void submitCaseManagements(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCaseManagementsApi = retrofit.create(RequestInterface.class);

        caseManagements.setCase_id(id);
        if(yes_neu.isChecked()){
            new_trauma_haemorrhage = 1;
        }else {
            new_trauma_haemorrhage = 0;
        }
        caseManagements.setNew_trauma_haemorrhage(new_trauma_haemorrhage);

        if(yes_uncontrolled_htn.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);


        if(yes_history_of_ich.isChecked()){
            history_ich = 1;
        }else {
            history_ich = 0;
        }
        caseManagements.setHistory_ich(history_ich);

        if(yes_known_intracranial.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_active_bleeding.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_endocarditis.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_known_bleeding_diathesis.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_abnormal_blood_glucose.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_rapidly_improving.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_major_trauma.isChecked()){
            uncontrolled_htn = 1;
        }else {
            uncontrolled_htn = 0;
        }
        caseManagements.setUncontrolled_htn(uncontrolled_htn);

        if(yes_active_bleeding_in_last.isChecked()){
            recent_active_bleed = 1;
        }else {
            recent_active_bleed = 0;
        }
        caseManagements.setRecent_active_bleed(recent_active_bleed);

        if(yes_seizure_at_onset.isChecked()){
            seizure_onset = 1;
        }else {
            seizure_onset = 0;
        }
        caseManagements.setSeizure_onset(seizure_onset);

        if(yes_recent_arterial_puncture.isChecked()){
            recent_arterial_puncture = 1;
        }else {
            recent_arterial_puncture = 0;
        }
        caseManagements.setRecent_arterial_puncture(recent_arterial_puncture);

        if(yes_recent_lumbar_puncture.isChecked()){
            recent_lumbar_puncture = 1;
        }else {
            recent_lumbar_puncture = 0;
        }
        caseManagements.setRecent_lumbar_puncture(recent_lumbar_puncture);

        if(yes_post_acs_pericarditis.isChecked()){
            post_acs_pericarditis = 1;
        }else {
            post_acs_pericarditis = 0;
        }
        caseManagements.setPost_acs_pericarditis(post_acs_pericarditis);

        if(yes_pregnant.isChecked()){
            pregnant = 1;
        }else {
            pregnant = 0;
        }
        caseManagements.setPregnant(pregnant);

        if(yes_ecr.isChecked()){
            ecr = 1;
        }else {
            ecr = 0;
        }
        caseManagements.setEcr(uncontrolled_htn);

        if(yes_surgical_management.isChecked()){
            surgical_rx = 1;
        }else {
            surgical_rx = 0;
        }
        caseManagements.setSurgical_rx(surgical_rx);

        if(yes_conservative_management.isChecked()){
            conservative_rx = 1;
        }else {
            conservative_rx = 0;
        }
        caseManagements.setConservative_rx(conservative_rx);


        Call<CaseManagements> response = updateCaseManagementsApi.updateCaseManagements(id, caseManagements);
        response.enqueue(new Callback<CaseManagements>() {
            @Override
            public void onResponse(Call<CaseManagements> call, Response<CaseManagements> response) {
                CaseManagements resp = response.body();
                if(resp != null){
                    Toast.makeText(getActivity(), "Managements Updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CaseManagements> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }





}
