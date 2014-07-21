package asuspt.thequiz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import asuspt.thequiz.data.StudentInfo;

/**
 * the static methods, variables that could be used in any class
 *
 */
public class MyUtils
{
	public static final String GRADE = "asuspt grade";

	public static final String PREFERENCES_NAME = "asuspt the quiz preferences";
	public static final String STUDENT_ID = "student id";
	public static final String STUDENT_PASSWORD = "student password";
	public static final String STUDENT_NAME= "student name";
	
	public static final String GRADING_FAILED = "asuspt i am a bad web minion";
	
	/**** Method for Setting the Height of the ListView dynamically.
	 **** Hack to fix the issue of not showing all the items of the ListView
	 **** when placed inside a ScrollView  ****/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}
	
	/**
	 * saves in preferences
	 */
	public static void saveLoginInfoInPreferences(StudentInfo studentInfo, Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences(MyUtils.PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		
		editor.putString(MyUtils.STUDENT_ID, studentInfo.getId());
		editor.putString(MyUtils.STUDENT_PASSWORD, studentInfo.getPassword());
		editor.putString(MyUtils.STUDENT_NAME, studentInfo.getName());
		
		editor.commit();
	}

	/**
	 * loads from preferences
	 */
	public static StudentInfo loadLoginInfoFromPreferences(Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences(MyUtils.PREFERENCES_NAME, Context.MODE_PRIVATE);
		
		StudentInfo studentInfo = new StudentInfo("", "");
		if (prefs.contains(MyUtils.STUDENT_ID))
			studentInfo.setId(prefs.getString(MyUtils.STUDENT_ID, ""));
		if (prefs.contains(MyUtils.STUDENT_PASSWORD))
			studentInfo.setPassword(prefs.getString(MyUtils.STUDENT_PASSWORD, ""));
		if (prefs.contains(MyUtils.STUDENT_NAME))
			studentInfo.setName(prefs.getString(MyUtils.STUDENT_NAME, ""));
		return studentInfo;
	}
}

