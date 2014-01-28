package com.georgeredinger.blunog;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    String ble_state;

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.d("debug","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.d("debug","OnStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.d("debug","OnDestroy");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String msg;

        //---check to determine whether BLE is supported on
        // the device---
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE)) {
            msg = "Bluetooth LE not supported on this device";
            finish();
        } else {
            msg = "Congrats! Bluetooth LE is supported on " +
                    "this device!";
        }
        ble_state = msg;
        android.util.Log.d("debug",msg);
        final TextView mText = (TextView) findViewById(R.id.textView);
        mText.setText(ble_state);
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
