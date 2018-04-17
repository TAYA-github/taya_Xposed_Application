package com.xposedapplicationtaya.hookApp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileModule {
    protected static File file = new File("/sdcard/tayaXoposed.txt");
    private String TAG = "taya file";
    private String Package_Name = null;
    private String Method_Name = null;

    public void buildFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG,"create new file failed");
            }
        }

    }

    public void save(String packageName, String methodName) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(packageName);
            bufferedWriter.newLine();
            bufferedWriter.write(methodName);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            Log.d(TAG, "write file failed");
        }
    }

    public String getPackageName() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Package_Name = bufferedReader.readLine();
            Method_Name = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "getPackageName not found file");
        } catch (IOException e) {
            Log.d(TAG, "read file failed");
        }
        return Package_Name;
    }

    public String getMethodName() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Package_Name = bufferedReader.readLine();
            Method_Name = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "getMethodName not found file");
        } catch (IOException e) {
            Log.d(TAG, "read file failed");
        }
        return Method_Name;
    }
}
