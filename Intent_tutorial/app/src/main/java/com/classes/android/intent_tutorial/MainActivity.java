package com.classes.android.intent_tutorial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText=(EditText)findViewById(R.id.editText);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url=editText.getText().toString();
                //Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(url));

                Intent intent=new Intent(MainActivity.this,MainActivitySecond.class);
                intent.putExtra("name",url);


                startActivity(intent);

            }
        });
    }


}
