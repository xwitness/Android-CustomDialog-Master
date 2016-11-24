package com.witness.customdialogdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.witness.customdialog.CustomDialog;
import com.witness.customdialog.listener.OnDismissListener;

public class MainActivity extends Activity {
    private CustomDialog mDialog;
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDialog = new CustomDialog(this);
        mDialog.setOnDismissListener(new OnDismissListener(){
            @Override
            public void onDismiss(CustomDialog hud) {
                // todo something, like: finish current activity
                Toast.makeText(getApplicationContext(),"dismiss",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void show(View view){
        mDialog.show();
    }
    public void showWithMaskType(View view){
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.None);
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.Black);
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.BlackCancel);
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.Clear);
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.ClearCancel);
        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.Gradient);
//        mDialog.showWithMaskType(CustomDialog.SVProgressHUDMaskType.GradientCancel);
    }
    public void showWithStatus(View view){
        mDialog.showWithStatus("加载中...", CustomDialog.SVProgressHUDMaskType.Gradient);
    }
    public void showInfoWithStatus(View view){
        mDialog.showInfoWithStatus("这是提示", CustomDialog.SVProgressHUDMaskType.None);
    }
    public void showSuccessWithStatus(View view){
        mDialog.showSuccessWithStatus("恭喜，提交成功！");
    }
    public void showErrorWithStatus(View view){
        mDialog.showErrorWithStatus("不约，叔叔我们不约～", CustomDialog.SVProgressHUDMaskType.GradientCancel);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (mDialog.getProgressBar().getMax() != mDialog.getProgressBar().getProgress()) {
                mDialog.getProgressBar().setProgress(progress);
                mDialog.setText(progress+"%");

                mHandler.sendEmptyMessageDelayed(0,500);
            }
            else{
                mDialog.dismiss();
            }

        }
    };
    public void showWithProgress(View view){
        progress = 0;
        mDialog.getProgressBar().setProgress(progress);//先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mDialog.showWithProgress(progress + "%", CustomDialog.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(0,500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(mDialog.isShowing()){
                mDialog.dismiss();
                mHandler.removeCallbacksAndMessages(null);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

}
