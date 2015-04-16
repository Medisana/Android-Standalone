package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Glucose´s settings. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class GlucoseSettings extends BaseModel {

    private String id;
    private Calendar measurementDate;
    private String moduleSerialId;
    private Integer bloodGlucoseTargetMax;
    private Calendar updatedDate;
    private Boolean active;
    private Integer bloodGlucoseTargetMin;
    private String glucoseMeasureUnit;
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

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Integer getBloodGlucoseTargetMax() {
        return bloodGlucoseTargetMax;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getBloodGlucoseTargetMin() {
        return bloodGlucoseTargetMin;
    }

    public String getGlucoseMeasureUnit() {
        return glucoseMeasureUnit;
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

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setBloodGlucoseTargetMax(Integer bloodGlucoseTargetMax) {
        this.bloodGlucoseTargetMax = bloodGlucoseTargetMax;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setBloodGlucoseTargetMin(Integer bloodGlucoseTargetMin) {
        this.bloodGlucoseTargetMin = bloodGlucoseTargetMin;
    }

    public void setGlucoseMeasureUnit(String glucoseMeasureUnit) {
        this.glucoseMeasureUnit = glucoseMeasureUnit;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
