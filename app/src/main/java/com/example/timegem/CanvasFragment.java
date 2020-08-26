package com.example.timegem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CanvasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CanvasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static int mParam1;
    private static int mParam2;

    public CanvasFragment() {
        // Required empty public constructor
    }

    public static CanvasFragment newInstance(int param1, int param2) {
        CanvasFragment fragment = new CanvasFragment();
        mParam1 = param1;
        mParam2 = param2;
        return fragment;
    }

    Button mStart,mStop,mReset;
    canvas mCanvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canvas, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.plane);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mParam1, mParam2);
        relativeLayout.setLayoutParams(params);
        mCanvas = new canvas(getContext());
        relativeLayout.addView(mCanvas);
        mStart =view.findViewById(R.id.startButton);
        mReset =view.findViewById(R.id.resetButton);
        mStop =view.findViewById(R.id.stopButton);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCanvas.spin(0);
                mStop.setEnabled(true);
                mReset.setEnabled(true);
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCanvas.stop();
                mReset.setEnabled(true);
                mStop.setEnabled(false);
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCanvas.reset();
                mReset.setEnabled(true);
                mStart.setEnabled(true);
                mStop.setEnabled(false);
            }
        });
        return view;

    }

}
