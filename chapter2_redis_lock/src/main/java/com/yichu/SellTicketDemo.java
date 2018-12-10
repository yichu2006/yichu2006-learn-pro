package com.yichu;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/11/27
 * Desc:
 */
public class SellTicketDemo {
    public static void main(String[] args) {
        SellTicket st = new SellTicket();
        for(int i=0; i<3; i++) {
            new Thread(st, "窗口"+(i+1)).start();
        }
    }
}
