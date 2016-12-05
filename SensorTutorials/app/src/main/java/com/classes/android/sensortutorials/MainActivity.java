package com.classes.android.sensortutorials;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView = null;
    SensorManager sm= null;
    List list;

    SensorEventListener sm1= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView.setText("x : " +values[0]+"\ny : "+values[1]+"\nz "+values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //get a sensor manager instance
       sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        textView = (TextView)findViewById(R.id.textView);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sm.registerListener(sm1, (Sensor) list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            Toast.makeText(getBaseContext(),"Error : No Accelerometer", Toast.LENGTH_LONG).show();
        }

    }


}
