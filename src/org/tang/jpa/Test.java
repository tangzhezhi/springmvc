package org.tang.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	
	public static void main(String[] args) {
		String addr = GetAddr("31.875676", "117.3094928");
		System.out.println(addr);
		//getCoordinate("中国");
	}

	/**
	 * 根据经纬度反向解析地址，有时需要多尝试几次
	 * 注意:(摘自：http://code.google.com/intl/zh-CN/apis/maps/faq.html
	 * 提交的地址解析请求次数是否有限制？) 如果在 24 小时时段内收到来自一个 IP 地址超过 15,000 个地址解析请求， 或从一个 IP
	 * 地址提交的地址解析请求速率过快，Google 地图 API 编码器将用 620 状态代码开始响应。 如果地址解析器的使用仍然过多，则从该 IP
	 * 地址对 Google 地图 API 地址解析器的访问可能被永久阻止。
	 *
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @return
	 */
	public static String GetAddr(String latitude, String longitude) {
		String addr = "";

		// 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
		// 密钥可以随便写一个key=abc
		// output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
		String url = String.format(
				"http://ditu.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s",
				latitude, longitude);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					System.out.println(data);
					String[] retList = data.split(",");
					if (retList.length > 2 && ("200".equals(retList[0]))) {
						addr = retList[2];
						addr = addr.replace("\"", "");
					} else {
						addr = "";
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return addr;
	}

	public static void getCoordinate(String addr)
	{
		String addrs = "";
	    String address = null;
		try {
			address = java.net.URLEncoder.encode(addr,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		};
        String output = "csv";
        String key = "abc";
        String url = String.format("http://maps.google.com/maps/geo?q=%s&output=%s&key=%s", address, output, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        //进行转码
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					System.out.println(data);
					String[] retList = data.split(",");
					/*
		            String latitude = retList[2];
		            String longitude = retList[3];

		            System.out.println("纬度"+ latitude);
		            System.out.println("经度"+ longitude);
		            */

					if (retList.length > 2 && ("200".equals(retList[0]))) {
						addrs = retList[2];
						addrs = addr.replace("\"", "");
					} else {
						addrs = "";
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(addrs);
	}


}
