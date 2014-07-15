package asuspt.thequiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import asuspt.thequiz.R;

/**
 * The home page
 * 
 * @author ahmed mohsen
 * 
 */
public class HomeActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	/**
	 * Listener for the start activity button
	 */
	public void startQuiz(View v)
	{
		// start quiz activity
		Intent intent = new Intent(this, QuizActivity.class);
		startActivity(intent);
	}

}
