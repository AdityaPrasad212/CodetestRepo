package com.codetest.assignMnyTap.home.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.codetest.assignMnyTap.R;
import com.codetest.assignMnyTap.home.contract.ClsHomeContract;
import com.codetest.assignMnyTap.home.presenter.ClsHomePresenter;




/*
  Application MnyTapAssign
  Filename ClsHomeActivity.java
  Description: ClsHomeActivity class extending AppCompactActivity and is home screen.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class ClsHomeActivity extends AppCompatActivity implements ClsHomeContract.RequiredViewOps {

    private ClsHomeContract.ProvidedPresenterOps mPresenter;
    private LinearLayout linear_parent;


    /**************************************************************************************
     * Function[onCreate] - call back method. This method is used for setting view for an activity
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param savedInstanceState : bundle of saved data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Window window = this.getWindow();
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.color_system_bar));

            setContentView(R.layout.home_activity_main);
            setupMVP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[setupMVP] - function for setting up the mvp pattern. Creating an instance of presenter class.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    private void setupMVP() {
        // Create the Presenter
        mPresenter = new ClsHomePresenter(this);
    }


    /**************************************************************************************
     * Function[registerViews] - function for registering the views for the activity
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    private void registerViews() {
        try {
            linear_parent = (LinearLayout) findViewById(R.id.linear_parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[setActionBar] - function for setting up the action bar for the activity
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    private void setActionBar() {
        try {
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                //   actionBar.setDisplayHomeAsUpEnabled(true);
                Drawable drawable_actionbar = ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawable_actionbar);
                actionBar.setBackgroundDrawable(drawable_actionbar);

                // setting action bar title text color
                Spannable text = new SpannableString(actionBar.getTitle());
                text.setSpan(new ForegroundColorSpan(getColor(this, R.color.colorWhite)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                actionBar.setTitle(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**************************************************************************************
     * Function[onCreateOptionsMenu] - call back method. Inflate the menu; this adds items to the action bar if it is present.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param menu : menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        try {
            // Inflate the menu
            getMenuInflater().inflate(R.menu.actionbar_menu, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when action item collapses
                    ActionBar actionBar = getSupportActionBar();

                    //Setting the action bar background color
                    if (actionBar != null) {
                        Drawable drawable_actionbar = ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawable_actionbar);
                        actionBar.setBackgroundDrawable(drawable_actionbar);
                    }
                    if (mPresenter != null) {
                        mPresenter.clearSearchData();
                    }

                    return true;  // Return true to collapse action view
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    ActionBar actionBar = getSupportActionBar();
                    //Setting the action bar background color
                    if (actionBar != null) {
                        Drawable drawable_actionbar = ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawable_actionbar_search);
                        actionBar.setBackgroundDrawable(drawable_actionbar);
                    }
                    return true;  // Return true to expand action view
                }
            });


            final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText != null)
                        if (mPresenter != null) {
                            mPresenter.searchQuery(ClsHomeActivity.this, newText);
                        }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (query != null) {
                        if (mPresenter != null) {
                            mPresenter.searchQuery(ClsHomeActivity.this, query);
                        }
                    }
                    return true;
                }
            };

            // Setting searchview with on Query Text Listener
            searchView.setOnQueryTextListener(queryTextListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**************************************************************************************
     * Function[onOptionsItemSelected] - call back method gets called when user clicks on menu items
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param item : MenuItem
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.action_search:
                    // User chose the "Search" action, mark the current item
                    return true;


                case android.R.id.home:
                    onBackPressed();
                    return true;

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**************************************************************************************
     * Function[getColor] - returns the color value.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param context : activity context
     * @param :       color resource ID
     */

    private int getColor(Context context, int resId) {
        try {
            int version = Build.VERSION.SDK_INT;
            if (version >= 23) {
                return ContextCompat.getColor(context, resId);
            } else {
                return context.getResources().getColor(resId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**************************************************************************************
     * Function[getAppContext] - returns application context
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }


    /**************************************************************************************
     * Function[getActivityContext] - returns the activity context.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    @Override
    public Context getActivityContext() {
        return this;
    }


    /**************************************************************************************
     * Function[initViews] - initializes view for the activity
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     */
    @Override
    public void initViews() {
        registerViews();
        setActionBar();
    }


    /**************************************************************************************
     * Function[addListingToParentView] - function for adiing the listing view to the parent
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 29,2018.
     *
     * @param listingView : listing view
     */
    @Override
    public void addListingToParentView(View listingView) {
        try {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.leftMargin = 2;
            params.rightMargin = 2;
            linear_parent.setVisibility(View.VISIBLE);
            linear_parent.removeAllViews();
            linear_parent.addView(listingView, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*End of file*/