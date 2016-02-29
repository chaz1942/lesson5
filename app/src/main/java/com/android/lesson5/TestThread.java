package com.android.lesson5;

/**
 * Created by scofieldchang on 16/2/28.
 */
public class TestThread extends Thread {
    UpdateUI update;
    public TestThread(UpdateUI update){
        this.update = update;
    }
    @Override
    public void run() {
        String sendMessage = "sendMessage";
        String receMessage = "receMessage";
        update.UpdateReceMessage(receMessage);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update.UpdateSendMessage(sendMessage);
    }
}
