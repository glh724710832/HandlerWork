package com.gary.handlerwork;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private int countDownNumber = 5; //用于线程倒计时次数
    private TextView tv_main;
    private ImageView iv_activity_splash;  //实验设置背景图

    private MyHandler myHandler;
    private  boolean  isClickJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

    }



    private void initView() {
        tv_main = findViewById(R.id.tv_main);
        iv_activity_splash = findViewById(R.id. iv_activity_splash);


        tv_main.setOnClickListener(this);

        myHandler = new MyHandler();
        MyThread my = new MyThread();
        my.start();

    }
    //将跳转至ViewPager界面方法抽出来单独做一个方法
    private void IntentActivity() {
        Intent intent = new Intent(HomeActivity.this,ViewPagerActivity.class);
        startActivity(intent);
    }

    //点击跳过直接跳转到页面
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_main:
                isClickJump =true;
                IntentActivity();

                break;
            default:
                break;
        }
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i =countDownNumber;i>=0;i--) {
                if (!isClickJump) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.arg1 = i;
                    myHandler.sendMessage(message);
                }
            }

        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int countdown = msg.arg1;
            if (countdown >0){
                String str =String.format("%d秒后跳转",countdown);
                tv_main.setText(str);
            }else{
                IntentActivity();
            }




        }
    }
}
