package com.example.timegem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmFragment extends Fragment implements clickInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmFragment newInstance(String param1, String param2) {
        AlarmFragment fragment = new AlarmFragment();
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

    private String ScheduledTime,CurrentDate;
    private ArrayList<RoomModel> mModels=new ArrayList<>();
    recyclerAdapter mRecyclerAdapter;
    RecyclerView mRecyclerView;
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.alarm,container,false);
        mRecyclerView=view.findViewById(R.id.recycler);
        fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        return view;
    }
    AlarmViewModel mViewModel;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel= ViewModelProviders.of(this).get(AlarmViewModel.class);
        mViewModel.mData.observe(getViewLifecycleOwner(), new Observer<List<RoomModel>>() {
            @Override
            public void onChanged(List<RoomModel> roomModels) {
                  mRecyclerAdapter=new recyclerAdapter((ArrayList<RoomModel>) roomModels,AlarmFragment.this);
                  mRecyclerView.setAdapter(mRecyclerAdapter);
            }
        });
    }
TextView timt,songt;
    private void dialog()
    {
        final Dialog dialog=new Dialog(getContext());
        dialog.getWindow().setContentView(R.layout.fragment_alarm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
        Button set,time,song;
        timt=dialog.findViewById(R.id.timetext);
        songt=dialog.findViewById(R.id.songtext);
        set=dialog.findViewById(R.id.send);
        time=dialog.findViewById(R.id.timebutton);
        song=dialog.findViewById(R.id.songbutton);
        Uri uri= Uri.parse("android.resource://com.example.timegem/" + R.raw.click);
        songs=uri.toString();
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR);
                int minute=calendar.get(Calendar.MINUTE);
                timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.HOUR,hourOfDay);
                        calendar1.set(Calendar.MINUTE,minute);
                        calendar1.set(Calendar.SECOND,00);
                        m=minute;
                        h=hourOfDay;
                        CharSequence charSequence= DateFormat.format("hh:mm:ss",calendar1);
                        ScheduledTime=charSequence.toString();
                        charSequence2= DateFormat.format("hh:mm",calendar1);
                        timt.setText(charSequence2);
                    }
                },hour,minute,true);
                timePickerDialog.show();

            }
        });
      song.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent1=new Intent();
              intent1.setType("audio/*");
              intent1.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(intent1,27);

          }
      });
      set.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             set();
             dialog.dismiss();
          }
      });

    }
    private void set()
    {  String format = "hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(Calendar.HOUR,hour);
        calendar1.set(Calendar.MINUTE,minute);
        calendar1.set(Calendar.YEAR,year);
        calendar1.set(Calendar.MONTH,month);
        calendar1.set(Calendar.DATE,day);
        CharSequence charSequence= DateFormat.format("hh:mm:ss",calendar1);
        CurrentDate=charSequence.toString();
        Date date1= null;
        Date date2=null;
        try {
            date1 = sdf.parse(CurrentDate);
            date2=sdf.parse(ScheduledTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long del=date2.getTime()-date1.getTime();
        Log.d("ddd",Long.toString(del));

        RoomModel model=new RoomModel();
        model.setKey(mModels.size());
        model.setTime(ScheduledTime);
        model.setSong(songs);
        mViewModel.insert(model);
        int id=mModels.size();
        ComponentName serviceComponent = new ComponentName(getContext(), MyJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(id, serviceComponent)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int res = scheduler.schedule(jobInfo);
    }
    CharSequence charSequence2;
    TimePickerDialog timePickerDialog;
    int h=0,m=0;
    String songs;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
             Uri uri=data.getData();
             songs=uri.toString();
        }
        else {
            Uri uri= Uri.parse("android.resource://com.example.timegem/" + R.raw.click);
            songs=uri.toString();
        }
        songt.setText(songs);
    }

    @Override
    public void click(int position, View view) {
        TextView textView= (TextView) view;
        if(textView.getText().toString().equals("Stop"))
        {
            JobScheduler jobScheduler= (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.cancel(position);
            RoomModel roomModel=new RoomModel();
            roomModel.setStatus("Ring");
            roomModel.setKey(position);
            roomModel.setTime(mModels.get(position).getTime());
            roomModel.setSong(mModels.get(position).getSong());
            mViewModel.update(roomModel);
        }
    }
}

/*
 mBinding=FragmentAlarmBinding.inflate(inflater,container,false);
        mBinding.timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR);
                int minute=calendar.get(Calendar.MINUTE);
                timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.HOUR,hourOfDay);
                        calendar1.set(Calendar.MINUTE,minute);
                        calendar1.set(Calendar.SECOND,00);
                        m=minute;
                        h=hourOfDay;
                        if(calendar1.get(Calendar.AM_PM)==Calendar.AM)
                         Log.d("SSS", "AM");
                        CharSequence charSequence= DateFormat.format("hh:mm:ss a",calendar1);
                        ScheduledTime=charSequence.toString();
                        charSequence2= DateFormat.format("hh:mm a",calendar1);
                        mBinding.timetext.setText(charSequence2);
                    }
                },hour,minute,true);
                timePickerDialog.show();

            }
        });
        mBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             char a=charSequence2.charAt(charSequence2.length()-2);

             Intent intent1=new Intent();
             intent1.setType("audio/*");
             intent1.setAction(Intent.ACTION_GET_CONTENT);
             startActivityForResult(intent1,27);

            }
        });

 */