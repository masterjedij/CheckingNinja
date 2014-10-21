package edu.barella4730.checkingninja;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.database.sqlite.*;

public class Checking extends Activity {
	
	interface MyCallbackClass{
		void callbackReturn();
	}
	
	MyCallbackClass settingsCbkClass;
	MyCallbackClass detailTransCbkClass;
	MyCallbackClass inputTransCbkClass;
	MyCallbackClass editTransCbkClass;
	
	void registerCallback(MyCallbackClass settingsCbk, MyCallbackClass detailTransCbk, MyCallbackClass inputTransCbk, MyCallbackClass editTransCbk)
	{
		settingsCbkClass = settingsCbk;
		detailTransCbkClass = detailTransCbk;
		inputTransCbkClass = inputTransCbk;
		editTransCbkClass = editTransCbk;		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checking);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		//setContentView(R.layout.fragment_viewtrans);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checking, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			getFragmentManager().beginTransaction().add(R.id.container, new Settings()).commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_viewtrans, container, false);
			return rootView;
		}
	}
}
