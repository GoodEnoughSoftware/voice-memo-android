package com.goodenoughsoftware.voicememo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {


    // TODO: Use a user object for this
    private boolean premium = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_about:
                displayAbout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows an about dialog with application and user information
     */
    private void displayAbout() {

        // Display the about dialog
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_about, wrapInScrollView)
                .positiveText(R.string.about_dismiss)
                .show();

        // Show whether the user is a free or premium user
        View view = dialog.getCustomView();
        TextView premiumText = (TextView) view.findViewById(R.id.premium_member);
        TextView freeText = (TextView) view.findViewById(R.id.free_member);

        if(premium) {
            premiumText.setVisibility(View.VISIBLE);
            freeText.setVisibility(View.GONE);
        } else {
            premiumText.setVisibility(View.GONE);
            freeText.setVisibility(View.VISIBLE);
        }

    }

}
