package asuspt.thequiz.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import asuspt.thequiz.R;
import asuspt.thequiz.data.StudentInfo;
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.web.LoginRegisterServer;

public class ProfileActivity extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		new ProfileLoaderTask().execute("");
	}

	/**
	 * fills the text views with the student's info
	 */
	public void setGui(StudentInfo studentInfo)
	{
		TextView id = (TextView) findViewById(R.id.textViewStudentID);
		TextView email = (TextView) findViewById(R.id.textViewStudentEmail);
		TextView department = (TextView) findViewById(R.id.textViewStudentDepartment);
		TextView results = (TextView) findViewById(R.id.textViewStudentResults);
		
		id.setText(studentInfo.getId());
		email.setText(studentInfo.getEmail());
		department.setText(studentInfo.getDepartment());
		results.setText(studentInfo.getQuizResults());


	}

	/**
	 * a thread that asks the server for the profile info
	 */
	public class ProfileLoaderTask extends AsyncTask<String, String, StudentInfo>
	{
		private ProgressDialog progressDialog;

		protected void onPreExecute()
		{
			// make a progress dialog
			progressDialog = ProgressDialog.show(ProfileActivity.this, "Loading",
					"Loading Profile info");
		}

		protected StudentInfo doInBackground(String... params)
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

			// get the complete student info (name, id, password, quiz results)
			StudentInfo incompleInfo = MyUtils
					.loadLoginInfoFromPreferences(getApplicationContext());
			StudentInfo result;
			try
			{
				result = LoginRegisterServer.getCompleteStudentInfo(incompleInfo);
			} catch (Exception e)
			{
				Toast.makeText(getApplicationContext(), "Couldn't load the profile",
						Toast.LENGTH_LONG).show();
				result = new StudentInfo(incompleInfo.getId(), incompleInfo.getPassword());
				result.setName("NA");
				result.setQuizResults("NA");
			}
			return result;
		}

		protected void onPostExecute(StudentInfo result)
		{
			progressDialog.dismiss();
			setGui(result);
		}
	}

}
