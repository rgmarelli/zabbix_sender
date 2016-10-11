package com.mytest;

import java.io.IOException;

import io.github.hengyunabc.zabbix.sender.DataObject;
import io.github.hengyunabc.zabbix.sender.SenderResult;
import io.github.hengyunabc.zabbix.sender.ZabbixSender;

public class MyApplication {
	
	public static void zabbix_sender(String zabbix_server, String zabbix_host, String key, String value) throws IOException {
		
		// Sample code from: https://github.com/hengyunabc/zabbix-sender
		
        int port = 10051;
        ZabbixSender zabbixSender = new ZabbixSender(zabbix_server, port);

        DataObject dataObject = new DataObject();
        dataObject.setHost(zabbix_host);
        dataObject.setKey(key);
        dataObject.setValue(value);
        // TimeUnit is SECONDS.
        dataObject.setClock(System.currentTimeMillis()/1000);
        SenderResult result = zabbixSender.send(dataObject);

        System.out.println("result:" + result);
        if (result.success()) {
            System.out.println("send success.");
        } else {
            System.err.println("send fail!");
        }			
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Java Zabbix_Sender test");
		
		String zabbix_server = "thezabbixserver";	// the zabbix server
		String zabbix_host = "myhost";				// name of monitored host (as registered in Zabbix)
		String key = "my.trapper.item.key";			// https://www.zabbix.com/documentation/3.0/manual/config/items/itemtypes/trapper
		String value = "a test value";				// some value you want to set
		
		MyApplication.zabbix_sender(zabbix_server, zabbix_host, key, value);
		
	}
}
