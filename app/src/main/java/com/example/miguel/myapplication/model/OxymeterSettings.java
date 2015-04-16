package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of OxyMeter´s settings. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class OxymeterSettings extends BaseModel {

    private String id;
    private Calendar measurementDate;
    private Integer pulseTargetMin;
    private Integer pulseTargetMax;
    private String moduleSerialId;
    private Integer saturationTargetMax;
    private Calendar updatedDate;
    private Boolean active;
    private Integer saturationTargetMin;
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

    public Integer getPulseTargetMin() {
        return pulseTargetMin;
    }

    public Integer getPulseTargetMax() {
        return pulseTargetMax;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Integer getSaturationTargetMax() {
        return saturationTargetMax;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getSaturationTargetMin() {
        return saturationTargetMin;
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

    public void setPulseTargetMin(Integer pulseTargetMin) {
        this.pulseTargetMin = pulseTargetMin;
    }

    public void setPulseTargetMax(Integer pulseTargetMax) {
        this.pulseTargetMax = pulseTargetMax;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setSaturationTargetMax(Integer saturationTargetMax) {
        this.saturationTargetMax = saturationTargetMax;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setSaturationTargetMin(Integer saturationTargetMin) {
        this.saturationTargetMin = saturationTargetMin;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
