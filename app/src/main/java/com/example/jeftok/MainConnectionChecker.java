package com.example.jeftok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class MainConnectionChecker extends Activity {

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	// Connection detector class
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		// creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());
		if (AppStatus.getInstance(this).isOnline()) {

			// go ahead with next activity load 
			Intent newintent = new Intent(
					"android.intent.action.NextActivity");
			startActivity(newintent);

		} else {

			showAlertDialog(MainConnectionChecker.this, "Internet Connection",
					"You Don't have internet connection", true); 
		}
		/**
		 * Check Internet status button click event
		 * */

	}

	/**
	 * Function to display simple Alert Dialog
	 * @param context - application context
	 * @param title - alert dialog title
	 * @param message - alert message
	 * @param status - success/failure (used to set icon)
	 * */
	public void showAlertDialog(Context context, String title, String message, Boolean status) {



		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
		.setCancelable(false)
		.setTitle(title)
		.setIcon((status) ? R.drawable.success : R.drawable.fail)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				MainConnectionChecker.this.finish();
			}
		})
		.setNegativeButton("Setting", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(dialogIntent);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();


	}
}