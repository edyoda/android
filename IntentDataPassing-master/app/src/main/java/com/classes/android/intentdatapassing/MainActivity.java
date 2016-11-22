package com.classes.android.intentdatapassing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int code_id = 1;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button = (Button) findViewById(R.id.enterdatabutton);


    }
    public void onClickEnterData(View button)
    {
        startActivityForResult(new Intent(this, EnterDataActivity.class), code_id);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getExtras().getString("person_name");
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hello " + str);


    }
}



