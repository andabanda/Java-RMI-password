import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.*;
import java.net.URI;
import java.rmi.Naming;
import java.util.Scanner;



public class FibClient {
   public static void main(String[] args) {
	   int a, b, c, q;
	   String userInput = null;
	     String userPass;
	     String fn = null;
	      

	   //Receive Input from Client for feature selection
	   Scanner scan = new Scanner(System.in);
	   System.out.println("Select a function: (1) Password Generator, (2) Password Encrypter, (3) Password Strength Checker");
	   c = scan.nextInt(); 
	   
	   //The Password Generator uses a switch case for cleaner code
	   if (c == 1) {
 		 System.out.println("You have selected the Password Generator function");
 	      System.out.println("Enter password type(1(weak), 2(medium), or 3(strong)): ");
 	      b = scan.nextInt();
 	     System.out.println("Enter preferred password length");
 	     a = scan.nextInt();
 	    try {
 	        Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
 	        
 	        switch(b) {
 	        case 1:
 	        	fn = f.weak(a);
 	        	break;
 	        case 2:
 	        	fn = f.medium(a);
 	        	break;
 	        case 3: 
 	        	fn = f.strong(a);
 	        	break;
 	        }
 	        
 	        System.out.println("Your password is: "+ fn);
 	        //clipboard copying code
 	       StringSelection stringSelection = new StringSelection(fn);
  	       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
  	       clipboard.setContents(stringSelection, null);
  	       //file writing code
  	       System.out.println("Your password has been copied to clipboard for immediate use.");
 	       FileOutputStream fos = new FileOutputStream("list.txt");
 	       DataOutputStream out = new DataOutputStream(new BufferedOutputStream(fos));
 	      	out.writeChars(fn);
	   	    System.out.println("Your password has been listed into a file.");
     	 	out.close();
     	 	
 	      } catch(Exception e) {
 	        System.err.println("Remote exception: "+e.getMessage());
 	        e.printStackTrace(); 
 	      }
 	    scan.close();
 	    	//Second function
 	      }else if (c == 2) {
 	    	  
 	    	 try {
 	    	System.out.println("For MD5 Encryption, press (1) || For SHA-512, press (2) || For PBKDF2 Encryption, (standard encryption method) press (3) ");
 	    	Scanner scanner = new Scanner (System.in);
 	    	int g = scanner.nextInt();
 	    	
 	    	//Different Encryption types incorporate switch cases
 	    	switch (g) {
 	    	//each case has with/without salt added to the encryption to demonstrate security
 	    	case 1:
 	    		System.out.println("Would you like MD5 Encryption with Salt? (Strongly Recommended) (y/n)");
 	    		char z = scanner.next().charAt(0);
 	    		System.out.println("Please insert password to be encrypted:");
 	 	    	 while((userInput = scan.nextLine()) != null) { 
 	 	    		userPass = scan.nextLine();
 	 	    		Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
 	 	    		byte[] salt = f.Salt();
 	 	    		if (z == 'y') {
 	 	   	        fn = f.MD5encrypt(userPass, salt);
 	 	   	        	System.out.println("Your MD5 encrypted password (with added salt) is: "+fn);
 	 	    		} else {
 	 	    			fn = f.MD5weak(userPass);
 	 	    			System.out.println("Your MD5 encrypted password (without salt) is: "+fn);
 	 	    		} 
 	 	   	       break;
 	    	}
 	 	    	 break;
 	    	case 2:
 	    		System.out.println("Would you like SHA-512 Encryption with Salt? (Strongly Recommended) (y/n)");
 	    		char p = scanner.next().charAt(0);
 	    		System.out.println("Please insert password to be encrypted:");
 	 	    	 while((userInput = scan.nextLine()) != null) { 
 	 	    		userPass = scan.nextLine();
 	 	    		Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
 	 	    		byte[] salt = f.Salt();
 	 	    		if (p == 'y') {
 	 	   	        fn = f.SHA512(userPass, salt);
 	 	   	        	System.out.println("Your SHA-512 encrypted password (with added salt) is: "+fn);
 	 	    		} else {
 	 	    			fn = f.SHA512weak(userPass);
 	 	    			System.out.println("Your SHA-512 encrypted password (without salt) is: "+fn);
 	 	    		} 
 	 	   	       break;}
 	 	    	 break;
 	 	    	 //This third case is the most secure and must have a salt included
 	    	case 3:
 	    		System.out.println("Please insert password to be encrypted:");
	 	    	 while((userInput = scan.nextLine()) != null) { 
	 	    		userPass = scan.nextLine();
	 	    		
	 	    		Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
	 	    		System.out.println("Enter the number of iterations you would like the encryption to have. (must need strong hardware for greater integers)");
	 	    		q = scan.nextInt();
	 	   	        fn = f.secure(userPass, q);
	 	   	        	System.out.println(fn);
	 	   	       break;
	    	}
	 	    	 break;
 	    		
 	    	}
 	    	
 	    	//Code to test password by copying to clipbopard and taking them to external site
 	    	System.out.println("Would you like to test your password?(y/n)");
	   	       Scanner sc = new Scanner(System.in);
	   	       char fip = sc.next().charAt(0);
	   	       if (fip == 'y' ) {
	   	    	   
	   	    	   
	   	    	StringSelection stringSelection = new StringSelection(fn);
	   	       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	   	       clipboard.setContents(stringSelection, null);
	   	       System.out.println("Your password has been copied to clipboard.");
	   	       System.out.println("You are about to head to an external site to test your password. Is this okay? (y/n)");
	   	       char fop = sc.next().charAt(0);
	   	       if (fop == 'y') {
	   	    	Desktop desktop = java.awt.Desktop.getDesktop();
	   	       URI website = new URI("https://crackstation.net/");
	   	       desktop.browse(website);}
	   	       else {
	   	    	sc.close();
	   	     
	   	       } 
	   	       }else {
	   	   sc.close();}
 	    	scanner.close();
	    	 }

 	   	       catch(Exception e) {
 	   	        System.err.println("Remote exception: "+e.getMessage());
 	   	        e.printStackTrace();
 	   	      }
 	      
 	      scan.close();  
   } else if (c == 3) {
	   System.out.println("Enter a password to check your strength:");
	   try {
	   while((userInput = scan.nextLine()) != null) { 
	    		userPass = scan.nextLine();
	    		Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
	    		if (f.Strong(userPass) == true) {
	    		System.out.println("Your password is strong.");
	    		
	    		}
	    		else if(f.Med(userPass) == true) {
	    			System.out.println("Your password is moderate.");
	    		}
	    		else if(f.weakest(userPass) == true) {
	    			System.out.println("Your password is terrible, please change it.");
	    		}else {
	    			System.out.println("Your password is terrible, please change it."); 
	    		}
	    		break;
	   }
	   
	   
	   
   } catch(Exception e) {
	        System.err.println("Remote exception: "+e.getMessage());
	        e.printStackTrace();
	      }
   }
	   
 	     
 	      }

}
   

