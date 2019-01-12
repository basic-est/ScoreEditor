package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * データベースファイル名の定数フィールド。
     */
    private static final String DATABASE_NAME = "score.db";
    /**
     * バージョン情報の定数フィールド。
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * コンストラクタ。
     */
    public DatabaseHelper(Context context) {
        //親クラスのコンストラクタの呼び出し。
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLの実行。
        try {
            db.execSQL(createTable_diagram());
        }catch(Exception e){
            Log.v("DEBUG","error e = " + e.getMessage());
        }

        db.execSQL(createTable_score());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private String createTable_score() {
        //テーブル作成用SQL文字列の作成。
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE score (");
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("title TEXT,");
        sb.append("lyrics TEXT,");
        sb.append("position INTEGER,");
        sb.append("created INTEGER,");
        sb.append("created_id INTEGER,");
        sb.append("changed INTEGER,");
        sb.append("changed_cnt INTEGER,");
        sb.append("permission INTEGER");         // 0:編集可  1:削除不可  9:閲覧不可
        sb.append(");");
        String sql = sb.toString();

        return sql;
    }

    private String createTable_diagram() {
        //テーブル作成用SQL文字列の作成。
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE diagram (");
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("name TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_1 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_2 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_3 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_4 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_5 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p0_6 TEXT,");                 // "〇" OR "×" OR " "
        sb.append("p1_1 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p1_2 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p1_3 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p1_4 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p1_5 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p1_6 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_1 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_2 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_3 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_4 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_5 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p2_6 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_1 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_2 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_3 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_4 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_5 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p3_6 INTEGER,");              // 0:OFF  1:ON  2:Valley
        sb.append("p4_1 INTEGER,");              // 0:OFF  1:ON
        sb.append("p4_2 INTEGER,");              // 0:OFF  1:ON
        sb.append("p4_3 INTEGER,");              // 0:OFF  1:ON
        sb.append("p4_4 INTEGER,");              // 0:OFF  1:ON
        sb.append("p4_5 INTEGER,");              // 0:OFF  1:ON
        sb.append("p4_6 INTEGER,");              // 0:OFF  1:ON
        sb.append("p1_7 INTEGER,");              // フレット数
        sb.append("p2_7 INTEGER,");              // フレット数
        sb.append("p3_7 INTEGER,");              // フレット数
        sb.append("p4_7 INTEGER,");              // フレット数
        sb.append("permission INTEGER");         // 0:編集可  1:削除不可  9:閲覧不可
        sb.append(");");
        String sql = sb.toString();

        return sql;
    }

}
