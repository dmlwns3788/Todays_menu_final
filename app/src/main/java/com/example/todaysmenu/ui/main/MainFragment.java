package com.example.todaysmenu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.todaysmenu.CustomMainActivity;
import com.example.todaysmenu.DBHelper;
import com.example.todaysmenu.LoginActivity;
import com.example.todaysmenu.R;
import com.example.todaysmenu.SellerMainActivity;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;



    public static MainFragment newInstance(int log_id) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("login_id", log_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        //int index = 1;
        //if (getArguments() != null) {
        //    index = getArguments().getInt(ARG_SECTION_NUMBER);
        //}
        pageViewModel.setIndex("Main");

        //Intent intent = Intent.getIntent();
        //final int login_id = intent.getIntExtra("login_id", 0);

        //if(getActivity() != null && getActivity() instanceof CustomMainActivity){
        //    int id = ((CustomMainActivity)getActivity()).getda
        //}



    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);
        //pageViewModel.getText().observe(this, new Observer<String>() {
        //  @Override
        //public void onChanged(@Nullable String s) {
        //  textView.setText(s);
        //}
        //});

        //ArrayList<String> information = new ArrayList<>();
        //information =
        Button deleteMenu = root.findViewById(R.id.customLogout);
        deleteMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });



        final DBHelper dbHelper = new DBHelper(getActivity(), "menu.db", null, 1);  // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 보냄

        final TextView user_id = (TextView)root.findViewById(R.id.textView8);
        final TextView user_name = (TextView)root.findViewById(R.id.textView14);
        final TextView user_ph = (TextView)root.findViewById(R.id.textView15);

        int login_id = this.getArguments().getInt("login_id");
        final ArrayList<String> list  = dbHelper.My_info(login_id);

        user_id.setText(list.get(0));
        user_name.setText(list.get(1));
        user_ph.setText(list.get(2));



        return root;
    }
}