package com.example.taweesak.mynoti;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class MainFragment extends Fragment {

    private CalendarView calendarView;
    private TimePicker alarmTimePicker;
    private String tag = "3NovV1";
    private final int[] dayInt = new int[1];
    private final int[] monthInt = new int[1];
    private final int[] yearInt = new int[1];
    private int hourInt, minuteInt;

    Calendar calendar; // testAdd

    public MainFragment() {
        // Required empty public constructor
    }

    // Activity /  first Activity / Main Method
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // new Add******
        alarmTimePicker = (TimePicker) getActivity().findViewById(R.id.alarmTimePicker);

        //    Setup CurrentDate
        setupCurrentDate();

        // Set Controller
        setController();

    }

    private void setController() {
        Button button = getView().findViewById(R.id.buttonSet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // New Add*****
                final int hour = alarmTimePicker.getCurrentHour();
                final int minute = alarmTimePicker.getCurrentMinute();;

                Log.d(tag, "Date ==> " + dayInt[0]);
                Log.d(tag, "Month ==> " + monthInt[0]);
                Log.d(tag, "Year ==> " + yearInt[0]);
                Log.d(tag, "Hour ==> " + hourInt);

                //minuteInt = minuteInt + 2; // เวลาที่ตั้ง
                Log.d(tag, "Minute ==> " + minuteInt);

                calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, dayInt[0]);
                calendar.set(Calendar.MONTH, monthInt[0]);
                calendar.set(Calendar.YEAR, yearInt[0]);
                /*calendar.set(Calendar.HOUR_OF_DAY, hourInt);
                calendar.set(Calendar.MINUTE, minuteInt);*/
                //test Add********
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());


                Log.d(tag, "calendar ==> " + calendar.getTime());
                Toast.makeText(getActivity(), "set"+calendar.getTime(), Toast.LENGTH_SHORT).show();

                //test Add********
                /*Intent i = new Intent(getActivity(),MyReceiver.class);
                i.putExtra(getString(R.string.titttle), calendar.getTime());
                getActivity().startActivity(i);*/

                // Method
                sentValueToReceiver(calendar);

                // set to database
                /*MyManage myManage = new MyManage(getActivity());
                myManage.addValueToSQLite(calendar.getTime().toString(),
                        Integer.toString(dayInt[0]),
                        Integer.toString(monthInt[0]),
                        Integer.toString(hourInt),
                        Integer.toString(minuteInt));*/

                //Replace to Show list Fragment
                /*getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new ShowListFragment())
                        .addToBackStack(null) // back to before fragment
                        .commit();*/


            }
        });
    }

    private void sentValueToReceiver(Calendar notiCalendar) {
        //??????????

        // random number to request number
        Random random = new Random();
        int requestInt = random.nextInt(100);


        Intent intent = new Intent(getActivity(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), requestInt,
                intent, 0);


        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, notiCalendar.getTimeInMillis(), pendingIntent);

        // testAdd************
        //intent.putExtra(getString(R.string.titttle), calendar.getTime());
        //intent.putExtra("calendar", calendar.getTime());
        intent.putExtra("calendar", "TestSendData");
        //getActivity().sendBroadcast(intent); /////////////////////////// new ********



    }

    private void setupCurrentDate() {
        //??????????
        calendarView = getView().findViewById(R.id.calendarViewSet);

        Calendar calendar = Calendar.getInstance();
        dayInt[0] = calendar.get(Calendar.DAY_OF_MONTH);
        monthInt[0] = calendar.get(Calendar.MONTH); // 0 ==> Jan
        yearInt[0] = calendar.get(Calendar.YEAR);
        hourInt = calendar.get(Calendar.HOUR_OF_DAY);
        minuteInt = calendar.get(Calendar.MINUTE);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfmonth) {
                dayInt[0] = dayOfmonth;
                monthInt[0] = month;
                yearInt[0] = year;
            }
        });


    }

    // View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
