package com.xposedapplicationtaya.hookApp;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "taya activity";
    protected static String Package_Name = null;
    protected static String Method_Name = null;
    public static File file;
    protected static FileModule fileModule = new FileModule();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView Package, Method;
        Button button;

        Package = findViewById(R.id.PackageName_Text);
        Method = findViewById(R.id.MethodName_Text);
        button = findViewById(R.id.Yes_Button);

        checkPermission();
        getPackMeth();
        Package.setText(Package_Name);
        Method.setText(Method_Name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Package_Name = Package.getText().toString();
                Method_Name = Method.getText().toString();
                savePackMeth(Package_Name, Method_Name);
            }
        });
    }

    private void getPackMeth() {
        Package_Name = fileModule.getPackageName();
        Method_Name = fileModule.getMethodName();
    }

    private void savePackMeth(String package_Name, String method_Name) {
        fileModule.save(package_Name, method_Name);
        Log.w(TAG, "Package Name = " + Package_Name);
        Log.w(TAG, "Method Name = " + Method_Name);
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "checkPermission: 已经授权！");
        }
    }
}
