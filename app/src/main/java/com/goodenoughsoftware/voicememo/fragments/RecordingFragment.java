package com.goodenoughsoftware.voicememo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.goodenoughsoftware.voicememo.MainActivity;
import com.goodenoughsoftware.voicememo.R;

/**
 * Fragment that controls the recording of audio
 * @author Aaron Vontell
 * @version 0.1
 */
public class RecordingFragment extends Fragment {

    /**
     * Creates a new recording fragment
     */
    public RecordingFragment() {

    }

    /**
     * Loads the recording view from the layout 'fragment_recording'
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return The recording screen
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout recordingScreen = (LinearLayout)
                inflater.inflate(R.layout.fragment_recording, null);

        recordingScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).hideRecordingScreen();
            }
        });

        // TODO: Get layout items here
        Log.d("RECORDING", "Screen initiated");

        return recordingScreen;

    }

    /**
     * Begins the process to start a recording
     */
    public void recordingStart() {

        Log.d("RECORDING", "Recording started");

    }

}
