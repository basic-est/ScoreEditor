package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreBrowseActivity extends AppCompatActivity {

    long _scoreId = -1;
//    final String FONTCOLOR_S = "&lt;font color=\\\"red\\\"&gt;";
//    final String FONTCOLOR_E = "&lt;/font&gt;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_browse);

        Toolbar toolbar =  findViewById(R.id.toolbar_score_view);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        _scoreId = intent.getLongExtra("scoreID", -1);

        DatabaseHelper helper = new DatabaseHelper(ScoreBrowseActivity.this);
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
            TextView tvTitle = findViewById(R.id.scoreViewTitle);
            TextView tvLyrics = findViewById(R.id.scoreViewLyrics);
            tvTitle.setText(_scoreTitle);
            tvLyrics.setText(_scoreLyrics);
        }
        finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }

        // TODO: コードの色付け処理
    }

    public void onBkScoreView(View view) {

        Intent intent = new Intent(ScoreBrowseActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_score_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.edit:
                intent = new Intent(ScoreBrowseActivity.this, ScoreEditActivity.class);
                intent.putExtra("edit_mode", 2);
                intent.putExtra("scoreId", _scoreId);
                startActivity(intent);
                return true;
            case R.id.copy:
                scoreCopy();
                intent = new Intent(ScoreBrowseActivity.this, ScoreEditActivity.class);
                TextView tvTitle = findViewById(R.id.scoreViewTitle);
                TextView tvLyrics = findViewById(R.id.scoreViewLyrics);
                String title = tvTitle.getText().toString();
                String lyrics = tvLyrics.getText().toString();
                intent.putExtra("edit_mode", 3);
                intent.putExtra("copy_title", title);
                intent.putExtra("copy_lyrics", lyrics);
                startActivity(intent);
                return true;
            case R.id.del:
                scoreDel();
                intent = new Intent(ScoreBrowseActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.export:
                intent = new Intent(ScoreBrowseActivity.this, ExportActivity.class);
                intent.putExtra("scoreID", _scoreId);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void scoreDel() {
        // TODO:ポップアップ表示処理（削除してもよろしいですか？y/n）

        DatabaseHelper helper = new DatabaseHelper(ScoreBrowseActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            //削除用SQL文字列を用意。
            String sqlDelete = "DELETE FROM score WHERE _id = ?";
            //SQL文字列を元にプリペアドステートメントを取得。
            SQLiteStatement stmt = db.compileStatement(sqlDelete);
            //変数のバイド。
            stmt.bindLong(1, _scoreId);
            //削除SQLの実行。
            stmt.executeUpdateDelete();

        } finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }
    }

    private void scoreCopy() {
         // TODO:複製してカレントのscoreIDをコピー先に変更

    }

    public void onToneUpBtn(View view) {
        TextView tvLyrics = findViewById(R.id.scoreViewLyrics);
        String lyrics = tvLyrics.getText().toString();
        CodeTransposer ct = new CodeTransposer();

        String wkLyrics = ct.toneChange(lyrics,1);

        tvLyrics.setText(wkLyrics);
    }

    public void onToneDownBtn(View view) {
        TextView tvLyrics = findViewById(R.id.scoreViewLyrics);
        String lyrics = tvLyrics.getText().toString();
        CodeTransposer ct = new CodeTransposer();

        String wkLyrics = ct.toneChange(lyrics,-1);

        tvLyrics.setText(wkLyrics);
    }

    boolean scroll = false;
    public void onScroll(View view) {
        //TODO: 自動スクロール機能
        ScrollView scrollLyrics = findViewById(R.id.scrollLyrics);
        String msg = "";
        AutoScroller autoScroller = new AutoScroller(scrollLyrics,0);
        if (scroll) {
            scroll = false;
            msg = "自動スクロール：オフ";
            autoScroller.stop();
        } else {
            scroll = true;
            msg = "自動スクロール：オン";
            autoScroller.start();
        }
        Toast.makeText(getApplicationContext(), msg ,Toast.LENGTH_SHORT).show();
    }
}