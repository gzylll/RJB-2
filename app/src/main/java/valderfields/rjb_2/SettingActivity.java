package valderfields.rjb_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences sp;
    private View imgCountView;
    private TextView imgCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        intiView();
    }

    private void intiView()
    {
        sp = getSharedPreferences("setting",MODE_PRIVATE);
        imgCountView = findViewById(R.id.uploadIMGCount);
        imgCountView.setOnClickListener(this);
        imgCount = (TextView)findViewById(R.id.imgCount);
        imgCount.setText(String.valueOf(sp.getInt("imgCount",9)));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.uploadIMGCount:
                showDialog();
                break;
        }
    }

    public void showDialog(){
        final EditText et = new EditText(this);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        new AlertDialog.Builder(this)
                .setTitle("设置图片数量(1-30)")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i = Integer.parseInt(et.getText().toString().trim());
                        if(i>0&&i<=30)
                        {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("imgCount",i);
                            imgCount.setText(String.valueOf(i));
                            editor.apply();
                        }
                        else
                        {
                            Toast.makeText(SettingActivity.this,"输入数字超出范围",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
}
