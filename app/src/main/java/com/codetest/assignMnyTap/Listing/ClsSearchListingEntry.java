package com.codetest.assignMnyTap.Listing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.codetest.assignMnyTap.R;

import java.util.ArrayList;

/*
  Application MnyTapAssign
  Filename ClsSearchListingEntry.java
  Description: ClsSearchListingEntry class used to access all functions related to creating and updating a listing view
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class ClsSearchListingEntry {
    private Context context;
    private Activity activity;
    private View srchTitleListView;
    private ArrayList<ClsSearchData> srchTitleDataArrayList;
    private RecyclerView listRecycler;
    private ItemClickListener itemClickListener;
    private ClsListItemValues clsListItemValues;
    private ImageView noSearchResults;

    /**************************************************************************************
     * Constructor[ClsSearchListingEntry] - ClsSearchListingEntry constructor
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param context                : context
     * @param activity               : activity instance
     * @param itemClickListener      : instance of ItemClickListener interface
     * @param srchTitleDataArrayList : Arraylist of type ClsSearchData
     * @param clsListItemValues      : instance of ClsListItemValues which has list item data
     */

    public ClsSearchListingEntry(Activity activity, Context context, ItemClickListener itemClickListener, ArrayList<ClsSearchData> srchTitleDataArrayList, ClsListItemValues clsListItemValues) {
        this.context = context;
        this.activity = activity;
        this.srchTitleDataArrayList = srchTitleDataArrayList;
        this.clsListItemValues = clsListItemValues;
        this.itemClickListener = itemClickListener;
    }


    /**************************************************************************************
     * Function[getSearchTitlesListView] - function for creating the listing view and returns the view
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     **************************************************************************************/
    public View getSearchTitlesListView() throws IndexOutOfBoundsException {
        try {
            LayoutInflater objInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            srchTitleListView = objInflater.inflate(R.layout.list_recycler_list, null);
            listRecycler = (RecyclerView) srchTitleListView.findViewById(R.id.recycler_view);
            noSearchResults = (ImageView) srchTitleListView.findViewById(R.id.img_noresults);
            listRecycler.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(context));
            listRecycler.setLayoutManager(linearLayoutManager);
            listRecycler.setItemAnimator(new DefaultItemAnimator());
            setSrchTitleRecyclerAdapter(srchTitleDataArrayList, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srchTitleListView;
    }


    /**************************************************************************************
     * Function[setSrchTitleRecyclerAdapter] - function for setting the recycler adapter
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 30,2018.
     *
     * @param dataArrayList : ArrayList<ClsSearchData>
     **************************************************************************************/
    private void setSrchTitleRecyclerAdapter(final ArrayList<ClsSearchData> dataArrayList, boolean blnClearResetAdapter, boolean blnIsValidQuery) {
        try {

            if (dataArrayList != null) {
                if (dataArrayList.size() <= 0 && !blnClearResetAdapter && blnIsValidQuery) {
                    noSearchResults.setVisibility(View.VISIBLE);
                    Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.noresults);
                    noSearchResults.setImageBitmap(bm);
                    listRecycler.setVisibility(View.GONE);
                } else {
                    noSearchResults.setVisibility(View.GONE);
                    noSearchResults.setImageBitmap(null);
                    listRecycler.setVisibility(View.VISIBLE);
                }
            }

            // Init recycler adapter
            ClsSrchTitlesRecyclerAdapter srchTitlesRecyclerAdapter = new ClsSrchTitlesRecyclerAdapter(context, activity, dataArrayList, clsListItemValues, new ClsSrchTitlesRecyclerAdapter.OnViewClick() {
                @Override
                public void onListItemClick(View view, int position) {
                    itemClickListener.onListItemClick(activity, view, position, dataArrayList.get(position));
                }
            }
            );

            // setting the adapter to recycler view
            listRecycler.setAdapter(srchTitlesRecyclerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[upateArrayListAndResetAdapter] - function for updating the arraylist and resetting the recycler adapter
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 30,2018.
     *
     * @param dataArrayList : ArrayList<ClsSearchData>
     **************************************************************************************/
    public void upateArrayListAndResetAdapter(ArrayList<ClsSearchData> dataArrayList, boolean blnIsValidQuery) {
        try {
            this.srchTitleDataArrayList.clear();
            this.srchTitleDataArrayList.addAll(dataArrayList);
            setSrchTitleRecyclerAdapter(srchTitleDataArrayList, false, blnIsValidQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[clearArrayListAndResetAdapter] - function for clearing the arraylist and resetting the recycler adapter
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 30,2018.
     **************************************************************************************/
    public void clearArrayListAndResetAdapter() {
        try {
            this.srchTitleDataArrayList.clear();
            setSrchTitleRecyclerAdapter(srchTitleDataArrayList, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Interface[ItemClickListener] - Interface that is used for handling the callbacks
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     **************************************************************************************/
    public interface ItemClickListener {
        void onListItemClick(Activity activity, View view, int position, ClsSearchData clsSearchData);
    }
}
/*End of file*/