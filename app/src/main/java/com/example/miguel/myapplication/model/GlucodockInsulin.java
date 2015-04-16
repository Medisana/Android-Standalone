package com.example.miguel.myapplication.model;

import java.util.Calendar;

/**
 * Class used to facilitate the storage of Glucodock Insulin´s data returned from the server. Extends BaseModel.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class GlucodockInsulin extends BaseModel {

    private String id;
    private String insulinTypeName2;
    private Calendar measurementDate;
    private Integer insulinTypeIndex;
    private String moduleSerialId;
    private Calendar updatedDate;
    private Float insulin;
    private String insulinTypeName3;
    private String insulinTypeName;
    private Boolean active;
    private Integer version;

    /*
     * GETTERS
     */
    public String getId() {
        return id;
    }

    public Object getInsulinTypeName2() {
        return insulinTypeName2;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public Integer getInsulinTypeIndex() {
        return insulinTypeIndex;
    }

    public Object getModuleSerialId() {
        return moduleSerialId;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public Float getInsulin() {
        return insulin;
    }

    public Object getInsulinTypeName3() {
        return insulinTypeName3;
    }

    public String getInsulinTypeName() {
        return insulinTypeName;
    }

    public Boolean getActive() {
        return active;
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

    public void setInsulinTypeName2(String insulinTypeName2) {
        this.insulinTypeName2 = insulinTypeName2;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setInsulinTypeIndex(Integer insulinTypeIndex) {
        this.insulinTypeIndex = insulinTypeIndex;
    }

    public void setModuleSerialId(String moduleSerialId) {
        this.moduleSerialId = moduleSerialId;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setInsulin(Float insulin) {
        this.insulin = insulin;
    }

    public void setInsulinTypeName3(String insulinTypeName3) {
        this.insulinTypeName3 = insulinTypeName3;
    }

    public void setInsulinTypeName(String insulinTypeName) {
        this.insulinTypeName = insulinTypeName;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
