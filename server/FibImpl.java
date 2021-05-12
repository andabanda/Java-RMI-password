
import java.math.BigInteger;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class FibImpl extends UnicastRemoteObject implements Fib {
   
   //connecting the Implementation
   public FibImpl() throws RemoteException {
      super();
   }

   
   //Password Function
   public String weak(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString(); 
   }
   
   public String medium(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString(); 
   }
   public String strong(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" +  "@#&!()^=+/:.;,$%*_-<>?{}[]";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString();
   }
   
   //encryption function
public String MD5weak(String n) {
	   
	   String password = null;
	   try {
		   MessageDigest msg = MessageDigest.getInstance("MD5");
			msg.update(n.getBytes());
			byte[] bytes = msg.digest();
			StringBuilder build = new StringBuilder();
			for(int i=0;i<bytes.length; i++) {
				build.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = build.toString();
	   }
	   catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	   return password;
	   
   }
   
   //same fucntion as before, but adding salt to make more secure
   public String MD5encrypt(String n, byte[] salt) {
	   
	   String password = null;
	   try {
		   MessageDigest msg = MessageDigest.getInstance("MD5");
			//msg.update(n.getBytes());
			//byte[] bytes = msg.digest();
		   msg.update(salt);
		   byte[] bytes = msg.digest(n.getBytes());
		   
			StringBuilder build = new StringBuilder();
			for(int i=0;i<bytes.length; i++) {
				build.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = build.toString();
	   }
	   catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	   return password;
	   
   }
   
   public String SHA512(String n, byte[] salt) {
	   String password = null;
	   try {
	   MessageDigest msg = MessageDigest.getInstance("SHA-512");
		//msg.update(n.getBytes());
		//byte[] bytes = msg.digest();
	   msg.update(salt);
	   byte[] bytes = msg.digest(n.getBytes());
	   
		StringBuilder build = new StringBuilder();
		for(int i=0;i<bytes.length; i++) {
			build.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		password = build.toString();
  }
  catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
  return password;
	   
   }
   
public String SHA512weak(String n) {
	   
	   String password = null;
	   try {
		   MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(n.getBytes());
			byte[] bytes = msg.digest();
			StringBuilder build = new StringBuilder();
			for(int i=0;i<bytes.length; i++) {
				build.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = build.toString();
	   }
	   catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	   return password;
	   
   }

public String secure(String n, int q) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException{
	int times = q;
	char[] chars = n.toCharArray();
	byte[] salt = Salt();
	int length = 512;
	
	PBEKeySpec spec = new PBEKeySpec(chars, salt, times, length);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = skf.generateSecret(spec).getEncoded();
    return "The number of iterations are: " + times + " The salted Hash is: " + toHex(salt) + toHex(hash);
	
}


public String toHex(byte[] array) throws NoSuchAlgorithmException
{
    BigInteger big = new BigInteger(1, array);
    String hex = big.toString(16);
    int paddingLength = (array.length * 2) - hex.length();
    if(paddingLength > 0)
    {
        return String.format("%0"  +paddingLength + "d", 0) + hex;
    }else{
        return hex;
    }
}

   //Salt is used to generate random data to further increase encryption security(Standard practices)
   public byte[] Salt() throws NoSuchAlgorithmException, NoSuchProviderException{
	   //Secure random is the standard method to create secure salts in Java
	   SecureRandom sec = SecureRandom.getInstance("SHA1PRNG", "SUN");
	   byte[] salt = new byte[16];
	   sec.nextBytes(salt);
	   return salt;
   }
   
   //password strength functions
   
   
   
   public boolean Strong(String password) {
	   String regex = "^(?=.*[0-9])"
               + "(?=.*[a-z])(?=.*[A-Z])"
               + "(?=.*[!@#$%^&+=])"
               + "(?=\\S+$).{8,20}$";
	   
	   Pattern p = Pattern.compile(regex);
	   if (password == null) { 
           return false; 
       } 
	   Matcher m = p.matcher(password); 
	   return m.matches();
	   
	   
   }
   public boolean Med(String password) {
	   String regex = "^(?=.*[0-9])"
               + "(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
	   
	   Pattern p = Pattern.compile(regex);
	   if (password == null) { 
           return false; 
       } 
	   Matcher m = p.matcher(password); 
	   return m.matches();
	   
	   
   }
   
   public boolean weakest(String password) {
	   String regex =  "^(?=.*[a-z])(?=.*[A-Z]).{2,20}$";
	   
	   Pattern p = Pattern.compile(regex);
	   if (password == null) { 
           return false; 
       } 
	   Matcher m = p.matcher(password); 
	   return m.matches();
	   
	   
   }
	   
   }
   
   
  
   
   
   
   
   
   
   
