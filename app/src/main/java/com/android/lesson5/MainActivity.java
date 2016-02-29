package com.android.lesson5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements UpdateUI{
    private TextView display;
    private EditText editTextView;
    private Button sendBtn,testBtn;
    private String LOG = "socket";
    private String messageContent;
    int UPDATE_UI = 0;
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    if (display != null)
                        display.append(messageContent);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWedgit();
        Thread testThread = new TestThread(this);
        testThread.start();
    }

    private void initWedgit(){
        editTextView = (EditText)findViewById(R.id.edit_zone);
        display = (TextView)findViewById(R.id.display_zone);

        sendBtn = (Button)findViewById(R.id.send_button);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextView.getText().toString();
                Log.d(LOG,content);
                UpdateSendMessage(content);

            }
        });
        testBtn = (Button)findViewById(R.id.test_button);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = "rece";
                UpdateReceMessage(test);
            }
        });

    }

    @Override
    public void UpdateSendMessage(String message) {
        messageContent = "send: " + message + "\n";
        myHandler.sendEmptyMessage(UPDATE_UI);
    }

    @Override
    public void UpdateReceMessage(String message) {
        messageContent = "rece: " + message + "\n";
        myHandler.sendEmptyMessage(UPDATE_UI);

    }
}
