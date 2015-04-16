package com.example.miguel.myapplication.dockclass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class used to facilitate the storage of Traker Phase´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TrackerPhase extends BaseModel{

    private String id;
    private Calendar measurementDate;
    private Boolean active;
    private List<TrackerPhaseEntry> trackerPhaseEntries = new ArrayList<>();
    private String moduleSerialId;
    private Calendar updatedDate;
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

    public List<TrackerPhaseEntry> getTrackerPhaseEntries() {
        return trackerPhaseEntries;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
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

    public void setTrackerPhaseEntries(List<TrackerPhaseEntry> trackerPhaseEntries) {
        this.trackerPhaseEntries = trackerPhaseEntries;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
