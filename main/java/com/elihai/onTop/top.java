package com.elihai.onTop;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.WindowManager.*;
import android.widget.*;

public class top extends Service
 {
	View mView;
	WindowManager wm ;
	LayoutInflater inflate;
	TextView t;
	Button b;
	LayoutParams params;
	@Override
	public void onCreate() {
		super.onCreate();   

		Toast.makeText(getBaseContext(),"White stripe has been put", Toast.LENGTH_SHORT).show();


		wm= (WindowManager) getSystemService(WINDOW_SERVICE);

	

	params = new WindowManager.LayoutParams(
           1000, 
           10,
            WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
			WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
			+ WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
			PixelFormat.TRANSLUCENT);


		params.gravity = Gravity.BOTTOM;
		params.setTitle("Load Average");

		inflate = (LayoutInflater) getBaseContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mView = inflate.inflate(R.layout.main, null);

		
	
		wm.addView(mView, params);

    }

	public void clicked(View v) {
		try
		{
			System.exit(0);
		}
		catch (Throwable e)
		{}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onTaskRemoved(Intent rootIntent){
		Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
		restartServiceIntent.setPackage(getPackageName());

		PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
		AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		alarmService.set(
			AlarmManager.ELAPSED_REALTIME,
			SystemClock.elapsedRealtime() + 1000,
			restartServicePendingIntent);

		super.onTaskRemoved(rootIntent);
	}

	 @Override
	 public int onStartCommand(Intent intent, int flags, int startId) {
		 super.onStartCommand(intent, flags, startId);
		 return START_STICKY;
	 }

	@Override
	public void onConfigurationChanged (Configuration newConfig){
		switch(newConfig.orientation) {

			case Configuration.ORIENTATION_LANDSCAPE:
				
				wm.removeView(mView);
				params = new WindowManager.LayoutParams(
					10, 
					1000,
					WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
					WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
					+ WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);
				params.gravity = Gravity.LEFT;
				mView.setBackgroundResource(R.drawable.bb);
				wm.addView(mView, params);
				break;

			case Configuration.ORIENTATION_PORTRAIT:
				
				wm.removeView(mView);
				params = new WindowManager.LayoutParams(
					1000, 
					10,
					WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
					WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
					+ WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);
				params.gravity = Gravity.BOTTOM;
				mView.setBackgroundResource(R.drawable.aa);
				wm.addView(mView, params);
				break;

				

		
	}
}}
	
