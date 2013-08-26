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
				logger.error("未找到有效的license文件，系统终止启动。");
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
				logger.debug("当前服务器所使用的License有效！");
				result=true;
			}else{
				logger.debug("当前服务器所使用的License无效！");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 
	
	/**
	 * 
	 * Description:校验数字签名,此方法不会抛出任务异常,成功返回true,失败返回false,要求全部参数不能为空
	 * 
	 * @param pubKeyText
	 *            公钥,base64编码
	 * @param plainText
	 *            明文
	 * @param signTest
	 *            数字签名的密文,base64编码
	 * @return 校验成功返回true 失败返回false
	 */
	public static boolean verify(PublicKey publicKey, String plainText,String signText) {
		try {
			// 解密由base64编码的公钥,并构造X509EncodedKeySpec对象
			byte[] pubKeyBytes=Base64.encodeToString(publicKey.getEncoded()).getBytes();
			java.security.spec.X509EncodedKeySpec bobPubKeySpec = new java.security.spec.X509EncodedKeySpec(Base64.decode(pubKeyBytes));
			// RSA对称加密算法
			java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
			// 取公钥匙对象
			java.security.PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			
			// 解密由base64编码的数字签名
			byte[] signedBytes = Base64.decode(signText.getBytes());
			java.security.Signature signatureChecker = java.security.Signature.getInstance("MD5withRSA");
			signatureChecker.initVerify(pubKey);
			signatureChecker.update(plainText.getBytes("utf-8"));
			// 验证签名是否正常
			if (signatureChecker.verify(signedBytes))
				return true;
			else
				return false;
		} catch (Throwable e) {
			logger.debug("校验签名失败");
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
