package com.etf;

public class Main {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Message msg = new Message(n);
        MobilePhone[] phones = new MobilePhone[n];
        for(int i = 0; i < n; ++i)
            phones[i] = new MobilePhone(i, msg);

        for(MobilePhone x : phones)
            x.start();
    }
}
