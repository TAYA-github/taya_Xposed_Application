package com.xposedapplicationtaya.hookApp;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class XposedController implements IXposedHookLoadPackage {
    private static final String TAG = "taya xposed";
    private String Package_Name = null;
    private String Method_Name = null;
    private static FileModule fileModule = new FileModule();

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        getPackMethName();
        if (!lpparam.packageName.equals(Package_Name)) {
            return;
        }
        Log.w(TAG, "found package = " + Package_Name);
        XposedHelpers.findAndHookMethod(Package_Name, lpparam.classLoader, Method_Name, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String result = param.getResult().toString();
                Log.w(TAG, result);
            }
        });

    }

    private void getPackMethName() {
        fileModule.buildFile();
        Package_Name = fileModule.getPackageName();
        Method_Name = fileModule.getMethodName();
    }
}
