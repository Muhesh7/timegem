package com.example.timegem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
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
private ProgressBar mProgressBar;
private TextView mTextView;
private CountDownTimer countDownTimer;
Button start,stop,reset;
long time,sect=0,mint=0;
Spinner minSpin,secSpin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_timer, container, false);
        mProgressBar=view.findViewById(R.id.prog);
        mTextView=view.findViewById(R.id.time);
        start=view.findViewById(R.id.startButton);
        stop=view.findViewById(R.id.stopButton);
        reset=view.findViewById(R.id.resetButton);
        minSpin=view.findViewById(R.id.min);
        secSpin=view.findViewById(R.id.sec);
        secSpin.setVisibility(View.INVISIBLE);
        set();
       // mProgressBar.setVisibility(View.INVISIBLE);
        minSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mint=position;
                time=(mint*60000);
                orgtime=time;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        secSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // sect=position;
               // time=(sect*1000)+(mint*60000);
                //Log.d("SSS",time+"s"+sect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setVisibility(View.VISIBLE);
                timer();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                mProgressBar.setProgress(100);
                mTextView.setText("00:00");
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               countDownTimer.cancel();
            }
        });
        return view;
    }

    public void timer()
    {
        countDownTimer=new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time=millisUntilFinished;
                int sec= (int) (millisUntilFinished/1000);
                mProgressBar.setProgress(sec);
                int progress= sec;
                int min= (int) (sec/60);
                sec=sec%60;
                int ot=(int) orgtime;
                String s=Integer.toString(sec);
                String m=Integer.toString(min);
                String t;
                if(min>=10) {
                    if(sec >= 10)
                    {t=m+":"+s;}
                    else {t=m+":"+"0"+s; }
                }else {
                    if(sec >=10)
                    {t="0"+m+":"+s;}
                    else {t="0"+m+":"+"0"+s; }
                }
                mTextView.setText(t);
            }

            @Override
            public void onFinish() {
                mProgressBar.setProgress(0);
            }
        }.start();
    }
    public void set()
    {  List<Integer> sec=new ArrayList<>();
        List<Integer> min=new ArrayList<>();
        for(int i=0;i<=15;++i)
        {
            min.add(i);
        }
        for(int i=0;i<60;++i)
        {
            sec.add(i);
        }
        ArrayAdapter<Integer> adapter1=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,sec);
        secSpin.setAdapter(adapter1);
        ArrayAdapter<Integer> adapter2=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,min);
        minSpin.setAdapter(adapter2);

    }
   long orgtime;
}
