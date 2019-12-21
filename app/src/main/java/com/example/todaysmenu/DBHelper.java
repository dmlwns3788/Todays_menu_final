package com.example.todaysmenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 LOGINT이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void join_account(String a_ID, String a_PW, String a_Name, String a_Phone) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO ACCOUNT VALUES(NULL,'" +
                a_ID + "','" + a_PW + "','" + a_Name + "','" + a_Phone + "',-1);");
        db.close();
    }


    public void join_saler(String s_ID, String s_PW, String s_Name, int s_Category, String s_Phone, String Buisiness_Number) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO SALER VALUES(NULL,'" +
                s_ID + "','" + s_PW + "','" + s_Name + "'," + s_Category + ",'" + s_Phone + "','" + Buisiness_Number + "');");
        db.close();
    }

    public void join_log(int l_ref_m_id, int l_ref_u_ID, int l_score, String l_memo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO SALER VALUES(" +
                l_ref_m_id + "," + l_ref_u_ID + "," + l_score + ",'" + l_memo + "');");
        db.close();
    }

    /*Login*/
    public int Login_seller(String ID_m, String PW_m) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int login_id = -1;

        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT * FROM SALER WHERE TEXTID='" + ID_m + "' AND PW='" + PW_m + "';", null);
        while (cursor.moveToNext()) {
            login_id = cursor.getInt(0);
        }
        return login_id;
    }

    /*Login*/
    public int Login_user(String ID_m, String PW_m) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int login_id = -1;

        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT * FROM ACCOUNT WHERE TEXTID='" + ID_m + "' AND PW='" + PW_m + "';", null);
        while (cursor.moveToNext()) {
            login_id = cursor.getInt(0);
        }
        return login_id;
    }

    //메뉴 아이디(int) 참조하여 가장 최근에 기록된 메뉴를 제외하여, 가장 높은 평점의 메뉴 3개중 랜덤으로 추천
    public int random_menu(int ID_m) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int i = 0;
        int menu_id = -1;
        Random r = new Random();
        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT refMID FROM ( SELECT * FROM LOG WHERE refMID NOT IN( SELECT refMID FROM LOG WHERE refUID = " +
                ID_m +
                "ORDER BY LID DESC LIMIT 1) ) WHERE refUID = " +
                ID_m +
                "GROUP BY refMID ORDER BY AVG(SCORE) DESC LIMIT 3 ;", null);
        i = r.nextInt(cursor.getCount()) + 1;
        menu_id = cursor.getInt(i);

        return menu_id;
    }

    public String View_menu(int ID_m) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String menu_name = "NONE";

        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT MNAME FROM MENU WHERE MID=" + ID_m + ";  ", null);
        while (cursor.moveToNext()) {
            String result_menu = cursor.getString(0);
            return result_menu;
        }
        return menu_name;
    }

    public Cursor selectAll(int s_ID) {
        SQLiteDatabase db = getReadableDatabase();

        //Cursor cursor = db.rawQuery("SELECT * FROM MENU", null);

        Cursor cursor = db.rawQuery("SELECT * FROM MENU WHERE refSID= " + s_ID + ";", null);

        return cursor;
    }


    //Using category id search the
    public int[] Category_list(int c_ID) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int[] list = {};
        int i = 0;
        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT MID FROM SALER, MENU WHERE CATEGORY = " +
                c_ID +
                " AND SID = refSID ;", null);
        while (cursor.moveToNext()) {
            list[i++] = cursor.getInt(0);
        }
        return list;
    }


    public void join_menu(String m_Name, int ref_s_ID) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MENU VALUES(NULL,'" +
                m_Name + "'," + ref_s_ID + ");");
        db.close();
    }

    public ArrayList<String> Menu_list(int m_ID) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> information = new ArrayList<>();
        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("SELECT MNAME FROM MENU WHERE refSID = " +
                m_ID +
                ";", null);
        while (cursor.moveToNext()) {
            information.add(cursor.getString(0)); //saler name
        }
        return information;
    }

    public void delete_menu(String m_name) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM MENU WHERE MNAME= '" + m_name + "';");
        System.out.println("DELETE FROM MENU WHERE MNAME= '" + m_name + "';");
    }

    public ArrayList<String> My_info(int u_ID) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> information = new ArrayList<>();
        // DB에 있는 데이터 조회
        Cursor cursor = db.rawQuery("select TEXTID, Name, Phone from ACCOUNT WHERE UID=" +
                u_ID +
                ";", null);
        while (cursor.moveToNext()) {
            information.add(cursor.getString(0)); //saler name
            information.add(cursor.getString(1)); //saler name
            information.add(cursor.getString(2)); //saler name
        }
        return information;
    }

    public void set_categrory(String c_name, int u_id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CID FROM CATEGORY WHERE CNAME = '" +
                c_name +"';",null);
        cursor.moveToNext();
        int c_id = cursor.getInt(0);
        System.out.println("helper" + c_id);
        db.execSQL("update account set refID = " +
                c_id +
                " WHERE uid = " +
                u_id +
                ";");
    }

    public String Recommnad_algorith(int u_id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT MNAME FROM MENU WHERE MID IN (SELECT refMID FROM LOG WHERE refUID = "+u_id+" ORDER BY SCORE DESC LIMIT 5)";
        Cursor cursor = db.rawQuery("SELECT MNAME FROM MENU ORDER BY RANDOM() LIMIT 1;",null);

        cursor.moveToNext();
        String menu = cursor.getString(0);
        if(menu == null){
            menu = "Please update preference";
        }
        return menu;
    }

}