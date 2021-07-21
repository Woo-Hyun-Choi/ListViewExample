package net.hutek.listviewexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);

        // 데이터를 저장하게 되는 리스트
        List<String> list = new ArrayList<>();

        // 리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        // context, layout Res int source, List object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);

        // 리스트뷰의 어댑터를 지정해준다.
        listView.setAdapter(adapter);

        // 리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                String selected_item = (String) adapterView.getItemAtPosition(position);
                //텍스트뷰에 출력
                textView.setText(selected_item + "를 선택하셨습니다");

            }
        });

        for(int i=1; i <=9999; i++) {
            list.add(i+"번째");
        }


    }
}