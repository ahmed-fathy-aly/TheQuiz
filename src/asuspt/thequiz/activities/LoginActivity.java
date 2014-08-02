package asuspt.thequiz.activities;

import java.util.prefs.Preferences;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import asuspt.thequiz.R;
import asuspt.thequiz.data.StudentInfo;
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.web.LoginRegisterServer;

public class LoginActivity extends Activity
{
	/* member variables */
	private EditText editTextId;
	private EditText editTextPassword;
	private Button buttonLogin;

	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);

		setGui();
		loadLoginInfoFromPreferences();
	}

	/**
	 * Sets gui elements
	 */
	private void setGui()
	{
		// Get references
		editTextId = (EditText) findViewById(R.id.editTextId);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);

		// add listeners
		buttonLogin.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				onButtonLoginClicked();
			}
		});

	}

	/**
	 * check if the id and password are correct and start the home activity if
	 * they are there
	 */
	private void onButtonLoginClicked()
	{
		new AsyncTask<Integer, Integer, Boolean>()
		{
			private ProgressDialog progressDialog;

			protected void onPreExecute()
			{
				// make a progress dialog
				progressDialog = ProgressDialog.show(LoginActivity.this, "Connecting",
						"connecting to server");
			}

			protected Boolean doInBackground(Integer... params)
			{
				/*
				// wait as if it'll take time to connect to server
				try
				{
					synchronized (this)
					{
						wait(10000);
					}
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				*/
				// get the id and password
				String id = editTextId.getText().toString();
				String password = editTextPassword.getText().toString();

				return LoginRegisterServer.isCorrectIdAndPassword(id, password);
			}

			protected void onPostExecute(Boolean result)
			{
				// remove the progress dialog
				progressDialog.dismiss();

				// go to home activity or inform the user he's an idiot
				if (result == true)
				{
					Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_LONG)
							.show();

					// get id and password and save them in pregferences
					// get the id and password
					String id = editTextId.getText().toString();
					String password = editTextPassword.getText().toString();
					MyUtils.saveLoginInfoInPreferences(new StudentInfo(id, password),
							getApplicationContext());

					Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
					startActivity(intent);
					finish();
				} else
				{
					Toast.makeText(LoginActivity.this, "Wrong Id or/and Password",
							Toast.LENGTH_LONG).show();
				}
			}
		}.execute(0);
	}

	private void loadLoginInfoFromPreferences()
	{
		StudentInfo studentInfo = MyUtils.loadLoginInfoFromPreferences(getApplicationContext());
		editTextId.setText(studentInfo.getId());
		editTextPassword.setText(studentInfo.getPassword());
	}
}
