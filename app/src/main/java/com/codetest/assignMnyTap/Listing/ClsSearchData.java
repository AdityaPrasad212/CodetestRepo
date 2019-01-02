package com.codetest.assignMnyTap.Listing;

import java.io.Serializable;

/*
  Application MnyTapAssign
  Filename ClsSearchData.java
  Description: ClsSearchData class implements Serializable, it has getters and setters methods for setting the search data
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 31,2018.
  Updator Aditya Prasad
 */


public class ClsSearchData implements Serializable {

    private String strNameTxt, strThumbnailUrl, strPageID, strDesTxt;
    private boolean isSeperator;

    public String getStrEditedTimeStamp() {
        return strEditedTimeStamp;
    }

    public void setStrEditedTimeStamp(String strEditedTimeStamp) {
        this.strEditedTimeStamp = strEditedTimeStamp;
    }

    String strEditedTimeStamp;

    public String getStrPageID() {
        return strPageID;
    }

    public void setStrPageID(String strPageID) {
        this.strPageID = strPageID;
    }

    public String getStrDesTxt() {
        return strDesTxt;
    }

    public void setStrDesTxt(String strDesTxt) {
        this.strDesTxt = strDesTxt;
    }

    public String getStrThumbnailUrl() {
        return strThumbnailUrl;
    }

    public void setStrThumbnailUrl(String strThumbnailUrl) {
        this.strThumbnailUrl = strThumbnailUrl;
    }

    public boolean isSeperator() {
        return isSeperator;
    }

    public void setSeperator(boolean seperator) {
        isSeperator = seperator;
    }

    public String getStrNameTxt() {
        return strNameTxt;
    }

    public void setStrNameTxt(String strNameTxt) {
        this.strNameTxt = strNameTxt;
    }

    private final long serialVersionUID = 652965096267754563L;
}
/* End of File */