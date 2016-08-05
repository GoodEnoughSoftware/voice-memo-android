package com.goodenoughsoftware.voicememo;

import android.animation.Animator;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.goodenoughsoftware.voicememo.fragments.RecordingFragment;
import com.goodenoughsoftware.voicememo.utils.ViewHelper;

public class MainActivity extends AppCompatActivity {

    // TODO: Use a user object for this
    private boolean premium = false;
    private RecordingFragment recordingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Attach the recording fragment
        recordingFragment = new RecordingFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.recording_screen, recordingFragment);
        transaction.commit();

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
            case R.id.action_tags:
                displayTags();
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
     * Shows the dialog to manage tags
     */
    private void displayTags() {

        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.layout_tags, wrapInScrollView)
                .title(R.string.tags_dialog_title)
                .iconRes(R.drawable.add_tags_label)
                .positiveText(R.string.tags_dialog_dismiss)
                .show();

        View rootView = dialog.getCustomView();

    }

    /**
     * Uses a radial reveal to instantiate and start the recording
     */
    public void showRecordingScreen() {

        // TODO: Go full screen

        final Animator recorderAnimator = ViewHelper.startRecordingReveal(this);
        recorderAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // TODO: Start recording here!
                recordingFragment.recordingStart();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    /**
     * Uses a radial animation to hide the recording screen and redisplay the FAB
     */
    public void hideRecordingScreen() {

        //TODO: Go not full screen

        final Animator recorderAnimator = ViewHelper.startRecordingHide(this);
        recorderAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

}
