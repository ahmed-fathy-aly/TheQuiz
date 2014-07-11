package asuspt.thequiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
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
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.views.QuestionsListAdapter;

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
		setGui();
	}

	/**
	 * Loads the quiz to the activity
	 */
	private void loadQuiz()
	{
		this.quiz = Quiz.generateTemplateQuiz("My Quiz", 10, 4);
	}

	/**
	 * sets up the gui elements
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
		try
		{
			// calculate score
			int score = questionsListAdapter.getScore();

			// start the grader activity
			Intent intent = new Intent(getBaseContext(), QuizGraderActivity.class);
			intent.putExtra(MyUtils.TOTAL_SCORE, score);
			intent.putExtra(MyUtils.MAXIMUM_SCORE, quiz.getMcqs().size());
			startActivity(intent);
			finish();
		} catch (Exception e)
		{
		}

	}

}
