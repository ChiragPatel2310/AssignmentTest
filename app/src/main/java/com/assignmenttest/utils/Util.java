package com.assignmenttest.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class Util {
    public static Util objUtil;
    public static Activity mContext;

    public static Util getInstance(Activity context) {
        mContext = context;
        if (objUtil == null) {
            objUtil = new Util();
        }
        return objUtil;
    }

    public void ShowToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void NavigationActivity(Class className, String key, String value, boolean isFinish) {
        Intent intent = new Intent(mContext, className);
        if (!key.isEmpty()) {
            intent.putExtra(key, value);
        }
        mContext.startActivity(intent);
        if (isFinish) {
            mContext.finish();
        }
    }

    public void ClearAllPreviousActivity(Class className) {
        Intent intent = new Intent(mContext, className);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }
}
