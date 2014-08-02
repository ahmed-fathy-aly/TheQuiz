package asuspt.thequiz.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import asuspt.thequiz.R;
import asuspt.thequiz.data.Quiz;
import asuspt.thequiz.data.StudentInfo;
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.views.QuestionsListAdapter;
import asuspt.thequiz.web.LoginRegisterServer;
import asuspt.thequiz.web.QuizServer;

/**
 * The activity that displays and lets user answer a quiz
 * 
 * @author ahmed fathy aly
 * 
 */
public class QuizActivity extends Activity
{

	/* gui elements */
	private Button submitButton;
	private Button clearButton;
	private ListView questionsList;
	private Quiz quiz;
	private QuestionsListAdapter questionsListAdapter;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		loadQuiz();
	}

	/**
	 * Loads the quiz to the activity
	 */
	private void loadQuiz()
	{
		boolean fathyDidTheWebStuff = true;
		if (!fathyDidTheWebStuff)
			this.quiz = Quiz.generateTemplateQuiz("My Quiz", 10, 4);
		else
		{
			new QuizLoaderTask().execute();
		}
	}

	/**
	 * sets up the gui elements called after the quiz is loaded
	 */
	private void setGui()
	{

		// questions list
		questionsListAdapter = new QuestionsListAdapter(this);
		questionsListAdapter.setQuiz(quiz);
		questionsList = (ListView) findViewById(R.id.questionsList);

		// quiz title
		TextView quizTitle = new TextView(getApplicationContext());
		quizTitle.setText(this.quiz.getQuizTitle());
		quizTitle.setTextAppearance(getApplicationContext(),
				android.R.style.TextAppearance_DeviceDefault_Large);
		quizTitle.setTextColor(0xff2186AD);
		quizTitle.setGravity(Gravity.CENTER);
		questionsList.setHeaderDividersEnabled(true);
		quizTitle.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
				ListView.LayoutParams.WRAP_CONTENT));
		questionsList.addHeaderView(quizTitle);

		// submit button
		submitButton = new Button(getApplicationContext());
		submitButton.setText("Submit");
		submitButton.setTextColor(0xff2186AD);
		submitButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				onSubmitButtonClicked();
			}
		});

		// clear button
		clearButton = new Button(getApplicationContext());
		clearButton.setText("Clear");
		clearButton.setTextColor(0xff2186AD);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				onClearButtonClicked();
			}
		});

		// The layout for submit and clear button
		LinearLayout l = new LinearLayout(getApplicationContext());
		l.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		l.addView(clearButton);
		l.addView(submitButton);
		questionsList.addFooterView(l);
		submitButton.setLayoutParams(p);
		clearButton.setLayoutParams(p);

		questionsList.setAdapter(questionsListAdapter);

	}

	/**
	 * Clears all selections
	 */
	protected void onClearButtonClicked()
	{
		try
		{
			questionsListAdapter.clearAllSelections();
		} catch (Exception e)
		{
		}
	}

	/**
	 * Calculate the grade and send it to the grader activity
	 */
	protected void onSubmitButtonClicked()
	{
		new QuizGraderTask().execute();

	}

	/**
	 * loads the quiz and stores it in this.quiz then sets up the gui elements
	 */
	class QuizLoaderTask extends AsyncTask<Void, Void, Quiz>
	{
		private ProgressDialog progressDialog;

		protected void onPreExecute()
		{
			// make a progress dialog
			progressDialog = ProgressDialog.show(QuizActivity.this, "Loading", "downloading quiz");
		}

		protected Quiz doInBackground(Void... params)
		{
			// wait as if it'll take time to connect to server
			try
			{
				synchronized (this)
				{
					wait(1000);
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			// ask web minions to load the quiz
			StudentInfo studentInfo = MyUtils.loadLoginInfoFromPreferences(getApplicationContext());
			return LoginRegisterServer.loadQuiz("secret-id");
		}

		protected void onPostExecute(Quiz result)
		{
			// check the result is valid
			if (result != null)
			{
				quiz = result;
			} else
			{
				Toast.makeText(getApplicationContext(), "Failed to load the quiz",
						Toast.LENGTH_SHORT).show();
				quiz = new Quiz("Failed Quiz");
			}

			// set GUI of the activity
			setGui();
			progressDialog.dismiss();

		}

	}

	class QuizGraderTask extends AsyncTask<Void, Void, String>
	{
		private ProgressDialog progressDialog;

		protected void onPreExecute()
		{
			// make a progress dialog
			progressDialog = ProgressDialog.show(QuizActivity.this, "Grading", "grading quiz");
		}

		@Override
		protected String doInBackground(Void... params)
		{
			// ask web minions to grade the quiz
			StudentInfo studentInfo = MyUtils.loadLoginInfoFromPreferences(getApplicationContext());
			ArrayList<Integer> choices = questionsListAdapter.getAnswers();
			return LoginRegisterServer.gradeQuiz(quiz, choices, studentInfo);
		}

		@Override
		protected void onPostExecute(String result)
		{
			progressDialog.dismiss();

			// if graded successfully then go to the grader activity
			if (result.equals(MyUtils.GRADING_FAILED))
			{
				Toast.makeText(getApplicationContext(), "Oops...couldn't submit",
						Toast.LENGTH_SHORT).show();
			} else
			{
				// start the grader activity
				Intent intent = new Intent(getBaseContext(), QuizGraderActivity.class);
				intent.putExtra(MyUtils.GRADE, result);
				startActivity(intent);
				finish();
			}
		}
	}
}
