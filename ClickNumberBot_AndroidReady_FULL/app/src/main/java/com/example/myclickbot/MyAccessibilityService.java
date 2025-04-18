package com.example.myclickbot;

import android.accessibilityservice.AccessibilityService;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = "ClickBot";
    private static boolean isRunning = false;
    private boolean isClicking = false;

    public static void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!isRunning || isClicking) return;
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;
        isClicking = true;
        clickAllOnes(rootNode);
        new Handler(Looper.getMainLooper()).postDelayed(() -> isClicking = false, 1000);
    }

    private void clickAllOnes(AccessibilityNodeInfo node) {
        if (node == null) return;
        CharSequence text = node.getText();
        if (text != null && text.toString().trim().equals("1")) {
            if (node.isClickable()) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.d(TAG, "Clicked on: " + text);
            }
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            clickAllOnes(node.getChild(i));
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Service interrupted");
    }
}