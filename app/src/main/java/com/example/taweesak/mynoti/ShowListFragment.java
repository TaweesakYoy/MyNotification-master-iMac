package com.example.taweesak.mynoti;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListFragment extends Fragment {


    public ShowListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // test Add********
        final TextView textViewTitle = (TextView) getActivity().findViewById(R.id.tv_title);
        // test Add *******
        Bundle bundle = getActivity().getIntent().getExtras();
        String msg = bundle.getString("calendar");
        textViewTitle.setText(msg);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list, container, false);
    }

}
