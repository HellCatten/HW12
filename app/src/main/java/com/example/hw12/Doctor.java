package com.example.hw12;

public class Doctor {

    private String fName;
    private String lName;
    private String speciality;
    private boolean isAttictated;

    public Doctor(String fName, String lName, String speciality, boolean isAttictated) {
        this.fName = fName;
        this.lName = lName;
        this.speciality = speciality;
        this.isAttictated = isAttictated;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public boolean isAttictated() {
        return isAttictated;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAttictated(boolean attictated) {
        isAttictated = attictated;
    }
}
