package com.example.miguel.myapplication.dockclass;

/**
 * Class used to facilitate the storage of Tracker Phase Entry´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TrackerPhaseEntry  extends BaseModel {

    private Float sleepQuality;
    private Integer startTime;
    private Float distance;
    private Integer duration;
    private Integer runningSteps;
    private Float calories;
    private Integer steps;
    private String type;

    /*
     * GETTERS
     */

    public Float getSleepQuality() {
        return sleepQuality;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Float getDistance() {
        return distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getRunningSteps() {
        return runningSteps;
    }

    public Float getCalories() {
        return calories;
    }

    public Integer getSteps() {
        return steps;
    }

    public String getType() {
        return type;
    }

    /*
     * SETTERS
     */

    public void setSleepQuality(Float sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setRunningSteps(Integer runningSteps) {
        this.runningSteps = runningSteps;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public void setType(String type) {
        this.type = type;
    }
}
