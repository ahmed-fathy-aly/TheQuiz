package asuspt.thequiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
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
		setContentView(R.layout.activity_home);

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
		// TODO start profile activity
	}

}
