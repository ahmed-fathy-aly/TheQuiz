package asuspt.thequiz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import asuspt.thequiz.R;
import asuspt.thequiz.data.StudentInfo;
import asuspt.thequiz.utils.MyUtils;
import asuspt.thequiz.web.LoginRegisterServer;

public class RegisterActivity extends Activity
{
	/* member variables */
	private EditText editTextId;
	private EditText editTextPassword;
	private EditText editTextpasswwordRepeat;
	private EditText editTextName;
	private Button buttonRegister;

	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_register);
		super.onCreate(savedInstanceState);

		setGui();
	}

	/**
	 * sets gui elements
	 */
	private void setGui()
	{
		// get references to view
		editTextId = (EditText) findViewById(R.id.editTextID);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextpasswwordRepeat = (EditText) findViewById(R.id.editTextPasswordRepeat);
		editTextName = (EditText) findViewById(R.id.editTextName);
		buttonRegister = (Button) findViewById(R.id.buttonRegister);

		// add listeners
		buttonRegister.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				onButtonRegisterClicked();
			}
		});

	}

	/**
	 * asks the server to register with the account info
	 */
	protected void onButtonRegisterClicked()
	{
		// Checks that the password and its repeat are the same
		String password = editTextPassword.getText().toString();
		String passwordRepeat = editTextpasswwordRepeat.getText().toString();
		if (!password.equals(passwordRepeat))
		{
			Toast.makeText(this, "The password doesn't match", Toast.LENGTH_LONG).show();
			editTextpasswwordRepeat.requestFocus();
			return;
		}

		// ask the server to register this
		new AsyncTask<Integer, Integer, Boolean>()
		{
			private ProgressDialog progressDialog;

			protected void onPreExecute()
			{
				// make a progress dialog
				progressDialog = ProgressDialog.show(RegisterActivity.this, "Connecting",
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
				String name = editTextName.getText().toString();

				return LoginRegisterServer.registerAccount(id, password, name);
			}

			protected void onPostExecute(Boolean result)
			{
				// remove the progress dialog
				progressDialog.dismiss();

				// go to log in activity or tell the user he's an idiot
				if (result == true)
				{
					String id = editTextId.getText().toString();
					String password = editTextPassword.getText().toString();
					String name = editTextName.getText().toString();
					Toast.makeText(RegisterActivity.this, "Registered succesfully",
							Toast.LENGTH_SHORT).show();
					Toast.makeText(RegisterActivity.this,
							"ID : " + id + "\nPassword : " + password, Toast.LENGTH_LONG).show();
					MyUtils.saveLoginInfoInPreferences(new StudentInfo(id, password, name),
							getApplicationContext());

					Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				} else
				{
					Toast.makeText(RegisterActivity.this, "Couldn't register", Toast.LENGTH_LONG)
							.show();
				}
			}
		}.execute(0);

	}

}
