package net.hutek.listviewexample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "ListViewExample";

    TextView textView;
    ListView listView;
    ArrayList items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);


        init_ArrayList(20);

        ListView_Adapter mAdapter = new ListView_Adapter(this, items);

        // 리스트뷰의 어댑터를 지정해준다.
        listView.setAdapter(mAdapter);


        // 리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                ListView_Item item = (ListView_Item) adapterView.getItemAtPosition(position);
                //텍스트뷰에 출력
                String title = item.getTitle();
                Log.d(TAG, title);
            }
        });
    }

    // Item 리스트를 생성하는 함수
    private void init_ArrayList(int count) {

        // Drawable 이미지 리소스 ID 값을 가져오기 위해 Resource객체 생성
        Resources res = getResources();

        // 함수의 인자로 넘겨준 count 아이템 개수만큼 반복, 아이템 추가
        for (int i = 0; i < count; i++) {
            // 이미지리소스 id 값을 가져옴, res.getIdentifier("이미지 이름", "리소스 폴더 이름", 현재패키지 이름)
            int img_ID = res.getIdentifier("listview_item" + (i % 4), "drawable", getPackageName());
            // item 객체 생성하여 리스트에 추가
            items.add(new ListView_Item((i + 1) + "번째 아이템", img_ID, "hello", "world"));
            Log.d(TAG, "이것은 " + img_ID);
        }
    }

    // item 데이터 객체, 클래스 생성
    private class ListView_Item {
        // 아이템 당 각각의 내용과 title이 담길 변수를 선언해준다
        private int thumbnail;
        private String title;
        private String duration;
        private String resolution;

        // 생성자 함수
        public ListView_Item(String title, int image, String duration, String resolution) {
            this.thumbnail = image;
            this.title = title;
            this.duration = duration;
            this.resolution = resolution;
        }

        public int getThumbnail() {
            return thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public String getDuration() {
            return duration;
        }

        public String getResolution() {
            return resolution;
        }
    }

    // Adapter 생성 - ListView 연결
    // ListView를 표현할 Adapter 구현, BaseAdapter 상속
    private class ListView_Adapter extends BaseAdapter {


        //보여줄 Item 몰록을 저장할 List
        List<ListView_Item> items = null;
        Context context;

        public ListView_Adapter(Context context, List<ListView_Item> items) {
            this.items = items;
            this.context = context;
        }

        // Adapter.getCount(), 아이템 개수 반환하는 함수
        @Override
        public int getCount() {
            return items.size();
        }

        // Adapter.getItem(int position), 해당 위치 아이템 반환 함수
        @Override
        public ListView_Item getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e(TAG, "getView :: position = " + position);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.listview_item, parent, false);

            // ListView의 Item을 구성하는 뷰 연결
            ImageView thumbnail = view.findViewById(R.id.listitem_thumbnail);
            TextView title = view.findViewById(R.id.listitem_title);
            TextView duration = view.findViewById(R.id.listitem_duration);
            TextView resolution = view.findViewById(R.id.listitem_resolution);

            // ListView의 Item을 구성하는 뷰 세팅
            ListView_Item item = items.get(position);
            thumbnail.setImageResource(item.getThumbnail());
            title.setText(item.getTitle());
            duration.setText(item.getDuration());
            resolution.setText(item.getResolution());

            return view;

        }
    }
}
