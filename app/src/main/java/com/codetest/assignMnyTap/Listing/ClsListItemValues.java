package com.codetest.assignMnyTap.Listing;

/**
 * Created by User on 13-12-2018.
 */
/*
 Application MnyTapAssign
  Filename ClsListItemValues.java
  Description: ClsListItemValues class which has data of List item view
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */

public class ClsListItemValues {

    public int listItemTxtColor, listItemTxtStyle, listItemTxtsize, listItemThumbnailSizeInDp, listItemSeperartorColor,
            listItemBackgroundColor,
            listItemDesTxtColor,
            listItemDesTxtStyle,
            listItemDesTxtsize, listItemMetaTxtColor, listItemMetaTxtStyle, listItemMetaTxtsize;

    /**************************************************************************************
     * Constructor[ClsListItemValues] - initializes all values that are related to list item view
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param listItemTxtColor          : name text color value
     * @param listItemTxtStyle          : name text style value
     * @param listItemTxtsize           : name text size value
     * @param listItemThumbnailSizeInDp : thumbnail size in dp
     * @param listItemSeperartorColor   : seperator colour value
     * @param listItemBackgroundColor   : list item background colour value
     */
    public ClsListItemValues(int listItemTxtColor, int listItemTxtStyle, int listItemTxtsize, int listItemDesTxtColor,
                             int listItemDesTxtStyle,
                             int listItemDesTxtsize,
                             int listItemMetaTxtColor, int listItemMetaTxtStyle, int listItemMetaTxtsize,
                             int listItemThumbnailSizeInDp,
                             int listItemSeperartorColor, int listItemBackgroundColor) {
        this.listItemTxtColor = listItemTxtColor;
        this.listItemTxtStyle = listItemTxtStyle;
        this.listItemTxtsize = listItemTxtsize;
        this.listItemThumbnailSizeInDp = listItemThumbnailSizeInDp;
        this.listItemSeperartorColor = listItemSeperartorColor;
        this.listItemBackgroundColor = listItemBackgroundColor;
        this.listItemDesTxtColor = listItemDesTxtColor;
        this.listItemDesTxtStyle = listItemDesTxtStyle;
        this.listItemDesTxtsize = listItemDesTxtsize;

        this.listItemMetaTxtColor = listItemMetaTxtColor;
        this.listItemMetaTxtStyle = listItemMetaTxtStyle;
        this.listItemMetaTxtsize = listItemMetaTxtsize;


    }
}
/*End of file*/