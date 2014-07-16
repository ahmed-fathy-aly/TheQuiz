package asuspt.thequiz.activities;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import asuspt.thequiz.R;

public class LoginOrRegister extends TabActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_login_or_register);
		super.onCreate(savedInstanceState);

		setTabs();
	}

	/**
	 * Sets 2 tabs; login, register
	 */
	private void setTabs()
	{
		TabHost tabHost = getTabHost();

		// Login tab
		TabSpec loginSpec = tabHost.newTabSpec("Log in");
		loginSpec.setIndicator("Log in");
		loginSpec.setContent(new Intent(this, LoginActivity.class));
		tabHost.addTab(loginSpec);

		// Register tab
		// Login tab
		TabSpec registerSpec = tabHost.newTabSpec("Log in");
		registerSpec.setIndicator("Register");
		registerSpec.setContent(new Intent(this, RegisterActivity.class));
		tabHost.addTab(registerSpec);

	}
}
