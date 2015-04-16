package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of OxyMeter´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class Oxymeter extends BaseModel {

    private Calendar measurementDate;
    private Integer pulseTargetMin;
    private Integer pulseTargetMax;
    private String moduleSerialId;
    private Calendar updatedDate;
    private Integer mood;
    private Integer saturationTargetMin;
    private Integer pulse;
    private Integer version;
    private String id;
    private Integer saturationTargetMax;
    private Integer saturation;
    private Boolean active;
    private Integer activityStatus;
    private String note;

    /*
     * GETTERS
     */

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Integer getPulseTargetMin() {
        return pulseTargetMin;
    }

    public Integer getPulseTargetMax() {/**/
        return pulseTargetMax;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getMood() {
        return mood;
    }

    public Integer getSaturationTargetMin() {
        return saturationTargetMin;
    }

    public Integer getPulse() {
        return pulse;
    }

    public Integer getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public Integer getSaturationTargetMax() {
        return saturationTargetMax;
    }

    public Integer getSaturation() {
        return saturation;
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

    /*
     * SETTERS
     */

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setPulseTargetMin(Integer pulseTargetMin) {
        this.pulseTargetMin = pulseTargetMin;
    }

    public void setPulseTargetMax(Integer pulseTargetMax) {
        this.pulseTargetMax = pulseTargetMax;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public void setSaturationTargetMin(Integer saturationTargetMin) {
        this.saturationTargetMin = saturationTargetMin;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaturationTargetMax(Integer saturationTargetMax) {
        this.saturationTargetMax = saturationTargetMax;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
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
}
