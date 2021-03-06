package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton = findViewById(R.id.next_button);
        Intent intent = getIntent();

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {

                mButton.setText(R.string.prev);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);


    }


    public void displayFragment() {
        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.prev);
        isFragmentDisplayed = true;

    }

    public void prevFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {

            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        mButton.setText(R.string.next);

        isFragmentDisplayed = false;
    }

    public void launchSecondActivity(View view) {

        if (!isFragmentDisplayed) {
            displayFragment();
        } else {
            prevFragment();
        }
    }

    public void returnReply(View view)
    {
        Intent replyIntent = new Intent();
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}