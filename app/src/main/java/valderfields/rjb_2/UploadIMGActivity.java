package valderfields.rjb_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class UploadIMGActivity extends AppCompatActivity implements
        AdapterView.OnItemLongClickListener{

    private int REQUEST_IMAGE = 1;
    //路径列表
    private List<String> imgPath = new ArrayList<>();
    //图片列表
    private List<Bitmap> bmpList = new ArrayList<>();

    private GridView imgArea;
    private GridAdapter adapter;
    private UploadPresenter presenter;

    private Button submit;

    private boolean isShowDelete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("上传图片");
        }
        initView();
        presenter = new UploadPresenter();
    }

    private void initView(){
        imgArea = (GridView)findViewById(R.id.IMG_area);
        adapter = new GridAdapter(this);
        imgArea.setAdapter(adapter);
        imgArea.setOnItemLongClickListener(this);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //presenter.uploadIMG(imgPath.get(0));
                presenter.uploadIMGs(imgPath);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.upload_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.selectIMG:
                /*
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
                */
                Intent intent = new Intent(this, MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                // 设置模式 (多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent, REQUEST_IMAGE);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 接受图片选择的数据
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 结果数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表,并更新
                UpdateImgListData(
                        data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 更新图片数组，更新显示适配
     * @param data 新的数据集
     */
    private void UpdateImgListData(List<String> data){
        for(int i=0;i<data.size();i++){
            boolean isExist = false;
            for(int j=0;j<imgPath.size();j++){
                if(data.get(i).equals(imgPath.get(j))){
                    isExist = true;
                    break;
                }
            }
            if(!isExist){
                imgPath.add(data.get(i));
                //获取新的图片
                String filepath = data.get(i);
                File file = new File(filepath);
                if (file.exists()) {
                    Bitmap bm = BitmapFactory.decodeFile(filepath);
                    //将图片更新到列表中
                    bmpList.add(bm);
                }
            }
        }
        isShowDelete = false;
        adapter.setIsShowDelete(false);
        adapter.setData(imgPath);
    }

    /**
     * 图片长按删除
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
   @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       if (isShowDelete) {
           isShowDelete = false;
           adapter.setIsShowDelete(false);
       } else {
           isShowDelete = true;
           adapter.setIsShowDelete(true);
           imgArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {

               @Override
               public void onItemClick(AdapterView<?> parent, View view,
                                       int position, long id) {
                   if(isShowDelete)
                        DeleteItem(position);//删除选中项
               }
           });
       }
       return true;
    }

    /**
     * 删除图片
     * @param i 图片id
     */
    private void DeleteItem(int i){
        imgPath.remove(i);
        bmpList.remove(i);
        adapter.setData(imgPath);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isShowDelete&& event.KEYCODE_BACK == keyCode){
            isShowDelete = false;
            adapter.setIsShowDelete(false);
            return true;
        }
        else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
