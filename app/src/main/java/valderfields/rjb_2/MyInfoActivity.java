package valderfields.rjb_2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyInfoActivity extends AppCompatActivity {

    private EditText name;
    private EditText oldpwd;
    private EditText newpwd1;
    private EditText newpwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
    }

    public void setOnClick1(View v){
        //创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("修改用户名");

        v = LayoutInflater.from(MyInfoActivity.this).inflate(R.layout.username,null);

        name = (EditText) v.findViewById(R.id.edit_text);

        builder.setView(v);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

        builder.setCancelable(false);

    }

    public void setOnClick2(View v){
        //创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("修改密码");

        v = LayoutInflater.from(MyInfoActivity.this).inflate(R.layout.pwd,null);

        oldpwd = (EditText) v.findViewById(R.id.oldpwd);
        newpwd1 = (EditText) v.findViewById(R.id.newpwd1);
        newpwd2 = (EditText) v.findViewById(R.id.newpwd2);


        builder.setView(v);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

        builder.setCancelable(false);

    }
}
