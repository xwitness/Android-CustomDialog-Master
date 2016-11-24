package com.witness.customdialog.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.witness.customdialog.R;


/**
 * Created by Sai on 15/8/15.
 * 默认的SVProgress效果
 */
public class ProgressDefaultView extends LinearLayout {
    private int resLoading = R.drawable.ic_status_loading;
    private int resInfo = R.drawable.ic_status_info;
    private int resSuccess = R.drawable.ic_status_success;
    private int resError = R.drawable.ic_status_error;
    private ImageView ivBigLoading, ivSmallLoading, ivStatus;
    private CircleProgressBar circleProgressBar;
    private TextView tvMsg;

    private RotateAnimation mRotateAnimation;

    public ProgressDefaultView(Context context) {
        super(context);
        initViews();
        init();
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_default, this, true);
        ivBigLoading = (ImageView) findViewById(R.id.ivBigLoading);
        ivSmallLoading = (ImageView) findViewById(R.id.ivSmallLoading);
        ivStatus = (ImageView) findViewById(R.id.ivStatus);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
    }

    private void init() {
        // 动画初始化设置
        mRotateAnimation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(1000L);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
    }

    public void show() {
        clearAnimations();
        ivBigLoading.setImageResource(resLoading);
        ivBigLoading.setVisibility(View.VISIBLE);
        ivSmallLoading.setVisibility(View.GONE);
        ivStatus.setVisibility(View.GONE);
        circleProgressBar.setVisibility(View.GONE);
        tvMsg.setVisibility(View.GONE);
        //开启旋转动画
        ivBigLoading.startAnimation(mRotateAnimation);
    }

    public void showWithStatus(String string) {
        if (string == null) {
            show();
            return;
        }
        clearAnimations();
        ivSmallLoading.setImageResource(resLoading);
        tvMsg.setText(string);
        ivBigLoading.setVisibility(View.GONE);
        ivSmallLoading.setVisibility(View.VISIBLE);
        ivStatus.setVisibility(View.GONE);
        circleProgressBar.setVisibility(View.GONE);
        tvMsg.setVisibility(View.VISIBLE);
        //开启旋转动画
        ivSmallLoading.startAnimation(mRotateAnimation);
    }

    public void showInfoWithStatus(String string) {
        showBaseStatus(resInfo, string);
    }

    public void showSuccessWithStatus(String string) {
        showBaseStatus(resSuccess, string);
    }

    public void showErrorWithStatus(String string) {
        showBaseStatus(resError, string);
    }

    public void showWithProgress(String string) {
        showProgress(string);
    }

    public CircleProgressBar getCircleProgressBar() {
        return circleProgressBar;
    }

    public void setText(String string) {
        tvMsg.setText(string);
    }

    public void showProgress(String string) {
        clearAnimations();
        tvMsg.setText(string);
        ivStatus.setVisibility(View.GONE);
        ivBigLoading.setVisibility(View.GONE);
        ivSmallLoading.setVisibility(View.GONE);
        circleProgressBar.setVisibility(View.VISIBLE);
        tvMsg.setVisibility(View.VISIBLE);
    }

    public void showBaseStatus(int res, String string) {
        clearAnimations();
        ivStatus.setImageResource(res);
        tvMsg.setText(string);
        ivBigLoading.setVisibility(View.GONE);
        ivSmallLoading.setVisibility(View.GONE);
        ivStatus.setVisibility(View.VISIBLE);

        circleProgressBar.setVisibility(View.GONE);
        tvMsg.setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        clearAnimations();
    }

    private void clearAnimations() {
        ivBigLoading.clearAnimation();
        ivSmallLoading.clearAnimation();
    }

}
