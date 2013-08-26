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
		
		//���ͻ���λ����
		if(license.getCustomerNameLimited().toUpperCase().equals("limited".toUpperCase()) &&
			!license.getCustomerName().toUpperCase().equals(serverInfo.getCustomerName().toUpperCase())){
			logger.info("License��Ȩ�ͻ���λ������ͻ���λ���Ʋ�ƥ��");
			return false;	
		}
		
		//�����Ȩ��Ʒ����
		if(license.getProductNameLimited().toUpperCase().equals("limited".toUpperCase()) &&
			!license.getProductName().toUpperCase().equals(serverInfo.getProductName().toUpperCase())){
			logger.info("License��Ȩ��Ʒ�����뵱ǰ��Ʒ���Ʋ�ƥ��");
			return false;	
		}		

		//�����Ȩ��Ʒ�汾
		if(license.getProductVersionLimited().toUpperCase().equals("limited".toUpperCase())){
			String licVersion=license.getProductVersion().toUpperCase();
			String curVersion=serverInfo.getProductVersion();
			int xPos=licVersion.indexOf(".X");
			if(xPos>=0 && curVersion.length()>=xPos){
				if(!(curVersion.toUpperCase().substring(0,xPos).equals(licVersion.toUpperCase().substring(0,xPos))
						|| curVersion.toUpperCase().equals(licVersion.toUpperCase()))){
					logger.info("License��Ȩ��Ʒ�汾�뵱ǰ��Ʒ�汾��ƥ��");
					return false;	
				}
			}
			if(xPos>=0 && curVersion.length()<xPos){
				logger.info("License��Ȩ��Ʒ�汾�뵱ǰ��Ʒ�汾��ƥ��");
				return false;
			}
			if(xPos<0 && !curVersion.toUpperCase().equals(licVersion.toUpperCase())) return false;
		}

		//�����Ȩ�ն��û���
		if(license.getUserCountLimited().toUpperCase().equals("limited".toUpperCase()) &&
			(Integer.parseInt(license.getUserCount())<serverInfo.getUserCount())){
			logger.info("License��Ȩ�û�����ʵ���û�����ƥ��");
			return false;	
		}
		
		//�����Ȩ������ַ
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
				logger.info("License��Ȩ������ַ��ʵ��������ַ��ƥ��");
				return false;	
			}
		}
		
		//�����Ȩ��IP��ַ
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
				logger.info("License��ȨIP��ַ��ʵ��IP��ַ��ƥ��");
				return false;	
			}
		}
		
		//�����Ȩ��Ч��
		if(license.getExpirationLimited().toUpperCase().equals("limited".toUpperCase())){
			DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date licDate=sdf.parse(license.getExpiration());
				@SuppressWarnings("static-access")
				Date nowDate=sdf.parse(serverInfo.getNowDate());
				if(licDate.before(nowDate)){
					logger.info("License��Ȩ�ѹ���");
					return false;	
				}
			} catch (ParseException e) {
				logger.info("License��Ȩ�ѵ���");
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
