package net.ib.base.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public abstract class ServerInfo {
	private static Logger logger = Logger.getLogger(ServerInfo.class);
	
	//获取服务器的网卡物理（Mac）地址
	public List<String> getMacAddrList(){
        List<String> macList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}");
        try{
            Enumeration <NetworkInterface> em = NetworkInterface.getNetworkInterfaces();
            String MacAddr = "";
            while (em.hasMoreElements ()) {
                NetworkInterface nic = em.nextElement();
                byte[] b = nic.getHardwareAddress();
                if (b == null){
                    continue;
                }
                for (int i = 0; i < b.length; i++){
                	MacAddr+=byteHEX(b[i])+"-";
                }
                MacAddr=StringUtils.substringBeforeLast(MacAddr,"-").toUpperCase();
                logger.debug("MacAddr is "+MacAddr);
                Matcher matcher = pattern.matcher(MacAddr);
                if(matcher.matches())
                	macList.add(MacAddr);
                MacAddr = "";
            }
        }catch (SocketException e){
            e.printStackTrace ();
            System.exit (-1);
        }
        return macList;
    }

	//获取服务器的IP地址
    public List<String> getIPList (){
    	List<String> ipList = new ArrayList<String>();
    	try{
            Enumeration <?> e1 = (Enumeration <?>) NetworkInterface.getNetworkInterfaces();
            while(e1.hasMoreElements()){
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                Enumeration <?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements ()){
                    InetAddress ia = (InetAddress) e2.nextElement ();
                    if(ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1")){
                    	ipList.add(ia.getHostAddress());
                    }
                }
            }
        }catch (SocketException e){
        	e.printStackTrace ();
            System.exit(-1);
        }
        return ipList;
    }
    
    public static String getNowDate(){
    	String nowDate="";
    	
    	Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1; //国外从0月开始，11月结束
       	int date = c.get(Calendar.DATE);
       
      	nowDate+=String.valueOf(year)+"-";
    	if(month<10) nowDate+="0";
    	nowDate+=String.valueOf(month)+"-";
    	if(date<10) nowDate+="0";
    	nowDate+=String.valueOf(date);
    	
    	return nowDate;
    }
    
    public abstract String 	getCustomerName(); 
    public abstract String 	getProductName();
    public abstract String 	getProductVersion();
    public abstract int 	getUserCount();
    
    /* 一个将字节转化为十六进制ASSIC码的函数 */
    public String byteHEX (byte ib){
        char[] Digit =
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String (ob);
        return s;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
