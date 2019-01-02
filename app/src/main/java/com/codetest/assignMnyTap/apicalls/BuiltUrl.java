package com.codetest.assignMnyTap.apicalls;

/*
  Application MnyTapAssign
  Filename BuiltUrl.java
  Description: BuiltUrl class simply builds the base url.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class BuiltUrl {

    private RequestEnum requestEnum;
    private String BASE_URL = "https://en.wikipedia.org";

    /**************************************************************************************
     * Constructor[BuiltUrl] - BuiltUrl constructor
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param requestEnum : enum type for building base url
     */

    public BuiltUrl(RequestEnum requestEnum) {
        this.requestEnum = requestEnum;
    }


    /**************************************************************************************
     * Function [buildUrl] - returns a url of type string for the respected enum value
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @return base url String
     */
    public String buildUrl() {
        switch (requestEnum) {

            case search:
                BASE_URL += "/w/api.php?";
                break;
        }
        return BASE_URL;
    }
}
/*End of File*/
