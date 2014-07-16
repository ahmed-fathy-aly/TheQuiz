package asuspt.thequiz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import asuspt.thequiz.R;
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
				// wait as if it'll take time to connect to server
				try
				{
					synchronized (this)
					{
						wait(3000);
					}
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}

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

					Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
					startActivity(intent);
				} else
				{
					Toast.makeText(LoginActivity.this, "Wrong Id or/and Password",
							Toast.LENGTH_LONG).show();
				}
			}
		}.execute(0);
	}

}
