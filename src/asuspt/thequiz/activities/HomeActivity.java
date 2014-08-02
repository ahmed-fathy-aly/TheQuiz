package asuspt.thequiz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import asuspt.thequiz.R;

/**
 * The home page
 * 
 * 
 */
public class HomeActivity extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		startQuizActivity(null);

	}

	/**
	 * opens the quiz activity
	 */
	public void startQuizActivity(View v)
	{
		Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
		startActivity(intent);
	}

	/**
	 * opens the profile activity
	 */
	public void startProfileActivity(View v)
	{
		Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
		startActivity(intent);
	}



}
