package com.classes.android.intentdatapassing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EnterDataActivity extends Activity {


    EditText editText;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_data);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        Button button = (Button)findViewById(R.id.addbutton);


    }
    public void onClickAdd (View button){
        Intent intent = getIntent();
        String name = editText.getText().toString();
        String phone = editText2.getText().toString();
        intent.putExtra("person_name", name);
        intent.putExtra("phone_no", phone);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}
