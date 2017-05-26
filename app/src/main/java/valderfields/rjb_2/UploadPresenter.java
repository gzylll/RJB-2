package valderfields.rjb_2;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 上传事件，向服务器上传图片
 * Created by 11650 on 2017/5/25.
 */

public class UploadPresenter {

    public void uploadIMGs(List<String> imgs){
        Log.e("IMG","uploadIMG");
        MediaType MEDIA_TYPE = null;
        final OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for(int i=0;i<imgs.size();i++){
            File file = new File(imgs.get(i));
            if(file.getName().endsWith("jpg")||file.getName().endsWith("jpeg"))
            {
                MEDIA_TYPE = MediaType.parse("image/jpeg");
            }
            else if(file.getName().endsWith("png"))
            {
                MEDIA_TYPE = MediaType.parse("image/png");
            }
            else if(file.getName().endsWith("gif"))
            {
                MEDIA_TYPE = MediaType.parse("image/gif");
            }
            builder.addFormDataPart("img",file.getName(), RequestBody.create(MEDIA_TYPE,file));
        }

        MultipartBody requestBody = builder.build();
        final Request request = new Request.Builder()
                .url("http://114.115.142.214:8080/ImageSortServer/AddImageServlet")//地址
                .post(requestBody)//添加请求体
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("IMG","onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("IMG",response.body().string());
                    }
                });
            }
        }).start();
    }

}
