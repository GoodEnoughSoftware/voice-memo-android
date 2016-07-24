package com.goodenoughsoftware.voicememo;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
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
                showRecordingScreen();
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

    /**
     * Uses a radial reveal to instantiate and start the recording
     */
    void showRecordingScreen() {

        // Get animation info for FAB --------------------------------------------------------------

        // previously invisible view
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // get the center for the fab circle
        int fx = fab.getMeasuredWidth() / 2;
        int fy = fab.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int fStartRadius = Math.max(fab.getWidth(), fab.getHeight()) / 2;
        int fFinalRadius = 0;

        // create the animator for this view
        Animator fabAnim =
                ViewAnimationUtils.createCircularReveal(fab, fx, fy, fStartRadius, fFinalRadius);

        fabAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fab.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        // Get animation info for the recording view -----------------------------------------------

        // previously invisible view
        View screen = findViewById(R.id.recording_screen);

        int cx = screen.getMeasuredWidth() / 2;
        int cy = screen.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int cFinalRadius = (int) Math.hypot(screen.getWidth(), screen.getHeight());
        int cStartRadius = 0;

        // create the animator for this view
        Animator circleAnim =
                ViewAnimationUtils.createCircularReveal(screen, screen.getWidth() - fx, screen.getHeight() - fy, cStartRadius, cFinalRadius);

        circleAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // TODO: Start recording here!
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        // Start the animations! -------------------------------------------------------------------
        // make the views visible and start the animation
        fab.setVisibility(View.VISIBLE);
        screen.setVisibility(View.VISIBLE);
        fabAnim.start();
        circleAnim.start();
    }

}
