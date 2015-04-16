package com.example.miguel.myapplication.model;

/**
 * Class used to facilitate the storage of Tracker Activity Entry´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz
 */
public class TrackerActivityEntry extends BaseModel {

    private Integer startTime;
    private Float calories;
    private Integer duration;
    private Float distance;
    private Integer steps;
    private Integer runningSteps;

    /*
     * GETTERS
     */
    public Integer getStartTime() {
        return startTime;
    }

    public Float getCalories() {
        return calories;
    }

    public Integer getDuration() {
        return duration;
    }

    public Float getDistance() {
        return distance;
    }

    public Integer getSteps() {
        return steps;
    }

    public Integer getRunningSteps() {
        return runningSteps;
    }

    /*
     * SETTERS
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public void setRunningSteps(Integer runningSteps) {
        this.runningSteps = runningSteps;
    }
}
