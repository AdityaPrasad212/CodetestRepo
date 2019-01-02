package com.codetest.assignMnyTap.apicalls;

/**
 * Created by User on 8/3/2016.
 */

/*
  Application MnyTapAssign
  Filename BuiltUrlParameters.java
  Description: BuiltUrlParameters class builds the url parameter according to the request type
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class BuiltUrlParameters {

    RequestEnum requestEnum;
    String urlParameters = null;


    /**************************************************************************************
     * Constructor[BuiltUrlParameters] - BuiltUrlParameters constructor
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param requestEnum : enum type for building params string
     */

    public BuiltUrlParameters(RequestEnum requestEnum) {
        this.requestEnum = requestEnum;
    }


    /**************************************************************************************
     * Function [buildParameter] - returns params string for the requested enum value
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @return url Parameters String
     */
    public String buildParameter(String... params) {
        switch (requestEnum) {
 /*
 action=query
formatversion=2
generator=prefixsearch
gpssearch=Albert%20Ei
gpslimit=10
prop=pageimages|pageterms
piprop=thumbnail
pithumbsize=50
pilimit=10
redirects=
wbptterms=description
 */
            case search:
                urlParameters = "action=query" + "&formatversion=2" + "&generator=prefixsearch"
                        + "&gpssearch=" + params[0] + "&gpslimit=10" + "&prop=pageimages|pageterms|revisions"
                        + "&piprop=thumbnail" + "&pithumbsize=50"
                        + "&pilimit=10" + "&redirects=" + params[1] + "&wbptterms=description" + "&format=json"+"&rvprop=timestamp";
                break;
        }
        return urlParameters;
    }
}
/*End of File*/