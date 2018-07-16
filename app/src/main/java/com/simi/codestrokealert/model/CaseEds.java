package com.simi.codestrokealert.model;


public class CaseEds {

    private int case_id;
    private String location;
    private byte registered;
    private byte triaged;
    private byte primary_survey;

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte getRegistered() {
        return registered;
    }

    public void setRegistered(byte registered) {
        this.registered = registered;
    }

    public byte getTriaged() {
        return triaged;
    }

    public void setTriaged(byte triaged) {
        this.triaged = triaged;
    }

    public byte getPrimary_survey() {
        return primary_survey;
    }

    public void setPrimary_survey(byte primary_survey) {
        this.primary_survey = primary_survey;
    }


}
