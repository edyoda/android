package com.classes.android.fragment_tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements OnNewItemAddedListener {

    private ArrayAdapter<String> aa;
    private ArrayList<String> todoItems;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate your view
        setContentView(R.layout.activity_main);

        // Get references to the Fragments
        FragmentManager fm = getFragmentManager();

        ToDoListFragment todoListFragment =
                (ToDoListFragment)fm.findFragmentById(R.id.TodoListFragment);

        // Create the array list of to do items
        todoItems = new ArrayList<String>();

        // Create the array adapter to bind the array to the listview
        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                todoItems);

        // Bind the array adapter to the listview.
        todoListFragment.setListAdapter(aa);
    }

    public void onNewItemAdded(String newItem) {
        todoItems.add(newItem);
        aa.notifyDataSetChanged();
    }

}
