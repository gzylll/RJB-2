package valderfields.rjb_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View userMessage;
    private View uploadIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        userMessage = findViewById(R.id.userMessage);
        userMessage.setOnClickListener(this);
        uploadIMG = findViewById(R.id.uploadIMG);
        uploadIMG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadIMG:
                Intent intent = new Intent(this,UploadIMGActivity.class);
                startActivity(intent);
                break;
            case R.id.userMessage:
            case R.id.avatar:
            case R.id.username:
                Intent intent1 = new Intent(this,MyInfoActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
