package com.codetest.assignMnyTap.home.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;

import com.codetest.assignMnyTap.Listing.ClsListItemValues;
import com.codetest.assignMnyTap.Listing.ClsSearchData;
import com.codetest.assignMnyTap.Listing.ClsSearchListingEntry;
import com.codetest.assignMnyTap.R;
import com.codetest.assignMnyTap.home.contract.ClsHomeContract;
import com.codetest.assignMnyTap.home.model.ClsHomeModel;
import com.codetest.assignMnyTap.webview.ClsWebViewActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static com.codetest.assignMnyTap.Listing.ClsLibUtilities.getColorFromRes;

/*
  Application MnyTapAssign
  Filename ClsHomePresenter.java
  Description: ClsHomePresenter class where we handle the logic code. It acts as communicaton bridge between view and model.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */
public class ClsHomePresenter implements ClsHomeContract.ProvidedPresenterOps, ClsHomeContract.RequiredPresenterOps, ClsSearchListingEntry.ItemClickListener {

    /* View reference. We use as a WeakReference
      because the Activity could be destroyed at any time
     and we don't want to create a memory leak
     */
    private WeakReference<ClsHomeContract.RequiredViewOps> mView;

    // Model reference
    private ClsHomeContract.ProvidedModelOps mModel;
    private ClsSearchListingEntry clsSearchListingEntry;


    /**************************************************************************************
     * Constructor[ClsHomePresenter] - ClsHomePresenter constructor
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param view MainActivity
     */
    public ClsHomePresenter(ClsHomeContract.RequiredViewOps view) {
        try {
            mView = new WeakReference<>(view);
            initPresenter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**************************************************************************************
     * Function[initPresenter] - function for creating an object of ClsHomeModel class and initializing views in activity
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     **************************************************************************************/
    private void initPresenter() {
        try {
            mModel = new ClsHomeModel(this);
            getView().initViews();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[getView] - Return the View reference.Throw an exception if the View is unavailable.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     **************************************************************************************/

    private ClsHomeContract.RequiredViewOps getView() throws NullPointerException {
        try {
            if (mView != null)
                return mView.get();
            else
                throw new NullPointerException("View is unavailable");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**************************************************************************************
     * Function[getAppContext] - Return the application context.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @return Application context
     **************************************************************************************/
    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**************************************************************************************
     * Function[getActivityContext] - Return the Activity context.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @return Activity context
     **************************************************************************************/
    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            return null;
        }
    }


    /**************************************************************************************
     * Function[onSearchQueryResult] - call back method for handling the search query api response
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity  : Activity instance
     * @param strResult : search query api response
     */
    @Override
    public void onSearchQueryResult(Activity activity, String strQuery, String strResult) {
        try {
            if (strResult != null) {
                ArrayList<ClsSearchData> clsSearchDataArrayList = new ArrayList<>();
                boolean isValidQuery = false;
                if (strQuery != null && strQuery.length() > 0) {
                    clsSearchDataArrayList = parseSearchResult(strResult);
                    isValidQuery = true;
                }
                if (clsSearchListingEntry != null) {
                    clsSearchListingEntry.upateArrayListAndResetAdapter(clsSearchDataArrayList, isValidQuery);
                } else {
                    addMenuListingToParent(activity, clsSearchDataArrayList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[addMenuListingToParent] - function adds listing view to parent
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity          : Activity instance
     * @param menuDataArrayList : arraylist of type ClsSearchData of Search Results
     */
    private void addMenuListingToParent(Activity activity, ArrayList<ClsSearchData> menuDataArrayList) {
        try {
            View listingView = getListingView(activity, menuDataArrayList);
            if (listingView != null)
                getView().addListingToParentView(listingView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[getListingView] - function which gets listing view
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity          : Activity instance
     * @param menuDataArrayList : arraylist of type ClsSearchData for showing in listing
     */
    private View getListingView(Activity activity, ArrayList<ClsSearchData> menuDataArrayList) {
        try {
            if (clsSearchListingEntry != null) {
                return clsSearchListingEntry.getSearchTitlesListView();
            } else {
                clsSearchListingEntry = new ClsSearchListingEntry(activity,
                        getActivityContext(), this,
                        menuDataArrayList, initListItemValues());
                return clsSearchListingEntry.getSearchTitlesListView();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**************************************************************************************
     * Function[parseSearchResult] - function parsing the search query api response which is in json format. Converts json data into Arraylist of ClsSearchData
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param strSearchResult : search query api response
     * @return ArrayList<ClsSearchData>
     */
    private ArrayList<ClsSearchData> parseSearchResult(String strSearchResult) {
        ArrayList<ClsSearchData> arrayList = new ArrayList<>();
        try {
            JSONObject objJson = new JSONObject(strSearchResult);
            JSONObject objJsonQuery = objJson.optJSONObject("query");
            if (objJsonQuery != null) {
                JSONArray pagesArray = objJsonQuery.optJSONArray("pages");
                if (pagesArray != null) {
                    for (int i = 0; i < pagesArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) pagesArray.get(i);

                        if (jsonObject != null) {
                            String strTitle = jsonObject.optString("title");
                            int intPageID = jsonObject.optInt("pageid");
                            String strDescription = "";
                            String strSrcThumbnailUrl = "";
                            int intThumbnailWidth;
                            int intThumbnailHeight;
                            String strEditedTimeStamp = "";

                            if (jsonObject.has("thumbnail")) {
                                JSONObject thumbnailJsonObj = jsonObject.optJSONObject("thumbnail");
                                if (thumbnailJsonObj != null) {
                                    strSrcThumbnailUrl = thumbnailJsonObj.optString("source");
                                    intThumbnailWidth = thumbnailJsonObj.optInt("width");
                                    intThumbnailHeight = thumbnailJsonObj.optInt("height");
                                }
                            }

                            if (jsonObject.has("terms")) {
                                JSONObject termsJsonObj = jsonObject.optJSONObject("terms");
                                if (termsJsonObj != null) {
                                    JSONArray descriptionArray = termsJsonObj.optJSONArray("description");
                                    if (descriptionArray != null) {
                                        for (int j = 0; j < descriptionArray.length(); j++) {
                                            strDescription = descriptionArray.get(j).toString();
                                        }
                                    }
                                }
                            }


                            if (jsonObject.has("revisions")) {
                                JSONArray revisionsJsonArray = jsonObject.optJSONArray("revisions");
                                if (revisionsJsonArray != null && revisionsJsonArray.length() > 0) {
                                    for (int k = 0; k < revisionsJsonArray.length(); k++) {
                                        JSONObject timeStampJsonObj = (JSONObject) revisionsJsonArray.get(k);
                                        if (timeStampJsonObj != null) {
                                            strEditedTimeStamp = timeStampJsonObj.optString("timestamp");
                                            if (strEditedTimeStamp != null) {
                                                break;
                                            }
                                        }

                                    }
                                }
                            }
                            ClsSearchData clsSearchData = new ClsSearchData();
                            clsSearchData.setStrNameTxt(strTitle);
                            clsSearchData.setStrPageID(String.valueOf(intPageID));
                            clsSearchData.setStrDesTxt(strDescription);
                            clsSearchData.setSeperator(true);
                            clsSearchData.setStrThumbnailUrl(strSrcThumbnailUrl);
                            clsSearchData.setStrEditedTimeStamp(getDateFormat(strEditedTimeStamp));
                            arrayList.add(clsSearchData);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


    /**************************************************************************************
     * Function[searchQuery] - call back method for searching a query.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity : Activity instance
     * @param strQuery : search query
     */
    @Override
    public void searchQuery(Activity activity, String strQuery) {
        try {
            if (mModel != null) {
                mModel.callSearchQueryApi(activity, strQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**************************************************************************************
     * Function[clearSearchData] - function that clears the previous searchdata
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    @Override
    public void clearSearchData() {
        try {
            if (clsSearchListingEntry != null) {
                clsSearchListingEntry.clearArrayListAndResetAdapter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[initListItemValues] - function used to assign and intialize values that are used for listing and list item views.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    private ClsListItemValues initListItemValues() {
        try {
            return new ClsListItemValues(getColorFromRes(getActivityContext(), R.color.textcolor), Typeface.NORMAL, 16, getColorFromRes(getActivityContext(), R.color.greyColor),
                    Typeface.NORMAL, 14, getColorFromRes(getActivityContext(), R.color.greyColor),
                    Typeface.NORMAL, 10,
                    48, getColorFromRes(getActivityContext(), R.color.lightGray),
                    getColorFromRes(getActivityContext(), R.color.colorWhite));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**************************************************************************************
     * Function[onListItemClick] - call back function when user clicks on list item
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity      : Activity instance
     * @param view          : view on which user clicked
     * @param position:     position of item clicked in listing
     * @param clsSearchData : object of search data the item clicked
     */
    @Override
    public void onListItemClick(Activity activity, View view, int position, ClsSearchData clsSearchData) {
        try {
            if (activity != null) {
                Intent intent = new Intent(activity, ClsWebViewActivity.class);
                intent.putExtra("title", clsSearchData.getStrNameTxt());
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**************************************************************************************
     * Function[getDateFormat] - function that returns the date and time in required format
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param strSetDate :  "yyyy-MM-dd'T'HH:mm:ss'Z'" format date value
     */
    private String getDateFormat(String strSetDate) {
        try {
            String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            SimpleDateFormat spf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
            Date newDate = null;
            try {
                newDate = spf.parse(strSetDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            spf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            strSetDate = spf.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strSetDate;
    }


}

/*End of file*/