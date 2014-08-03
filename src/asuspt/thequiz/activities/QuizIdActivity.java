package asuspt.thequiz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import asuspt.thequiz.R;
import asuspt.thequiz.data.Quiz;
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.web.LoginRegisterServer;
import asuspt.thequiz.web.QuizServer;

public class QuizIdActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_id);
	}

	/**
	 * checks the quiz id and opens the quiz activity
	 */
	public void onStartQuizButtonClicked(View v)
	{
		new IdCheckerTask().execute(false);
	}

	public class IdCheckerTask extends AsyncTask<Boolean, Boolean, Boolean>
	{
		private ProgressDialog progressDialog;

		protected void onPreExecute()
		{
			// make a progress dialog
			progressDialog = ProgressDialog.show(QuizIdActivity.this, "Chekcing", "Checking ID");

		}

		protected Boolean doInBackground(Boolean... params)
		{
			EditText editTextId = (EditText) findViewById(R.id.editTextQuizId);
			String id = editTextId.getText().toString();
			//Quiz resultQuiz = LoginRegisterServer.loadQuiz(id);
			//return ! resultQuiz.getQuizTitle().equals(MyUtils.QUIZ_LOAD_FAILED);
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result)
		{
			// check the result is valid
			if (result == true)
			{
				EditText editTextId = (EditText) findViewById(R.id.editTextQuizId);
				String id = editTextId.getText().toString();
				
				Intent intent = new Intent(QuizIdActivity.this, QuizActivity.class);
				intent.putExtra(MyUtils.QUIZ_ID, id);
				startActivity(intent);
				finish();
			} else
			{
				Toast.makeText(getApplicationContext(), "Wrong ID", Toast.LENGTH_SHORT).show();
			}

			// remove the progress dialog
			progressDialog.dismiss();
		}

	}
}
