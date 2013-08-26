package net.ib.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.ib.base.InitServlet;

import org.apache.log4j.Logger;

import license.License;

public class LicenseCheck {
	private static Logger logger = Logger.getLogger(InitServlet.class);

	public static boolean Check(License license){
		ServerInfoUtil serverInfo=new ServerInfoUtil();
		
		//检查客户单位名称
		if(license.getCustomerNameLimited().toUpperCase().equals("limited".toUpperCase()) &&
			!license.getCustomerName().toUpperCase().equals(serverInfo.getCustomerName().toUpperCase())){
			logger.info("License授权客户单位名称与客户单位名称不匹配");
			return false;	
		}
		
		//检查授权产品名称
		if(license.getProductNameLimited().toUpperCase().equals("limited".toUpperCase()) &&
			!license.getProductName().toUpperCase().equals(serverInfo.getProductName().toUpperCase())){
			logger.info("License授权产品名称与当前产品名称不匹配");
			return false;	
		}		

		//检查授权产品版本
		if(license.getProductVersionLimited().toUpperCase().equals("limited".toUpperCase())){
			String licVersion=license.getProductVersion().toUpperCase();
			String curVersion=serverInfo.getProductVersion();
			int xPos=licVersion.indexOf(".X");
			if(xPos>=0 && curVersion.length()>=xPos){
				if(!(curVersion.toUpperCase().substring(0,xPos).equals(licVersion.toUpperCase().substring(0,xPos))
						|| curVersion.toUpperCase().equals(licVersion.toUpperCase()))){
					logger.info("License授权产品版本与当前产品版本不匹配");
					return false;	
				}
			}
			if(xPos>=0 && curVersion.length()<xPos){
				logger.info("License授权产品版本与当前产品版本不匹配");
				return false;
			}
			if(xPos<0 && !curVersion.toUpperCase().equals(licVersion.toUpperCase())) return false;
		}

		//检查授权终端用户数
		if(license.getUserCountLimited().toUpperCase().equals("limited".toUpperCase()) &&
			(Integer.parseInt(license.getUserCount())<serverInfo.getUserCount())){
			logger.info("License授权用户数与实际用户数不匹配");
			return false;	
		}
		
		//检查授权网卡地址
		if(license.getMacAddressLimited().toUpperCase().equals("limited".toUpperCase())){
			List<String> macAddrList=serverInfo.getMacAddrList();
			int macAddrCount=macAddrList.size();
			boolean result=false;
			String licMacAddress=license.getMacAddress().toUpperCase();
			for(int i=0;i<macAddrCount && !result;i++){
				if(macAddrList.get(i).toUpperCase().equals(licMacAddress)){
					result=true;
				}
			}
			if(!result){
				logger.info("License授权网卡地址与实际网卡地址不匹配");
				return false;	
			}
		}
		
		//检查授权的IP地址
		if(license.getIpAddressLimited().toUpperCase().equals("limited".toUpperCase())){
			List<String> ipAddrList=serverInfo.getIPList();
			int ipAddrCount=ipAddrList.size();
			boolean result=false;
			String licIPAddress=license.getIpAddress().toUpperCase();
			for(int i=0;i<ipAddrCount && !result;i++){
				if(ipAddrList.get(i).toUpperCase().equals(licIPAddress)){
					result=true;
				}
			}
			if(!result){
				logger.info("License授权IP地址与实际IP地址不匹配");
				return false;	
			}
		}
		
		//检查授权有效期
		if(license.getExpirationLimited().toUpperCase().equals("limited".toUpperCase())){
			DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date licDate=sdf.parse(license.getExpiration());
				@SuppressWarnings("static-access")
				Date nowDate=sdf.parse(serverInfo.getNowDate());
				if(licDate.before(nowDate)){
					logger.info("License授权已过期");
					return false;	
				}
			} catch (ParseException e) {
				logger.info("License授权已到期");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
