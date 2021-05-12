
import java.rmi.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public interface Fib extends Remote {
   public final static String SERVICENAME="FibService";
   
   public String weak(int n) throws RemoteException;
   public String medium(int n) throws RemoteException;
   public String strong(int n) throws RemoteException;
   
   public String MD5weak(String n) throws RemoteException;
   public String MD5encrypt(String n, byte[] salt) throws RemoteException;
   
   public String SHA512weak(String n) throws RemoteException;
   public String SHA512(String n, byte[] salt) throws RemoteException;
   
   public byte[] Salt() throws NoSuchAlgorithmException, NoSuchProviderException, RemoteException;
   
   public String secure(String n, int q) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, RemoteException;
   public String toHex(byte[] array) throws NoSuchAlgorithmException, RemoteException;
   
   
   public boolean Strong(String password) throws RemoteException;
   public boolean Med(String password) throws RemoteException;
   public boolean weakest(String password) throws RemoteException;
}
