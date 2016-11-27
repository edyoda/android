package com.classes.android.fragment_tutorial;

        import android.app.Activity;
        import android.app.Fragment;
        import android.view.KeyEvent;
        import android.view.LayoutInflater;
        import android.os.Bundle;
        import android.view.LayoutInflater.*;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;

public class NewItemFragment extends Fragment {

    private OnNewItemAddedListener onNewItemAddedListener;
    //private MainActivity onNewItemAddedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment, container, false);

        //Factory f = inflater.getFactory();


        final EditText myEditText =
                (EditText)view.findViewById(R.id.myEditText);

        myEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = myEditText.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onNewItemAddedListener = (OnNewItemAddedListener)activity;
            //onNewItemAddedListener = (MainActivity)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnNewItemAddedListener");
        }
    }


}
