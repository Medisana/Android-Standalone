package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Thermodock´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class Thermodock extends BaseModel {

    private String id;
    private Calendar measurementDate;
    private Float bodyTemperatureTargetMax;
    private String moduleSerialId;
    private Float bodyTemperatureTargetMin;
    private Calendar updatedDate;
    private Integer mood;
    private Boolean active;
    private Integer activityStatus;
    private String note;
    private Integer version;
    private Float bodyTemperature;

    /*
     * GETTERS
     */
    public String getId() {
        return id;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Float getBodyTemperatureTargetMax() {
        return bodyTemperatureTargetMax;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Float getBodyTemperatureTargetMin() {
        return bodyTemperatureTargetMin;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
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

    public String getNote() {
        return note;
    }

    public Integer getVersion() {
        return version;
    }

    public Float getBodyTemperature() {
        return bodyTemperature;
    }
    /*
     * SETTERS
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setBodyTemperatureTargetMax(Float bodyTemperatureTargetMax) {
        this.bodyTemperatureTargetMax = bodyTemperatureTargetMax;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setBodyTemperatureTargetMin(Float bodyTemperatureTargetMin) {
        this.bodyTemperatureTargetMin = bodyTemperatureTargetMin;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
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

    public void setNote(String note) {
        this.note = note;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setBodyTemperature(Float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }
}
