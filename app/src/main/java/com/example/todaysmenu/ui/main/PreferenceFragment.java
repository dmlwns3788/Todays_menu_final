package com.example.todaysmenu.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.todaysmenu.DBHelper;
import com.example.todaysmenu.R;
import com.example.todaysmenu.SellerMainActivity;
import com.example.todaysmenu.SellerMenuActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class PreferenceFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PreferenceFragment newInstance(int log_id) {
        PreferenceFragment fragment = new PreferenceFragment();
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
        pageViewModel.setIndex("Preference");

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preference, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);
        //pageViewModel.getText().observe(this, new Observer<String>() {
        //  @Override
        //public void onChanged(@Nullable String s) {
        //  textView.setText(s);
        //}
        //});
        final int login_id = this.getArguments().getInt("login_id");
        Button addmenu = root.findViewById(R.id.CustomSet);
        final EditText customText = (EditText)root.findViewById(R.id.textView11);
        final EditText customText2 = (EditText)root.findViewById(R.id.textView13);
        final Spinner Spinner = (Spinner)root.findViewById(R.id.Customspinner);
        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String preferString = Spinner.getSelectedItem().toString();
                if(preferString.matches("")){
                    Toast.makeText(getActivity(), "좋아하는 음식을 선택하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    // 입력한 menuString DB에 추가
                    //Intent intent = new Intent(SellerMenuActivity.this, SellerMainActivity.class);
                    //dbHelper.join_menu(menuString, login_id);
                    customText.setText(null);
                    customText2.setText(null);
                    Toast.makeText(getActivity(), "저장완료", Toast.LENGTH_SHORT).show();
                }


                final DBHelper dbHelper = new DBHelper(getActivity(), "menu.db", null, 1);  // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 보냄

                dbHelper.set_categrory(preferString,login_id);

            }
        });


        return root;
    }
}