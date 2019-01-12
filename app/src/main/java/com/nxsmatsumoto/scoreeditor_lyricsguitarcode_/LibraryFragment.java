package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryFragment extends Fragment {

    List<Integer> libListId = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // ListViewに表示するデータ
        ListView lvLib = getActivity().findViewById(R.id.lvLib);
        List<String> libList = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            //主キーによる検索SQL文字列の用意。
            String sql = "SELECT * FROM score WHERE 1 = 1";
            //SQLの実行。
            Cursor cursor = db.rawQuery(sql, null);
            //SQL実行の戻り値であるカーソルオブジェクトをループさせてデータベース内のデータを取得。
            while (cursor.moveToNext()) {
                //カラムのインデックス値を取得。
                int idxId = cursor.getColumnIndex("_id");
                int idxTitle = cursor.getColumnIndex("title");
                //カラムのインデックス値を元に実際のデータを取得。
                libListId.add((int) cursor.getInt(idxId));
                libList.add(cursor.getString(idxTitle));
            }
        }
        finally {
            //データベース接続オブジェクトの解放。
            db.close();
        }

        ArrayAdapter<String> adapter =  new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, libList);
        lvLib.setAdapter(adapter);

        lvLib.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ScoreBrowseActivity.class);
                long curId = libListId.get(position);
                intent.putExtra("scoreID", curId);
                intent.putExtra("edit_mode", 2);
                startActivity(intent);
            }
        });
    }

}