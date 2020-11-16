package com.hello.demo_hello;

import java.util.Collections;
import java.util.Scanner;

//@SpringBootApplication
//@RestController
public class DemoHelloApplication {
    public static Thread t;
    public static boolean ifcont = true;

    public class Threadnew implements Runnable {


        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        // SpringApplication.run(DemoHelloApplication.class, args);
        Scanner s = new Scanner(System.in);
        //请输入系统名称
        System.out.println("请输入系统名称:如S02_1/P02_1");
        String sNum = s.nextLine();
        String portNum = null;
        String rportNum1 = null;
        String rportNum2 = null;
        String clqName = null;
        String rePos1 = null;
        String rePos2 = null;
        System.out.println(sNum.substring(0, 1));
        if (sNum.substring(0, 1).equals("P")) {
            portNum = "40" + sNum.substring(1, 3);
            rportNum1 = "6003";
            rportNum2 = "6004";
            clqName = "QMGR_CLUSTER2";
            rePos1 = "QMGR.CLUSTER3";
            rePos2 = "QMGR.CLUSTER4";
        } else if (sNum.substring(0, 1) == "S") {
            portNum = "50" + sNum.substring(1, 3);
            clqName = "QMGR_CLUSTER1";
            rePos1 = "QMGR.CLUSTER1";
            rePos2 = "QMGR.CLUSTER2";
        } else {
            System.out.println("本玩具目前只支持S和P系统脚本生成。");

        }
        //请输入端口号
        // System.out.println("请输入端口号:");
        // String portNum = s.nextLine();
        //请输入IP地址
        System.out.println("请输入IP地址:如127.0.0.1");
        String Ip = s.nextLine();
        String line = "*QMGR." + sNum + "_1系统---------------------------------------------" + "\r\n" + "*创建监听" +
                "\r\n" + "DEFINELISTENER(LISTENER.TCP) TRPTYPE(TCP) PORT(" + portNum + ") CONTROL(QMGR) replace" +
                "\r\n" + "start LISTENER(LISTENER.TCP)" + "\r\n" + "*创建集群存储库" + "\r\n" + "alter QMGR REPOS(" + clqName + ")" +
                "\r\n" + "*创建集群接收方通道" + "\r\n" + "DEFINE CHANNEL(TO." + sNum + ") CHLTYPE(CLUSRCVR) TRPTYPE(TCP) CONNAME('" + Ip + "(" + portNum + ")') CLUSTER(" + clqName + ") maxmsgl(99999999) replace" +
                "\r\n" + "*创建集群发送方通道" + "\r\n" + "DEFINE CHANNEL(TO." + rePos1.substring(5) + ") CHLTYPE(CLUSSDR) TRPTYPE(TCP) CONNAME('" + Ip + "(" + rportNum1 + ")') CLUSTER(" + clqName + ") maxmsgl(99999999) replace" +
                "\r\n" + "DEFINE CHANNEL(TO." + rePos2.substring(5) + ") CHLTYPE(CLUSSDR) TRPTYPE(TCP) CONNAME('" + Ip + "(" + rportNum2 + ")') CLUSTER(" + clqName + ") maxmsgl(99999999) replace" + "\r\n" + "*修改集群传输队列最大消息长度" + "\r\n+";
        //Iterable<String> words = Splitter.on(' ').trimResults().omitEmptyStrings().split(line);
        Iterable<String> words = Collections.singleton(line);
        for (String word : words) {
            System.out.println(word);
        }
    }


}
