package com.goodenoughsoftware.voicememo.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodenoughsoftware.voicememo.R;

/**
 * Class that handles the controls for adding and removing tags
 * @author Aaron Vontell
 * @version 0.1
 * Last modified on July 24, 2016
 */
public class TagsDialogFragment extends DialogFragment {

    /**
     * Empty constructor required by DialogFragments
     */
    public TagsDialogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tags, container);

        return view;
    }

}
