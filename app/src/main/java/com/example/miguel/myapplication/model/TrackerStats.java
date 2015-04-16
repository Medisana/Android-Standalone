package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Tracker Stat´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz
 */
public class TrackerStats extends BaseModel{

    private Integer sleepQuality;
    private Calendar measurementDate;
    private String moduleSerialId;
    private Integer runningSteps;
    private Calendar updatedDate;
    private Integer durationSleep;
    private Integer steps;
    private Integer goodSleepQualityDuration;
    private Integer version;
    private String id;
    private Integer mediumSleepQualityDuration;
    private Double distance;
    private Integer badSleepQualityDuration;
    private Boolean active;
    private Integer excellentSleepQualityDuration;
    private Integer calories;
    private Integer durationActivity;

    /*
     * GETTERS
     */
    public Integer getSleepQuality() {
        return sleepQuality;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Integer getRunningSteps() {
        return runningSteps;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getDurationSleep() {
        return durationSleep;
    }

    public Integer getSteps() {
        return steps;
    }

    public Integer getGoodSleepQualityDuration() {
        return goodSleepQualityDuration;
    }

    public Integer getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public Integer getMediumSleepQualityDuration() {
        return mediumSleepQualityDuration;
    }

    public Double getDistance() {
        return distance;
    }

    public Integer getBadSleepQualityDuration() {
        return badSleepQualityDuration;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getExcellentSleepQualityDuration() {
        return excellentSleepQualityDuration;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getDurationActivity() {
        return durationActivity;
    }

    /*
     * SETTERS
     */
    public void setSleepQuality(Integer sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setRunningSteps(Integer runningSteps) {
        this.runningSteps = runningSteps;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setDurationSleep(Integer durationSleep) {
        this.durationSleep = durationSleep;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public void setGoodSleepQualityDuration(Integer goodSleepQualityDuration) {
        this.goodSleepQualityDuration = goodSleepQualityDuration;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMediumSleepQualityDuration(Integer mediumSleepQualityDuration) {
        this.mediumSleepQualityDuration = mediumSleepQualityDuration;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setBadSleepQualityDuration(Integer badSleepQualityDuration) {
        this.badSleepQualityDuration = badSleepQualityDuration;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setExcellentSleepQualityDuration(Integer excellentSleepQualityDuration) {
        this.excellentSleepQualityDuration = excellentSleepQualityDuration;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setDurationActivity(Integer durationActivity) {
        this.durationActivity = durationActivity;
    }
}
