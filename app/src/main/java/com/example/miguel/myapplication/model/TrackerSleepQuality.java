package com.example.miguel.myapplication.model;

/**
 * Class used to facilitate the storage of Tracker Sleep Quality´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz
 */
public class TrackerSleepQuality  extends BaseModel {

    private Integer startTime;
    private Integer sleepQuality;
    private Integer duration;

    /*
     * GETTERS
     */
    public Integer getStartTime() {
        return startTime;
    }

    public Integer getSleepQuality() {
        return sleepQuality;
    }

    public Integer getDuration() {
        return duration;
    }

    /*
     * SETTERS
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setSleepQuality(Integer sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
