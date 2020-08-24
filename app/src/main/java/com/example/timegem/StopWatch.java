package com.example.timegem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StopWatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopWatch extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StopWatch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopWatch.
     */
    // TODO: Rename and change types and number of parameters
    public static StopWatch newInstance(String param1, String param2) {
        StopWatch fragment = new StopWatch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int height,width;
        Fragment fragment;
       View view= inflater.inflate(R.layout.fragment_stop_watch, container, false);
        RelativeLayout relativeLayout =view.findViewById(R.id.act);
        if(this.getResources().getConfiguration().orientation== ORIENTATION_LANDSCAPE)
        { height =getResources().getDisplayMetrics().heightPixels;
            width =getResources().getDisplayMetrics().heightPixels;
            relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            relativeLayout.setMinimumHeight(height);}
        else {height =getResources().getDisplayMetrics().widthPixels;
            width =getResources().getDisplayMetrics().widthPixels;
            relativeLayout.setGravity(Gravity.CENTER);}
        fragment = CanvasFragment.newInstance(width,height);
        getFragmentManager().beginTransaction().replace(R.id.can,fragment)
                 .replace(R.id.but,new ButtonFragment()).commit();

        return view;
    }
}
