package com.example.magneticsensor;

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

public class MagneticMainActivity extends Activity implements SensorEventListener{

	private SensorManager mySensorMgr;
	private Sensor mySensor;
	private TextView myTextX;
	private TextView myTextY;
	private TextView myTextZ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magnetic_main);
		
		mySensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensor = mySensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        myTextX = (TextView) this.findViewById(R.id.azimuthDisplay);
        myTextY = (TextView) this.findViewById(R.id.pitchDisplay);
        myTextZ = (TextView) this.findViewById(R.id.rollDisplay);
        
        if(mySensor == null){
        	Toast.makeText(MagneticMainActivity.this, 
                " No Magnetic Field Sensor! ", 
                Toast.LENGTH_LONG).show();
        	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.magnetic_main, menu);
		return true;
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		myTextX.setText(String.valueOf(event.values[0]));
		myTextY.setText(String.valueOf(event.values[1]));
		myTextZ.setText(String.valueOf(event.values[2]));	
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
