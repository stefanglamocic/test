package com.etf;

import java.util.Random;

import static com.etf.ThreadColor.*;

public class Message {
    private Random rng;
    private int n;
    public boolean[] available;

    public Message(int n){
        rng = new Random();
        this.n = n;
        available = new boolean[n];
        for(int i = 0; i < n; i++)
            available[i] = true;
    }

    public void sendMessage(int callerId) throws InterruptedException{
        int receiver;
        synchronized (this){
            receiver = rng.nextInt(n);
            if(receiver == callerId)
                receiver = (receiver + 1) % (n - 1);
            while(!available[callerId])
                wait();
            System.out.println("Telefon " + ANSI_GREEN + callerId + ANSI_RESET + ". pokusava uspostaviti poziv sa "
                    + ANSI_YELLOW + receiver + ANSI_RESET + ".");
            //available[callerId] = false; // telefon koji pokusava uspostaviti poziv je zauzet

            while(!available[receiver]) {
                System.out.println(ANSI_RED + "Telefon " + receiver + ". je zauzet!!!" + ANSI_RESET);
                wait();
            }
            System.out.println(ANSI_GREEN + "Telefon " + callerId + ". je uspostavio poziv sa "
                    + receiver + "." + ANSI_RESET);
            available[receiver] = false; // telefon s kojim je uspostavljen poziv je zauzet
            available[callerId] = false; // telefon koji pokusava uspostaviti poziv je zauzet
        }

        Thread.sleep(3000);

        synchronized (this) {
            System.out.println(ANSI_BLUE + "Poziv izmedju telefona " + callerId + ". i telefona " +
                    + receiver + ". je zavrsen" + ANSI_RESET);
            available[receiver] = true;
            available[callerId] = true;
            notifyAll();
        }

    }
}
