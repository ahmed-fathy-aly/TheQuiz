package asuspt.thequiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import asuspt.thequiz.R;
import asuspt.thequiz.data.Quiz;
import asuspt.thequiz.utils.MyUtils;

/**
 * The activity that receives a Quiz object and grades it and lets user either
 * retry or go back to home page
 * 
 * @author ahmed ossama
 * 
 * 
 */
public class QuizGraderActivity extends Activity
{
	/* gui elements */
	private TextView textViewGrade;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_grader);

		loadGrade();
	}

	/**
	 * Try to receive the grade sent by the quiz activity and set it to
	 * textFieldGrade
	 */
	private void loadGrade()
	{
		int totalScore = 0;
		int maximumScore = 0;
		try
		{
			Bundle extras = getIntent().getExtras();
			totalScore = extras.getInt(MyUtils.TOTAL_SCORE);
			maximumScore = extras.getInt(MyUtils.MAXIMUM_SCORE);
			textViewGrade = (TextView) findViewById(R.id.textViewGrade);
			textViewGrade.setText("Grade : " + totalScore + "/" + maximumScore);

		} catch (Exception e)
		{
			Toast.makeText(getApplicationContext(),
					"Couldn't fetch your score :(... you're doomed", Toast.LENGTH_SHORT).show();
		}
	}

	public void RetryQuiz(View view)
	{
		// Do something in response to button
		Intent intent = new Intent(this, QuizActivity.class);
		startActivity(intent);
	}

	public void CallHome(View view)
	{
		// Do something in response to button
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
