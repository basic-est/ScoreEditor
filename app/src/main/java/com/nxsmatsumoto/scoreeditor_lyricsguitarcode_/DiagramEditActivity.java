package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

public class DiagramEditActivity extends AppCompatActivity {

    // 1:新規作成  2:既存スコアの編集  3:コピー新規の編集
    int edit_mode = 0;
    long _diagramId = -1;
    String _codeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_edit);

        Toolbar toolbar =  findViewById(R.id.toolbar_diagram_edit);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        edit_mode = intent.getIntExtra("edit_mode",0);
        if (edit_mode == 2) {
            _diagramId = intent.getLongExtra("diagramID", 0);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;

        switch (id) {
            case android.R.id.home:
                // saveDiagram();
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    private void saveDiagram() {

        EditText etCodeName = findViewById(R.id.etCodeName);
        _codeName = etCodeName.getText().toString();

        DatabaseHelper_d helper = new DatabaseHelper_d(DiagramEditActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            SQLiteStatement stmt;
            String sqlInsert;
            if (edit_mode == 2) {
                //既存スコアの編集中の場合は、取得したIDのスコアデータを削除。その後インサートを行う。
                //削除用SQL文字列を用意。
                String sqlDelete = "DELETE FROM diagram WHERE _id = ?";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlDelete);
                //変数のバイド。
                stmt.bindLong(1, _diagramId);
                //削除SQLの実行。
                stmt.executeUpdateDelete();
                //インサート用SQL文字列の用意。
                sqlInsert = "INSERT INTO diagram (_id, name) VALUES (?, ?)";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlInsert);
                //変数のバイド。
                stmt.bindLong(1, _diagramId);
                stmt.bindString(2, _codeName);
                //インサートSQLの実行。
                stmt.executeInsert();
            } else {
                //インサート用SQL文字列の用意。
                sqlInsert = "INSERT INTO diagram (name) VALUES ( ?)";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlInsert);
                //変数のバイド。
                stmt.bindString(1, _codeName);
                //インサートSQLの実行。
                stmt.executeInsert();

            }
            //インサートSQLの実行。
            stmt.executeInsert();
        }
        finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }

    }

}