package com.example.miguel.myapplication.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class used to facilitate the storage of Tracker Activity´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz
 */
public class TrackerActivity extends BaseModel{

    private String id;
    private Calendar measurementDate;
    private Boolean active;
    private String moduleSerialId;
    private Calendar updatedDate;
    private List<TrackerActivityEntry> trackerActivityEntries = new ArrayList<>();
    private Integer version;

    /*
     * GETTERS
     */

    public String getId() {
        return id;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Boolean getActive() {
        return active;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public List<TrackerActivityEntry> getTrackerActivityEntries() {
        return trackerActivityEntries;
    }

    public Integer getVersion() {
        return version;
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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setTrackerActivityEntries(List<TrackerActivityEntry> trackerActivityEntries) {
        this.trackerActivityEntries = trackerActivityEntries;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
