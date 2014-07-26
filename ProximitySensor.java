package com.example.proximitysensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ProximityMainActivity extends Activity implements SensorEventListener{

	private SensorManager mySensorMgr;
	private Sensor mySensor;
	private TextView myText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proximity_main);
		
		mySensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensor = mySensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        myText = (TextView) this.findViewById(R.id.proxDisplay);
        if(mySensor == null){
        	Toast.makeText(ProximityMainActivity.this, 
                " No Proximity Sensor! ", 
                Toast.LENGTH_LONG).show();
        	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.proximity_main, menu);
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		myText.setText(String.valueOf(event.values[0]));
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	 protected void onResume() {
	 super.onResume();
	 mySensorMgr.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
	 }
	
	@Override
	 protected void onPause() {
	 super.onPause();
	 mySensorMgr.unregisterListener(this);
	 }
}
