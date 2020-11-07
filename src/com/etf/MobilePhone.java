package com.etf;

public class MobilePhone extends Thread{
    private int mobileId;
    private Message msg;

    public MobilePhone(int mobileId, Message msg){
        this.mobileId = mobileId;
        this.msg = msg;
    }

    public int getMobileIdId(){
        return mobileId;
    }

    public void run(){
        while(true){
            try{
                msg.sendMessage(mobileId);
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
