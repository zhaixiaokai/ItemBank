package net.ib.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import net.ib.common.Base64;
import license.License;

public class LicenseUtils {
	private static Logger logger = Logger.getLogger(LicenseUtils.class);
	
	public static PublicKey readPublicKeyFromJar(String fileURLName){
		XStream xstream = new XStream(new DomDriver("utf-8"));
		InputStream url=LicenseUtils.class.getResourceAsStream(fileURLName);
		PublicKey publicKey=(PublicKey)xstream.fromXML(url);
		return  publicKey;
	}
	
	@SuppressWarnings("unused")
	public static Object parseLicenseFromFile(String fileURLName){
		XStream xstream = new XStream(new DomDriver("utf-8"));
		try{ 
			@SuppressWarnings("resource")
			InputStream url=new FileInputStream(new File(fileURLName));
			if(null!=url) return xstream.fromXML(url);
			else{
				logger.error("δ�ҵ���Ч��license�ļ���ϵͳ��ֹ������");
				System.exit(0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getLicenseContent(License license){
		String licensePlainText="";
		if(null!=license){
			licensePlainText+=license.getCustomerNameLimited();
			licensePlainText+=license.getCustomerName();
			licensePlainText+=license.getProductNameLimited();
			licensePlainText+=license.getProductName();
			licensePlainText+=license.getProductVersionLimited();
			licensePlainText+=license.getProductVersion();
			licensePlainText+=license.getMacAddressLimited();
			licensePlainText+=license.getMacAddress();
			licensePlainText+=license.getIpAddressLimited();
			licensePlainText+=license.getIpAddress();
			licensePlainText+=license.getUserCountLimited();
			licensePlainText+=license.getUserCount();
			licensePlainText+=license.getExpirationLimited();
			licensePlainText+=license.getExpiration();
			licensePlainText+=license.getOrganization();
			licensePlainText+=license.getCreateDate();
		}
		return licensePlainText;
	}
	
	public static boolean isValidLicense(License license,PublicKey publicKey){
		boolean result=false;
		try {
			if(LicenseUtils.verify(publicKey,new String(LicenseUtils.getLicenseContent(license).getBytes("utf-8"),"utf-8"),new String(license.getSignatuer().getBytes("utf-8"),"utf-8"))){
				logger.debug("��ǰ��������ʹ�õ�License��Ч��");
				result=true;
			}else{
				logger.debug("��ǰ��������ʹ�õ�License��Ч��");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 
	
	/**
	 * 
	 * Description:У������ǩ��,�˷��������׳������쳣,�ɹ�����true,ʧ�ܷ���false,Ҫ��ȫ����������Ϊ��
	 * 
	 * @param pubKeyText
	 *            ��Կ,base64����
	 * @param plainText
	 *            ����
	 * @param signTest
	 *            ����ǩ��������,base64����
	 * @return У��ɹ�����true ʧ�ܷ���false
	 */
	public static boolean verify(PublicKey publicKey, String plainText,String signText) {
		try {
			// ������base64����Ĺ�Կ,������X509EncodedKeySpec����
			byte[] pubKeyBytes=Base64.encodeToString(publicKey.getEncoded()).getBytes();
			java.security.spec.X509EncodedKeySpec bobPubKeySpec = new java.security.spec.X509EncodedKeySpec(Base64.decode(pubKeyBytes));
			// RSA�ԳƼ����㷨
			java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
			// ȡ��Կ�׶���
			java.security.PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			
			// ������base64���������ǩ��
			byte[] signedBytes = Base64.decode(signText.getBytes());
			java.security.Signature signatureChecker = java.security.Signature.getInstance("MD5withRSA");
			signatureChecker.initVerify(pubKey);
			signatureChecker.update(plainText.getBytes("utf-8"));
			// ��֤ǩ���Ƿ�����
			if (signatureChecker.verify(signedBytes))
				return true;
			else
				return false;
		} catch (Throwable e) {
			logger.debug("У��ǩ��ʧ��");
			//e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String args[]){
		String fileURL="/net/ogi/license/public.key";
		PublicKey publicKey=LicenseUtils.readPublicKeyFromJar(fileURL);
		System.out.println("public key is "+Base64.encodeToString(publicKey.getEncoded()));
		
		License license=(License)LicenseUtils.parseLicenseFromFile("/license.xml");
		System.out.println("License Content is "+LicenseUtils.getLicenseContent(license));

		System.out.println("--------------------");
		System.out.println("License Check is "+LicenseUtils.isValidLicense(license, publicKey));
	}
}
