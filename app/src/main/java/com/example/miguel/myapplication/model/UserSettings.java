package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of User´s Settings. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class UserSettings extends BaseModel {

    private Calendar measurementDate;
    private Calendar birthday;
    private String sex;
    private String personalMessages;
    private String moduleSerialId;
    private Calendar updatedDate;
    private Integer goalSteps;
    private Float stride;
    private Integer version;
    private String id;
    private Float height;
    private Boolean active;
    private String lengthUnit;
    private Integer bodyWeight;

    /*
     * GETTERS
     */

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public String getPersonalMessages() {
        return personalMessages;
    }

    public String getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Integer getGoalSteps() {
        return goalSteps;
    }

    public Float getStride() {
        return stride;
    }

    public Integer getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public Float getHeight() {
        return height;
    }

    public Boolean getActive() {
        return active;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public Integer getBodyWeight() {
        return bodyWeight;
    }

    /*
     * SETTERS
     */

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPersonalMessages(String personalMessages) {
        this.personalMessages = personalMessages;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setGoalSteps(Integer goalSteps) {
        this.goalSteps = goalSteps;
    }

    public void setStride(Float stride) {
        this.stride = stride;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setLengthUnit(String lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public void setBodyWeight(Integer bodyWeight) {
        this.bodyWeight = bodyWeight;
    }
}
