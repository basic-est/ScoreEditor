package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ScoreEditActivity extends AppCompatActivity {

    // 1:新規作成  2:既存スコアの編集  3:コピー新規の編集
    int edit_mode;
    long _scoreId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_edit);

        Toolbar toolbar =  findViewById(R.id.toolbar_score_edit);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        edit_mode = intent.getIntExtra("edit_mode",0);
        if (edit_mode == 2) {
            _scoreId = intent.getLongExtra("scoreId", -1);
            DatabaseHelper helper = new DatabaseHelper(ScoreEditActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();

            try {
                //主キーによる検索SQL文字列の用意。
                String sql = "SELECT * FROM score WHERE _id = " + _scoreId;
                //SQLの実行。
                Cursor cursor = db.rawQuery(sql, null);
                //データベースから取得した値を格納する変数の用意。データがなかった時のための初期値も用意。
                String _scoreTitle = ("");
                String _scoreLyrics = ("");
                //SQL実行の戻り値であるカーソルオブジェクトをループさせてデータベース内のデータを取得。
                while (cursor.moveToNext()) {
                    //カラムのインデックス値を取得。
                    int idxTitle = cursor.getColumnIndex("title");
                    int idxLyrics = cursor.getColumnIndex("lyrics");
                    //カラムのインデックス値を元に実際のデータを取得。
                    _scoreTitle = cursor.getString(idxTitle);
                    _scoreLyrics = cursor.getString(idxLyrics);
                }
                //感想のEditTextの各画面部品を取得しデータベースの値を反映。
                EditText etTitle = findViewById(R.id.scoreEditTitle);
                EditText etLyrics = findViewById(R.id.scoreEditLyrics);
                etTitle.setText(_scoreTitle);
                etLyrics.setText(_scoreLyrics);
            }
            finally {
                //データベース接続オブジェクトの解放。
                db.close();
            }

        } else if (edit_mode == 3) {
            String _scoreTitle = intent.getStringExtra("copy_title");
            String _scoreLyrics = intent.getStringExtra("copy_lyrics");
            EditText etTitle = findViewById(R.id.scoreEditTitle);
            EditText etLyrics = findViewById(R.id.scoreEditLyrics);
            etTitle.setText(_scoreTitle);
            etLyrics.setText(_scoreLyrics);
        }
    }

    public void onDoneBtnClick(View view){
        EditText scoreEditTitle = findViewById(R.id.scoreEditTitle);
        EditText scoreEditLyrics = findViewById(R.id.scoreEditLyrics);
        String _scoreTitle = scoreEditTitle.getText().toString();
        String _scoreLirics = scoreEditLyrics.getText().toString();

        DatabaseHelper helper = new DatabaseHelper(ScoreEditActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            SQLiteStatement stmt;
            String sqlInsert;
            if (edit_mode == 2) {
                //既存スコアの編集中の場合は、取得したIDのスコアデータを削除。その後インサートを行う。
                //削除用SQL文字列を用意。
                String sqlDelete = "DELETE FROM score WHERE _id = ?";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlDelete);
                //変数のバイド。
                stmt.bindLong(1, _scoreId);
                //削除SQLの実行。
                stmt.executeUpdateDelete();
                //インサート用SQL文字列の用意。
                sqlInsert = "INSERT INTO score (_id, title, lyrics) VALUES (?, ?, ?)";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlInsert);
                //変数のバイド。
                stmt.bindLong(1, _scoreId);
                stmt.bindString(2, _scoreTitle);
                stmt.bindString(3, _scoreLirics);
            } else {
                //新規作成中の場合は、IDを指定しないインサートSQL文字列の用意。
                sqlInsert = "INSERT INTO score (title, lyrics) VALUES (?, ?)";
                //SQL文字列を元にプリペアドステートメントを取得。
                stmt = db.compileStatement(sqlInsert);
                //変数のバイド。
                stmt.bindString(1, _scoreTitle);
                stmt.bindString(2, _scoreLirics);
            }
            //インサートSQLの実行。
            _scoreId = stmt.executeInsert();
        }
        finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }

        Intent intent = new Intent(ScoreEditActivity.this, ScoreBrowseActivity.class);
        intent.putExtra("scoreID", _scoreId);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;

        switch (id) {
            case android.R.id.home:
                // TODO: 変更が保存されていないことの確認ポップアップ表示
                OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    public void onToneUpBtn(View view) {
        EditText etLyrics = findViewById(R.id.scoreEditLyrics);
        String lyrics = etLyrics.getText().toString();
        CodeTransposer ct = new CodeTransposer();

        String wkLyrics = ct.toneChange(lyrics,1);

        etLyrics.setText(wkLyrics);
    }

    public void onToneDownBtn(View view) {
        EditText etLyrics = findViewById(R.id.scoreEditLyrics);
        String lyrics = etLyrics.getText().toString();
        CodeTransposer ct = new CodeTransposer();

        String wkLyrics = ct.toneChange(lyrics,-1);

        etLyrics.setText(wkLyrics);
    }



}