package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class JoinInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_info);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "menu.db", null, 1);  // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 보냄

        final EditText idInput = (EditText)findViewById(R.id.idInput);
        final EditText pwInput = (EditText)findViewById(R.id.pwInput);
        final EditText pwCheckDup = (EditText)findViewById(R.id.pwCheckDup);
        final EditText nameInput = (EditText)findViewById(R.id.nameInput);
        final EditText telNumInput = (EditText)findViewById(R.id.telNumInput);

        //determine checkbox state
        final CheckBox seller_checkBox = (CheckBox) findViewById(R.id.sellerCheckBox) ;

        Button join = (Button) findViewById(R.id._joinButton);    //xml에 정의한 android:id="@+id/loginButton"과 매핑 선언, 이후부턴 login 버튼 클릭시 아래의 listener가 실행된다.
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가입정보 확인
                if(idInput.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(JoinInfoActivity.this, "ID를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(pwInput.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(JoinInfoActivity.this, "비밀번호를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(!pwCheckDup.getText().toString().equals(pwInput.getText().toString())) {
                    Toast.makeText(JoinInfoActivity.this, "비밀번호를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(nameInput.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(JoinInfoActivity.this, "이름을 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(telNumInput.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(JoinInfoActivity.this, "전화번호를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (seller_checkBox.isChecked()) {
                        //판매자 추가 정보 입력 페이지로 이동 및 데이터 전달
                        //DB에 유저 추가
                        dbHelper.join_account(idInput.getText().toString(), pwInput.getText().toString(), nameInput.getText().toString(), telNumInput.getText().toString());
                        //가입 완료 후 다시 로그인 페이지로 이동
                        Intent intent = new Intent(JoinInfoActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); //finish를 추가하지 않았을 경우 로그인 화면에서 뒤로가기 버튼을 누르면 다시 회원가입 페이지로 이동
                    } else {
                        //DB에 유저 추가
                        dbHelper.join_account(idInput.getText().toString(), pwInput.getText().toString(), nameInput.getText().toString(), telNumInput.getText().toString());

                        //가입 완료 후 다시 로그인 페이지로 이동
                        Intent intent = new Intent(JoinInfoActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); //finish를 추가하지 않았을 경우 로그인 화면에서 뒤로가기 버튼을 누르면 다시 회원가입 페이지로 이동
                    }
                }

            }
        });
    }
}
