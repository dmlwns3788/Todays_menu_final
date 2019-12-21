package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SellerMenuDeleteActivity extends AppCompatActivity{
    int chk = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "menu.db", null, 1);
        final int login_id = getIntent().getIntExtra("login_id", 0);
        System.out.println(login_id);

        // 1. 레이아웃을 정의한 레이아웃 리소스(R.layout)을 사용하여 현재 액티비티의 화면을 구성하도록 합니다.
        setContentView(R.layout.activity_seller_menu_delete);


        // 2. 레이아웃 파일에 정의된 ListView를 자바 코드에서 사용할 수 있도록 합니다.
        // findViewById 메소드는 레이아웃 파일의 android:id 속성을 이용하여 뷰 객체를 찾아 리턴합니다.
        ListView listview = (ListView) findViewById(R.id.listview);


        // 3. 실제로 문자열 데이터를 저장하는데 사용할 ArrayList 객체를 생성합니다.
        final ArrayList<String> list = dbHelper.Menu_list(login_id);


        // 4. ArrayList 객체에 데이터를 집어넣습니다.



        // 5. ArrayList 객체와 ListView 객체를 연결하기 위해 ArrayAdapter객체를 사용합니다.
        // 우선 ArrayList 객체를 ArrayAdapter 객체에 연결합니다.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //context(액티비티 인스턴스)
                android.R.layout.simple_list_item_1, // 한 줄에 하나의 텍스트 아이템만 보여주는 레이아웃 파일
                // 한 줄에 보여지는 아이템 갯수나 구성을 변경하려면 여기에 새로만든 레이아웃을 지정하면 됩니다.
                list  // 데이터가 저장되어 있는 ArrayList 객체
        );


        // 6. ListView 객체에 adapter 객체를 연결합니다.
        listview.setAdapter(adapter);


        // 7. ListView 객체의 특정 아이템 클릭시 처리를 추가합니다.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long id) {
                //show();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("메뉴 삭제");
                builder.setMessage("해당 메뉴를 삭제하시겠습니까?");

                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selected_item = (String) adapterView.getItemAtPosition(position);
                                dbHelper.delete_menu(selected_item);
                                // 9. 해당 아이템을 ArrayList 객체에서 제거하고
                                list.remove(selected_item);
                                // 추가로 쿼리로 실제 데이터베이스에서도 반영하도록
                                // delete

                                // 10. 어댑터 객체에 변경 내용을 반영시켜줘야 에러가 발생하지 않습니다.
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                chk = 0;
                            }
                        });
                builder.show();


            }

        });
    }
}