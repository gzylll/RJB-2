package valderfields.rjb_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookupUserActivity extends AppCompatActivity {

    private ListView userList;
    private LookupUserPresenter presenter;
    private SimpleAdapter adapter;
    //list中显示的用户数据
    private List<HashMap<String,String>> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_user);
        presenter = new LookupUserPresenter(this);
        initView();
        presenter.initData();
    }

    private void initView()
    {
        userList = (ListView)findViewById(R.id.userList);
        //生成适配器
        adapter = new SimpleAdapter(this,
                dataList,//数据来源
                R.layout.item_listview,//ListItem的XML实现
                new String[] {"uid", "username"},
                new int[] {R.id.userUID,R.id.userName});
        userList.setAdapter(adapter);
    }

    public void updateData(List<HashMap<String,String>> dataList)
    {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        Log.i("1",dataList.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
