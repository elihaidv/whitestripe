package com.elihai.onTop;

import android.annotation.TargetApi;
import android.app.*;
import android.net.Uri;
import android.os.*;
import android.content.*;
import android.provider.Settings;

public class MainActivity extends Activity 
{
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 5469;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        checkPermission();
        Intent intent = new Intent( this,top.class);
        this.startService(intent);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission();

            }
            else
            {
                //do as per your logic
            }

        }



    }
    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

}
