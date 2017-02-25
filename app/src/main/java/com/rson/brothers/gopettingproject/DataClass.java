package com.rson.brothers.gopettingproject;

/**
 * Created by Brother on 2/24/2017.
 */
public class DataClass {

    private String TOTAL="total";
    private String STARTDATE="startDate";
    private String OBJTYPE="objType";
    private String ENDDATE="endDate";
    private String NAME="name";
    private String LOGIN="loginRequired";
    private String URL="url";
    private String VENUE="venue";
    private String ICON="icon";
    private String DATA="data";

    public DataClass(){

    }

    public DataClass( String TOTALm,
             String STARTDATEm,
             String OBJTYPEm,
             String ENDDATEm,
            String NAMEm,
             String LOGINm,
             String URLm,
            String VENUEm,
            String ICONm,
             String DATAm){

        TOTAL=TOTALm;
         STARTDATE=STARTDATEm;
        OBJTYPE=OBJTYPEm;
        ENDDATE=ENDDATEm;
         NAME=NAMEm;
         LOGIN=LOGINm;
         URL=URLm;
         VENUE=VENUEm;
         ICON=ICONm;
         DATA=DATAm;


    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getSTARTDATE() {
        return STARTDATE;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public String getOBJTYPE() {
        return OBJTYPE;
    }

    public void setOBJTYPE(String OBJTYPE) {
        this.OBJTYPE = OBJTYPE;
    }

    public String getENDDATE() {
        return ENDDATE;
    }

    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getVENUE() {
        return VENUE;
    }

    public void setVENUE(String VENUE) {
        this.VENUE = VENUE;
    }

    public String getICON() {
        return ICON;
    }

    public void setICON(String ICON) {
        this.ICON = ICON;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

   /* public String toString(){
        return
    }*/
}
