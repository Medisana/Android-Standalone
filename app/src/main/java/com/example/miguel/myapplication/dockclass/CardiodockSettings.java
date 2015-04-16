package com.example.miguel.myapplication.dockclass;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of CardioDock´s settings. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CardiodockSettings extends BaseModel {

    private String id;
    private Calendar measurementDate;
    private Integer systoleTargetMax;
    private Integer pulseTargetMin;
    private Integer pulseTargetMax;
    private String moduleSerialId;
    private Integer systoleTargetMin;
    private Calendar updatedDate;
    private Integer diastoleTargetMin;
    private Boolean active;
    private Integer diastoleTargetMax;
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

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getDiastoleTargetMin() {
        return diastoleTargetMin;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getDiastoleTargetMax() {
        return diastoleTargetMax;
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

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setDiastoleTargetMin(Integer diastoleTargetMin) {
        this.diastoleTargetMin = diastoleTargetMin;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setDiastoleTargetMax(Integer diastoleTargetMax) {
        this.diastoleTargetMax = diastoleTargetMax;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
