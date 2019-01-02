package com.codetest.assignMnyTap.apicalls;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
  Application MnyTapAssign
  Filename ApiPostCallTask.java
  Description: ApiPostCallTask class extends AyncTask. This class posts the request to the API.
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 28,2018.
  Updator Aditya Prasad
 */
public class ApiPostCallTask extends AsyncTask<String, Void, String> {
    private int request_timeout = 10 * 1000;

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            Log.v("API", "Param0 :" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(request_timeout);
            connection.setReadTimeout(request_timeout);
            String urlParameters = params[1];
            Log.v("API", "Param1 :" + params[1]);
            Log.i("LOGTAG", "The timeout is" + Integer.toString(connection.getConnectTimeout()));
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            try {
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseOutput.append(line);
            }
            Log.i("LogTag", "the response from the server is" + responseOutput.toString());
            br.close();
            return responseOutput.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (final java.net.SocketTimeoutException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
/*End of File*/
