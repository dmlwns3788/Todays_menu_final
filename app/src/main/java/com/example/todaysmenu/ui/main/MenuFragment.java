package com.example.todaysmenu.ui.main;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.todaysmenu.DBHelper;
import com.example.todaysmenu.R;
import com.example.todaysmenu.ui.main.PageViewModel;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    public static MenuFragment newInstance(int log_id) {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("login_id", log_id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel.setIndex("Menu");
    }

    ImageView image1;

    Random r;
    int img1;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        final TextView customText = (TextView) root.findViewById(R.id.text1);
        final int login_id = this.getArguments().getInt("login_id");

        Button b_roll;
        final DBHelper dbHelper = new DBHelper(getActivity(), "menu.db", null, 1);  // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 보냄

        r = new Random();

        b_roll = (Button) root.findViewById(R.id.b_roll);

        image1 = (ImageView) root.findViewById(R.id.image1);
        customText.setVisibility(View.INVISIBLE);

        b_roll.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animate first image
                customText.setVisibility(View.INVISIBLE);
                image1.setImageResource(R.drawable.anim);
                //get String from algorithm
                final String goodmenu = dbHelper.Recommnad_algorith(login_id);
                final AnimationDrawable image1anim = (AnimationDrawable) image1.getDrawable();
                image1anim.start();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1anim.stop();

                        setImages();
                        customText.setText(goodmenu);
                        customText.setVisibility(View.VISIBLE);
                        getImages();
                    }
                }, 500);

            }
        });
        return root;
    }


    public void setImages(){
        img1=r.nextInt(6)+1;

        switch (img1){
            case 1:
                image1.setImageResource(R.drawable.image1);
                break;
            case 2:
                image1.setImageResource(R.drawable.image2);
                break;
            case 3:
                image1.setImageResource(R.drawable.image3);
                break;
            case 4:
                image1.setImageResource(R.drawable.image4);
                break;
            case 5:
                image1.setImageResource(R.drawable.image5);
                break;
            case 6:
                image1.setImageResource(R.drawable.image6);
                break;
        }
    }
    public void getImages(){

        Toast.makeText(getActivity(),"PERFECT MENU", Toast.LENGTH_SHORT).show();

    }

}