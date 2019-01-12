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
    final String[] CNS_CODE = {
            "C","C#","Db","D","D#","Eb","E","F","F#","Gb","G","G#","Ab","A","A#","Bb","B"};
    final String[] CNS_CODE7 = {
            "C7","C#7","Db7","D7","D#7","Eb7","E7","F7","F#7","Gb7","G7","G#7","Ab7","A7","A#7","Bb7","B7"};
    final String[] CNS_CODEm = {
            "Cm","C#m","Dbm","Dm","D#m","Ebm","Em","Fm","F#m","Gbm","Gm","G#m","Abm","Am","A#m","Bbm","Bm"};
    final String[] CNS_CODEm7 = {
            "Cm7","C#m7","Dbm7","Dm7","D#m7","Ebm7","Em7","Fm7","F#m7","Gbm7","Gm7","G#m7","Abm7","Am7","A#m7","Bbm7","Bm7"};
    final String[] CNS_CODEM7 = {
            "CM7","C#M7","DbM7","DM7","D#M7","EbM7","EM7","FM7","F#M7","GbM7","GM7","G#M7","AbM7","AM7","A#M7","BbM7","BM7"};
    final String[] CNS_CODEmM7 = {
            "CmM7","C#mM7","DbmM7","DmM7","D#mM7","EbmM7","EmM7","FmM7","F#mM7","GbmM7","GmM7","G#mM7","AbmM7","AmM7","A#mM7","BbmM7","BmM7"};
    final String[] CNS_CODEs4 = {
            "Csus4","C#sus4","Dbsus4","Dsus4","D#sus4","Ebsus4","Esus4","Fsus4","F#sus4","Gbsus4","Gsus4","G#sus4","Absus4","Asus4","A#sus4","Bbsus4","Bsus4"};
    final String[] CNS_CODEdim = {
            "Cdim","C#dim","Dbdim","Ddim","D#dim","Ebdim","Edim","Fdim","F#dim","Gbdim","Gdim","G#dim","Abdim","Adim","A#dim","Bbdim","Bdim"};
    final String[] CNS_CODEm75 = {
            "Cm7-5","C#m7-5","Dbm7-5","Dm7-5","D#m7-5","Ebm7-5","Em7-5","Fm7-5","F#m7-5","Gbm7-5","Gm7-5","G#m7-5","Abm7-5","Am7-5","A#m7-5","Bbm7-5","Bm7-5"};
    final String[] CNS_CODEag = {
            "Caug","C#aug","Dbaug","Daug","D#aug","Ebaug","Eaug","Faug","F#aug","Gbaug","Gaug","G#aug","Abaug","Aaug","A#aug","Bbaug","Baug"};
    final String[] CNS_CODEad9 = {
            "Cadd9","C#add9","Dbadd9","Dadd9","D#add9","Ebadd9","Eadd9","Fadd9","F#add9","Gbadd9","Gadd9","G#add9","Abadd9","Aadd9","A#add9","Bbadd9","Badd9"};
    final String[] CNS_CODE6 = {
            "C6","C#6","Db6","D6","D#6","Eb6","E6","F6","F#6","Gb6","G6","G#6","Ab6","A6","A#6","Bb6","B6"};
    final String[] CNS_CODEm6 = {
            "Cm6","C#m6","Dbm6","Dm6","D#m6","Ebm6","Em6","Fm6","F#m6","Gbm6","Gm6","G#m6","Abm6","Am6","A#m6","Bbm6","Bm6"};

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

        String[] words = lyrics.split(" ",0);
        String wkLyrics = "";
        int matchflag = 0;
        for(int i = 0; i < words.length; i++) {
            matchflag = 0;

            for(String code:CNS_CODE){
                if(words[i].equals(code)){
                    wkLyrics += toneUp(code) + " ";
                    matchflag = 1;
                }
            }

            for(String code:CNS_CODE7){
                if(words[i].equals(code)){
                    String triad = "7";
                    wkLyrics += toneUp(code.replace(triad, "")) + triad + " ";
                    matchflag = 1;
                }
            }

            if(matchflag == 0) wkLyrics += words[i]+ " ";
        }

       tvLyrics.setText(wkLyrics);
    }

    public void onToneDownBtn(View view) {
        TextView tvLyrics = findViewById(R.id.scoreViewLyrics);
        String lyrics = tvLyrics.getText().toString();

        String[] words = lyrics.split(" ",0);
        String wkLyrics = "";
        int matchflag = 0;
        for(int i = 0; i < words.length; i++) {
            matchflag = 0;

            for(String code:CNS_CODE){
                if(words[i].equals(code)){
                    wkLyrics += toneDown(code) + " ";
                    matchflag = 1;
                }
            }

            for(String code:CNS_CODE7){
                if(words[i].equals(code)){
                    String triad = "7";
                    wkLyrics += toneDown(code.replace(triad, "")) + "7" + " ";
                    matchflag = 1;
                }
            }

            if(matchflag == 0) wkLyrics += words[i]+ " ";

        }

        tvLyrics.setText(wkLyrics);

    }

    public String toneUp(String code) {
        switch (code) {
            case "C":
                return "C#";
            case "C#":
                return "D";
            case "Db":
                return "D";
            case "D":
                return "D#";
            case "D#":
                return "E";
            case "Eb":
                return "E";
            case "E":
                return "F";
            case "F":
                return "F#";
            case "F#":
                return "G";
            case "Gb":
                return "G";
            case "G":
                return "G#";
            case "G#":
                return "A";
            case "Ab":
                return "A";
            case "A":
                return "A#";
            case "A#":
                return "B";
            case "Bb":
                return "B";
            case "B":
                return "C";
            default:
                break;
        }
        return code;
    }

    public String toneDown(String code) {
        switch (code) {
            case "C":
                return "B";
            case "C#":
                return "C";
            case "Db":
                return "C";
            case "D":
                return "C#";
            case "D#":
                return "D";
            case "Eb":
                return "D";
            case "E":
                return "Eb";
            case "F":
                return "E";
            case "F#":
                return "F";
            case "Gb":
                return "F";
            case "G":
                return "F#";
            case "G#":
                return "G";
            case "Ab":
                return "G";
            case "A":
                return "G#";
            case "A#":
                return "A";
            case "Bb":
                return "A";
            case "B":
                return "Bb";
            default:
                break;
        }
        return code;
    }

    boolean scroll = false;
    public void onScroll(View view) {
        //TODO: 自動スクロール機能
        ScrollView scrollLyrics = findViewById(R.id.scrollLyrics);
        AutoScroller autoScroller = new AutoScroller(scrollLyrics);
        String msg = "";
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