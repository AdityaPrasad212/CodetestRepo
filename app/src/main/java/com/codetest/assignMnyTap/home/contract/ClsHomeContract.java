package com.codetest.assignMnyTap.home.contract;

import android.app.Activity;
import android.content.Context;
import android.view.View;
/*
  Application MnyTapAssign
  Filename ClsHomeContract.java
  Description: ClsHomeContract class is a contract for model, view , presenter classes.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class ClsHomeContract {


    /**************************************************************************************
     * Interface[RequiredViewOps]
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    public interface RequiredViewOps {
        // View operations permitted to Presenter
        Context getAppContext();

        Context getActivityContext();

        void initViews();

        void addListingToParentView(View listingView);

    }

    /**************************************************************************************
     * Interface[ProvidedPresenterOps]
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     * <p>
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    public interface ProvidedPresenterOps {
        // Presenter operations permitted to View
        void searchQuery(Activity activity, String strQuery);

        void clearSearchData();
    }


    /**************************************************************************************
     * Interface[RequiredPresenterOps]
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     * <p>
     * Required Presenter methods available to Model.
     */
    public interface RequiredPresenterOps {
        // Presenter operations permitted to Model
        Context getAppContext();

        Context getActivityContext();

        void onSearchQueryResult(Activity activity, String strQuery, String strResult);

    }


    /**************************************************************************************
     * Interface[ProvidedModelOps]
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     * <p>
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     */
    public interface ProvidedModelOps {
        // Model operations permitted to Presenter
        void callSearchQueryApi(Activity activity, String strQuery);
    }


}

/*End of file */