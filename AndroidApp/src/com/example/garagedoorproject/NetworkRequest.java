package com.example.garagedoorproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

class NetworkRequest extends AsyncTask<String, String, String> {
	
	private MainActivity parent;

	public NetworkRequest(MainActivity activity) {
		this.parent = activity;
	}
	
	public void displayToast(final  String message) {
		parent.runOnUiThread(new Runnable() {
			  public void run() {
			    Toast.makeText(parent.getBaseContext(), message, Toast.LENGTH_LONG).show();
			  }
			});
	}

	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = null;
		httppost = new HttpPost(params[0]);
		
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("OPEN", ""));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			Log.i("GarageDoorOpener", "Response: "+ response);
			displayToast("Success");
		} catch (ClientProtocolException e) {
			Log.e("GarageDoorOpener", "ClientProtocolException while calling RaspberryPi: "+ e);
			displayToast("ClientProtocolException");
		} catch (IOException e) {
			Log.e("GarageDoorOpener", "IOException while calling RaspberryPi: "+ e);
			displayToast("IOException");
		} catch (Exception e) {
			Log.e("GarageDoorOpener", "Exception while calling RaspberryPi: "+ e);
			displayToast("Exception "+ e);
		}
		
		return null;
	}

}