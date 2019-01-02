package com.codetest.assignMnyTap.Listing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.codetest.assignMnyTap.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.codetest.assignMnyTap.Listing.ClsLibUtilities.dpTopx;

/*
 Application MnyTapAssign
  Filename ClsSrchTitlesRecyclerAdapter.java
  Description: ClsSrchTitlesRecyclerAdapter class is an adapter class used for displaying the listing
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */
public class ClsSrchTitlesRecyclerAdapter extends RecyclerView.Adapter<ClsSrchTitlesRecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ClsSearchData> dataArrayList;
    private OnViewClick onViewClick;
    private ClsListItemValues clsListItemValues;


    /**************************************************************************************
     * Constructor[ClsSrchTitlesRecyclerAdapter] - ClsSrchTitlesRecyclerAdapter constructor used to initialize variables
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param context           : context
     * @param activity          : activity instance
     * @param onViewClick       : instance of OnViewClick interface
     * @param dataArrayList     : Arraylist of type ClsSearchData
     * @param clsListItemValues : instance of ClsListItemValues which has list item data
     */
    public ClsSrchTitlesRecyclerAdapter(Context context, Activity activity, ArrayList<ClsSearchData> dataArrayList, ClsListItemValues clsListItemValues, OnViewClick onViewClick) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.onViewClick = onViewClick;
        this.clsListItemValues = clsListItemValues;
    }


    /**************************************************************************************
     * Class[MyViewHolder] - MyViewHolder class implements View.OnClickListener, View.OnLongClickListener
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtDes, txtMeta, txtEditedon;
        ImageView thumbnail;
        RelativeLayout relItemParent, relItem;
        LinearLayout linear_title_des;
        View seperatorView;
        private WeakReference<OnViewClick> listenerRef;

        public MyViewHolder(View view, OnViewClick onViewClick) {
            super(view);
            listenerRef = new WeakReference<>(onViewClick);
            relItemParent = (RelativeLayout) view.findViewById(R.id.item_parent_rel);
            relItem = (RelativeLayout) view.findViewById(R.id.item_rel);
            txtName = (TextView) view.findViewById(R.id.txt_name);
            txtDes = (TextView) view.findViewById(R.id.txt_description);
            txtMeta = (TextView) view.findViewById(R.id.txt_edit_date);
            txtEditedon = (TextView) view.findViewById(R.id.txt_editedon);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            thumbnail.setClipToOutline(true);
            seperatorView = (View) view.findViewById(R.id.seperator);
            linear_title_des = (LinearLayout) view.findViewById(R.id.linear_title_des);

            // Set Listeners
            relItem.setOnClickListener(this);

            //setValuesItemview
            setValuesItemview(clsListItemValues);
        }

        @Override
        public void onClick(final View view) {
            listenerRef.get().onListItemClick(view, getAdapterPosition());
        }

        /**************************************************************************************
         * Function[setValuesItemview] - used for setting parameters for list item view.
         * Created by Aditya Prasad
         * Created December 28,2018.
         * Updator  by Aditya Prasad
         * Updated December 28,2018.
         *
         * @param clsListItemValues : instance of ClsListItemValues which has list item data
         **************************************************************************************/
        private void setValuesItemview(ClsListItemValues clsListItemValues) {
            try {
                if (clsListItemValues != null) {
                    int txtNameColor = clsListItemValues.listItemTxtColor;
                    int txtNameStyle = clsListItemValues.listItemTxtStyle;
                    int txtNameSize = clsListItemValues.listItemTxtsize;

                    int txtDesColor = clsListItemValues.listItemDesTxtColor;
                    int txtDesStyle = clsListItemValues.listItemDesTxtStyle;
                    int txtDesSize = clsListItemValues.listItemDesTxtsize;

                    int txtMetaColor = clsListItemValues.listItemMetaTxtColor;
                    int txtMetaStyle = clsListItemValues.listItemMetaTxtStyle;
                    int txtMetaSize = clsListItemValues.listItemMetaTxtsize;

                    int thumbnailSizeInDp = clsListItemValues.listItemThumbnailSizeInDp;
                    int seperatorColor = clsListItemValues.listItemSeperartorColor;
                    int itemBackgroundColor = clsListItemValues.listItemBackgroundColor;


                    // Setting list item background color
                    relItemParent.setBackgroundColor(itemBackgroundColor);

                    // Setting text size for name textview
                    txtName.setTextSize(TypedValue.COMPLEX_UNIT_SP, txtNameSize);

                    // Setting text style for name textview
                    txtName.setTypeface(null, txtNameStyle);

                    // Setting text size for Description textview
                    txtDes.setTextSize(TypedValue.COMPLEX_UNIT_SP, txtDesSize);

                    // Setting text style for name textview
                    txtDes.setTypeface(null, txtDesStyle);


                    // Setting text size for meta textview
                    txtMeta.setTextSize(TypedValue.COMPLEX_UNIT_SP, txtMetaSize);
                    txtEditedon.setTextSize(TypedValue.COMPLEX_UNIT_SP, txtMetaSize);

                    // Setting text style for meta textview
                    txtMeta.setTypeface(null, txtMetaStyle);
                    txtEditedon.setTypeface(null, txtMetaStyle);


                    // Setting text color for name textview
                    if (txtNameColor != 0) {
                        txtName.setTextColor(txtNameColor);
                    } else {
                        txtName.setTextColor(Color.BLACK);
                    }


                    // Setting text color for description textview
                    if (txtDesColor != 0) {
                        txtDes.setTextColor(txtDesColor);
                    } else {
                        txtDes.setTextColor(Color.BLACK);
                    }


                    // Setting text color for meta textview
                    if (txtMetaColor != 0) {
                        txtMeta.setTextColor(txtMetaColor);
                        txtEditedon.setTextColor(txtMetaColor);
                    } else {
                        txtMeta.setTextColor(Color.BLACK);
                        txtEditedon.setTextColor(Color.BLACK);
                    }



                    // Setting thumbnail dimensions
                    ViewGroup.LayoutParams thumbnailViewparams = thumbnail.getLayoutParams();
                    thumbnailViewparams.height = dpTopx(context, thumbnailSizeInDp);
                    thumbnailViewparams.width = dpTopx(context, thumbnailSizeInDp);
                    thumbnail.setLayoutParams(thumbnailViewparams);

                    // Setting seperator view background color
                    if (seperatorColor != 0) {
                        seperatorView.setBackgroundColor(seperatorColor);
                    } else {
                        seperatorView.setBackgroundColor(Color.GRAY);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_item, parent, false);

        return new MyViewHolder(itemView, onViewClick);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try {
            ClsSearchData clsSearchData = dataArrayList.get(position);
            setDataToFields(clsSearchData, holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }


    /**************************************************************************************
     * Function[setDataToFields] - used for setting menu data to list item views.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param clsSearchData : ClsSearchData which has menu data
     * @param holder        :  MyViewHolder
     **************************************************************************************/
    private void setDataToFields(ClsSearchData clsSearchData, MyViewHolder holder) {
        try {
            String name = clsSearchData.getStrNameTxt();
            setValuesAndVisibility(name, holder.txtName);
            String desTxt = clsSearchData.getStrDesTxt();
            setValuesAndVisibility(desTxt, holder.txtDes);

            String editedDate = clsSearchData.getStrEditedTimeStamp();
            if (editedDate != null && editedDate.length() > 0) {
                holder.txtEditedon.setVisibility(View.VISIBLE);
                setValuesAndVisibility(editedDate, holder.txtMeta);
            } else {
                holder.txtEditedon.setVisibility(View.GONE);
                setValuesAndVisibility(editedDate, holder.txtMeta);
            }


            String strThumbnailUrl = clsSearchData.getStrThumbnailUrl();

            // Setting values for thumbnail
            if (strThumbnailUrl != null && strThumbnailUrl.length() > 0) {
                holder.thumbnail.setVisibility(View.VISIBLE);
                new ClsThumbnailDownTask(holder.thumbnail).execute(strThumbnailUrl);
            } else {
                holder.thumbnail.setVisibility(View.GONE);
            }

            // Setting values for seperator view
            boolean isSeperator = clsSearchData.isSeperator();
            if (isSeperator) {
                holder.seperatorView.setVisibility(View.VISIBLE);
            } else {
                holder.seperatorView.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**************************************************************************************
     * Function[setValuesAndVisibility] - used for setting value for name textview
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param strValue : string value for name textview
     * @param textView :  name textview
     **************************************************************************************/
    private void setValuesAndVisibility(String strValue, TextView textView) {
        try {
            if (strValue != null && strValue.length() > 0 && textView != null) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(strValue);
            } else if (textView != null) {
                textView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**************************************************************************************
     * Interface[OnViewClick] - Interface that is used for handling the callbacks
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     **************************************************************************************/
    public interface OnViewClick {
        void onListItemClick(View view, int position);
    }

    /*End of file*/
}