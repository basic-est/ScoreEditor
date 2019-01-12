package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DiagramFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diagram, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // ListViewに表示するデータ
        ListView lvDiagram = getActivity().findViewById(R.id.lvDiagram);
        List<String> diagramList = new ArrayList<>();

        DatabaseHelper_d helper = new DatabaseHelper_d(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='diagram';";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            String result = c.getString(0);
            if ( result.equals("1")) {
                //主キーによる検索SQL文字列の用意。
                String sql = "SELECT * FROM diagram WHERE 1 = 1";
                //SQLの実行。
                Cursor cursor = db.rawQuery(sql, null);
                //SQL実行の戻り値であるカーソルオブジェクトをループさせてデータベース内のデータを取得。
                while (cursor.moveToNext()) {
                    //カラムのインデックス値を取得。
                    int idxTitle = cursor.getColumnIndex("name");
                    //カラムのインデックス値を元に実際のデータを取得。
                    diagramList.add(cursor.getString(idxTitle));
                }
            }
        }
        finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }

        ArrayAdapter<String> adapter =  new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, diagramList);

        lvDiagram.setAdapter(adapter);

    }


}

