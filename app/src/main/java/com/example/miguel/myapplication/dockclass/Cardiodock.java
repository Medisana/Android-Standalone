package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of CardioDock´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muoz.
 */
public class Cardiodock extends BaseModel {

    private Calendar measurementDate;
    private Integer systoleTargetMax;
    private Integer pulseTargetMin;
    private Integer pulseTargetMax;
    private String moduleSerialId;
    private Integer systoleTargetMin;
    private Integer diastole;
    private Calendar updatedDate;
    private Integer diastoleTargetMin;
    private Integer systole;
    private Integer mood;
    private Integer pulse;
    private Integer type;
    private Integer arrhythmic;
    private Integer diastoleTargetMax;
    private Integer version;
    private String id;
    private Boolean active;
    private Integer activityStatus;
    private String note;

    /*
    GETTERS
     */
    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Integer getSystoleTargetMax() {
        return systoleTargetMax;
    }

    public Integer getPulseTargetMin() {
        return pulseTargetMin;
    }

    public Integer getPulseTargetMax() {
        return pulseTargetMax;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Integer getSystoleTargetMin() {
        return systoleTargetMin;
    }

    public Integer getDiastole() {
        return diastole;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getDiastoleTargetMin() {
        return diastoleTargetMin;
    }

    public Integer getSystole() {
        return systole;
    }

    public Integer getMood() {
        return mood;
    }

    public Integer getPulse() {
        return pulse;
    }

    public Integer getType() {
        return type;
    }

    public Integer getArrhythmic() {
        return arrhythmic;
    }

    public Integer getDiastoleTargetMax() {
        return diastoleTargetMax;
    }

    public Integer getVersion() {
        return version;
    }

    public String getId() {
        return id;
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
    SETTERS
     */
    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setSystoleTargetMax(Integer systoleTargetMax) {
        this.systoleTargetMax = systoleTargetMax;
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

    public void setSystoleTargetMin(Integer systoleTargetMin) {
        this.systoleTargetMin = systoleTargetMin;
    }

    public void setDiastole(Integer diastole) {
        this.diastole = diastole;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setDiastoleTargetMin(Integer diastoleTargetMin) {
        this.diastoleTargetMin = diastoleTargetMin;
    }

    public void setSystole(Integer systole) {
        this.systole = systole;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setArrhythmic(Integer arrhythmic) {
        this.arrhythmic = arrhythmic;
    }

    public void setDiastoleTargetMax(Integer diastoleTargetMax) {
        this.diastoleTargetMax = diastoleTargetMax;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(String id) {
        this.id = id;
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
