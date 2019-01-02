package com.codetest.assignMnyTap.home.model;

import android.app.Activity;

import com.codetest.assignMnyTap.apicalls.ApiPostCallTask;
import com.codetest.assignMnyTap.apicalls.BuiltUrl;
import com.codetest.assignMnyTap.apicalls.BuiltUrlParameters;
import com.codetest.assignMnyTap.apicalls.RequestEnum;
import com.codetest.assignMnyTap.home.contract.ClsHomeContract;


/*
  Application MnyTapAssign
  Filename ClsHomeModel.java
  Description: ClsHomeModel class is a contract for model, view , presenter classes.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */
public class ClsHomeModel implements ClsHomeContract.ProvidedModelOps {

    // Presenter reference
    private ClsHomeContract.RequiredPresenterOps mPresenter;


    /**************************************************************************************
     * Constructor[ClsHomeModel] -Main constructor, called by Activity during MVP setup
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param presenter Presenter instance
     */
    public ClsHomeModel(ClsHomeContract.RequiredPresenterOps presenter) {
        this.mPresenter = presenter;
    }


    /**************************************************************************************
     * Function[callSearchQueryApi] - call back method for calling a search query api
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param activity : Activity instance
     * @param strQuery : query string value
     */
    @Override
    public void callSearchQueryApi(final Activity activity, final String strQuery) {
        ApiPostCallTask searchQueryApi = null;
        try {
            searchQueryApi = new ApiPostCallTask() {
                @Override
                protected void onPostExecute(String result) {
                    try {
                        if (result != null) {
                            if (mPresenter != null) {
                                mPresenter.onSearchQueryResult(activity, strQuery, result);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchQueryApi.execute(new BuiltUrl(RequestEnum.search).buildUrl(), new BuiltUrlParameters(RequestEnum.search).buildParameter(strQuery, ""));
    }


}

/*End of file*/