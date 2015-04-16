package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Target Scales´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TargetScale extends BaseModel {

    private Calendar measurementDate;
    private String moduleSerialId;
    private Float bmi;
    private Calendar updatedDate;
    private Integer mood;
    private Float bodyWater;
    private Float targetWeight;
    private Integer kcal;
    private Float muscleMass;
    private Float bodyFat;
    private Integer version;
    private String id;
    private Integer mealStatus;
    private Float boneMass;
    private Boolean active;
    private Integer activityStatus;
    private String note;
    private Float bodyWeight;
    private Integer athletic;

    /*
     * GETTERS
     */
    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Float getBmi() {
        return bmi;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getMood() {
        return mood;
    }

    public Float getBodyWater() {
        return bodyWater;
    }

    public Float getTargetWeight() {
        return targetWeight;
    }

    public Integer getKcal() {
        return kcal;
    }

    public Float getMuscleMass() {
        return muscleMass;
    }

    public Float getBodyFat() {
        return bodyFat;
    }

    public Integer getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public Integer getMealStatus() {
        return mealStatus;
    }

    public Float getBoneMass() {
        return boneMass;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public String getNote() {
        return note;
    }

    public Float getBodyWeight() {
        return bodyWeight;
    }

    public Integer getAthletic() {
        return athletic;
    }

    /*
     * SETTERS
     */
    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public void setBodyWater(Float bodyWater) {
        this.bodyWater = bodyWater;
    }

    public void setTargetWeight(Float targetWeight) {
        this.targetWeight = targetWeight;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public void setMuscleMass(Float muscleMass) {
        this.muscleMass = muscleMass;
    }

    public void setBodyFat(Float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMealStatus(Integer mealStatus) {
        this.mealStatus = mealStatus;
    }

    public void setBoneMass(Float boneMass) {
        this.boneMass = boneMass;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBodyWeight(Float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public void setAthletic(Integer athletic) {
        this.athletic = athletic;
    }
}
