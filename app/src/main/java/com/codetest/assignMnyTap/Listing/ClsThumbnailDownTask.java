package com.codetest.assignMnyTap.Listing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 Application MnyTapAssign
  Filename ClsThumbnailDownTask.java
  Description: ClsThumbnailDownTask class extending AsyncTask. It does the downloading operations
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */
public class ClsThumbnailDownTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;

    /**************************************************************************************
     * Constructor[ClsThumbnailDownTask] - ClsThumbnailDownTask constructor
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param imageView : Imageview instance to which thumbnail has to be set.
     */
    public ClsThumbnailDownTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }


    /**************************************************************************************
     * Function[doInBackground] - functionw which does the task on background thread
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     **************************************************************************************/
    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadBitmap(params[0]);
    }


    /**************************************************************************************
     * Function[onPostExecute] - function is called once the doing in background completes its task. Here we need to handle UI operations, as this runs on main thread
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     **************************************************************************************/
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }
        try {
            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setVisibility(View.GONE);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**************************************************************************************
     * Function[downloadBitmap] - function used to download thumbnail from Url.
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param url : thumbnail url
     * @return Bitmap
     **************************************************************************************/
    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("thumbnailDownload", "Error in downloading from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}

/*End of file*/