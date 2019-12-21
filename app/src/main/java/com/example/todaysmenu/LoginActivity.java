package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    int checkbox_state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); /*Start DB Helper*/

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "menu.db", null, 1);  // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 보냄

        final EditText idEdit = (EditText)findViewById(R.id.id);            //xml에 정의한 android:id="@+id/id" 를 가지고 인스턴스 생성, 나중에 idEdit.getText().toString() 를 사용해서 데이터를 읽어온다.
        final EditText pwEdit = (EditText)findViewById(R.id.password);      //xml에 정의한 android:id="@+id/password" 를 가지고 인스턴스 생성, 나중에 pwEdit.getText().toString() 를 사용해서 데이터를 읽어온다.

        pwEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);    //비밀번호 입력시 텍스트 동그라미 모양으로 바꿔줌
        PasswordTransformationMethod pwTrans = new PasswordTransformationMethod();
        pwEdit.setTransformationMethod(pwTrans);


        //determine checkbox state
        CheckBox login_checkBox = (CheckBox) findViewById(R.id.sellerCheck) ;
        login_checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    checkbox_state = 1;
                } else {
                    checkbox_state = 0;
                }
            }
        });


        /* start: 로그인 조회 & 유저와 판매자 로그인 */
        Button select = (Button) findViewById(R.id.loginButton);    //xml에 정의한 android:id="@+id/loginButton"과 매핑 선언, 이후부턴 login 버튼 클릭시 아래의 listener가 실행된다.
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(checkbox_state == 1) {   //판매자라면 판매자 페이지로 이동
                        int login_id = dbHelper.Login_seller(idEdit.getText().toString(),pwEdit.getText().toString());    // ID, PW확인 후 값이 존재한다면 login_id 에 값이 고유 로그인 번호가 셋팅된다

                        if(login_id > 0) {    //로그인 확인 결과 일치한다면
                            Intent intent = new Intent(LoginActivity.this, SellerMainActivity.class);
                            intent.putExtra("login_id",login_id);
                            startActivity(intent);
                        }
                        else {                     //ID,PW가 일치하지 않는다면
                            Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {  //일반 사용자라면 유저 페이지로 이동
                        int login_id = dbHelper.Login_user(idEdit.getText().toString(), pwEdit.getText().toString());    // ID, PW확인 후 값이 존재한다면 login_id 에 값이 고유 로그인 번호가 셋팅된다

                        if(login_id > 0) {    //로그인 확인 결과 일치한다면
                            Intent intent = new Intent(LoginActivity.this, CustomMainActivity.class);
                            intent.putExtra("login_id",login_id);
                            startActivity(intent);
                        }
                        else {                     //ID,PW가 일치하지 않는다면
                            Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호를 확인하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
        /* end: 로그인 조회 & 유저와 판매자 로그인 */

        //회원가입
        Button join = (Button) findViewById(R.id.joinButton);    //xml에 정의한 android:id="@+id/loginButton"과 매핑 선언, 이후부턴 login 버튼 클릭시 아래의 listener가 실행된다.
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}
