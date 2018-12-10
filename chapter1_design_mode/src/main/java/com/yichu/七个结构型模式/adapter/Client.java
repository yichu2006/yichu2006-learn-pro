package com.yichu.七个结构型模式.adapter;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/7
 * Desc: 客户端（笔记本）  笔记本只有USB接口，其他接口的线无法使用
 */
public class Client {

    private void test1(Target t){
        t.handleReq();
    }

    public static void main(String[] args) {

        Client c = new Client();
        Target t = new Adapter(); //类适配器
        c.test1(t);

        Client c2 = new Client();
        Adaptee a = new Adaptee();
        Target t2 = new Adapter2(a);  //对象适配器
        c2.test1(t2);





    }
}
