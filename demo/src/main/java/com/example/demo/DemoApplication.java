package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import com.google.common.base.Splitter;

@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        Scanner s = new Scanner(System.in);
        //请输入系统名称
        System.out.println("请输入系统名称:");
        String sNum = s.nextLine();
        //请输入端口号
        System.out.println("请输入端口号:");
        String portNum = s.nextLine();
        //请输入IP地址
        System.out.println("请输入IP地址:");
        String Ip = s.nextLine();
        String line = "*QMGR.S" + sNum + "_1系统---------------------------------------------" + "\r\n" + "*创建监听" + "\r\n" + "DEFINE\tLISTENER(LISTENER.TCP)\tTRPTYPE(TCP)\tPORT(" + portNum + ")\tCONTROL(QMGR)\treplace" + "\r\n" + "start\tLISTENER(LISTENER.TCP)" + "\r\n" + "*创建集群存储库" + "\r\n" + "alter\tQMGR\tREPOS(QMGR_CLUSTER2)" + "\r\n" + "*创建集群接收方通道" + "\r\n" + "define\tCHANNEL(TO." + sNum + ")\tCHLTYPE(CLUSRCVR)\tTRPTYPE(TCP)\tCONNAME('" + Ip + "(" + portNum + ")')\tCLUSTER(QMGR_CLUSTER2)\tmaxmsgl(99999999)\treplace" + "\r\n" + "*创建集群发送方通道" + "\r\n" + "DEFINE\tCHANNEL(TO.CLUSTER4)\tCHLTYPE(CLUSSDR)\tTRPTYPE(TCP)\tCONNAME('" + Ip + "(" + portNum + ")')\tCLUSTER(QMGR_CLUSTER2)\tmaxmsgl(99999999)\treplace";
        Iterable<String> words = Splitter.on(' ').trimResults().omitEmptyStrings().split(line);
        for (String word : words) {
            System.out.println(word);
        }
    }

}
