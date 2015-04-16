package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Target Scale´s settings. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class TargetScaleSettings extends BaseModel {

    private String id;
    private Boolean enterMode;
    private Calendar measurementDate;
    private String measureUnit;
    private String moduleSerialId;
    private Calendar updatedDate;
    private Boolean athleteMode;
    private Boolean active;
    private Integer targetWeight;
    private Integer version;

    /*
     * GETTERS
     */

    public String getId() {
        return id;
    }

    public Boolean getEnterMode() {
        return enterMode;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Boolean getAthleteMode() {
        return athleteMode;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getTargetWeight() {
        return targetWeight;
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

    public void setEnterMode(Boolean enterMode) {
        this.enterMode = enterMode;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setAthleteMode(Boolean athleteMode) {
        this.athleteMode = athleteMode;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setTargetWeight(Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
