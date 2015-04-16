package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Cardiodock Glucose´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz
 */
public class GlucodockGlucose extends BaseModel {

    private String id;
    private Calendar measurementDate;
    private Integer bloodGlucose;
    private String moduleSerialId;
    private Integer bloodGlucoseTargetMax;
    private Calendar updatedDate;
    private Integer mealStatus;
    private Integer mood;
    private Boolean active;
    private Integer activityStatus;
    private Integer bloodGlucoseTargetMin;
    private String note;
    private Integer version;

    /*
    GETTERS
     */
    public String getId() {
        return id;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Integer getBloodGlucose() {
        return bloodGlucose;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Integer getBloodGlucoseTargetMax() {
        return bloodGlucoseTargetMax;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getMealStatus() {
        return mealStatus;
    }

    public Integer getMood() {
        return mood;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public Integer getBloodGlucoseTargetMin() {
        return bloodGlucoseTargetMin;
    }

    public String getNote() {
        return note;
    }

    public Integer getVersion() {
        return version;
    }

    /*
    SETTERS
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setBloodGlucose(Integer bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setBloodGlucoseTargetMax(Integer bloodGlucoseTargetMax) {
        this.bloodGlucoseTargetMax = bloodGlucoseTargetMax;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setMealStatus(Integer mealStatus) {
        this.mealStatus = mealStatus;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setBloodGlucoseTargetMin(Integer bloodGlucoseTargetMin) {
        this.bloodGlucoseTargetMin = bloodGlucoseTargetMin;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
