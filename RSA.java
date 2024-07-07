
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA_FILE {
	
	public static void main(String[] args) {
		
			System.out.println("\t----- Generating the public and private key-------");
			KeyPairGenerator kpg = null;
			try {
				kpg = KeyPairGenerator.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				
				System.out.println(e.toString());
			}//catch
			
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
		
			PublicKey publickey = kp.getPublic(); 
			PrivateKey privatekey = kp.getPrivate();
						
			
			try {
				FileOutputStream fos = new FileOutputStream("public.key");
				fos.write(publickey.getEncoded());
			} catch (FileNotFoundException e) {
				
				System.out.println(e.toString());
			}//catch file io
				catch (IOException e) {
				
				System.out.println(e.toString());
			}
			
		
			
			System.out.println("---------storing keys in the file---------");
			
			
			String publickeyfile = "C:/Users/aitav/OneDrive/Desktop/keys.txt/";
			
			String privatekeyfile = "C:/Users/aitav/OneDrive/Desktop/keys1.txt/";
			
			FileOutputStream keyout = null;
			FileOutputStream key1out =null;
			try {
			 keyout= new FileOutputStream(publickeyfile);
			 key1out = new FileOutputStream(privatekeyfile);
			} catch (FileNotFoundException e1) {
				
				System.out.println(e1.toString());
				
			}
			
			try {
				keyout.write(publickey.getEncoded());
				
				key1out.write(privatekey.getEncoded());
			} catch (IOException e1) {
				
				System.out.println(e1.toString());
			}
			
			System.out.println("----------secret message is reading----------");
			
			
			String secretmessage = "C:/Users/aitav/OneDrive/Desktop/New Text Document.txt/";
			FileInputStream in = null;
			try {
				in = new FileInputStream(secretmessage);
			} catch (FileNotFoundException e) {
				
				System.out.println(e.toString());
			}
			
			System.out.println();
			System.out.println("------reading all the bytes to the filebyte array---------");
			byte[] fileBytes = null;
			try {
				fileBytes = in.readAllBytes();
			} catch (IOException e) {
				
				System.out.println(e.toString());
			}
			
			Cipher encryptCipher = null;
			try {
				encryptCipher = Cipher.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				
				System.out.println(e.toString());
			} catch (NoSuchPaddingException e) {
				
				System.out.println(e.toString());
			}
			try {
				encryptCipher.init(Cipher.ENCRYPT_MODE, publickey);
			} catch (InvalidKeyException e) {
				
				System.out.println(e.toString());
			}
			
			
			byte[] encryptedFileBytes = null;
			try {
				encryptedFileBytes = encryptCipher.doFinal(fileBytes);
			} catch (IllegalBlockSizeException e) {
			
				System.out.println(e.toString());
			} catch (BadPaddingException e) {
				
				System.out.println(e.toString());
			}
			
			String encryptmessage = "C:/Users/aitav/OneDrive/Desktop/Encrypted msg.txt/";
			try (FileOutputStream out = new FileOutputStream(encryptmessage)){
				
				out.write(encryptedFileBytes);
				
			} catch (FileNotFoundException e) {
				
				System.out.println(e.toString());
			} catch (IOException e) {
			
				System.out.println(e.toString());
			}
			System.out.println("-----------encrypt message is saved--------");
			System.out.println();
			System.out.println();
			System.out.println("------decryption is staring-------");
			
			String decryptmessage = "C:/Users/aitav/OneDrive/Desktop/Decrypted msg.txt/";
			
			
			FileOutputStream out = null;
			try {
				 out = new FileOutputStream(decryptmessage);
			} catch (FileNotFoundException e) {
				
				System.out.println(e.toString());
				
			}
			
			Cipher decryptCipher = null;
			try {
				decryptCipher = Cipher.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				
				System.out.println(e.toString());
			} catch (NoSuchPaddingException e) {
				
				System.out.println(e.toString());
			}
			try {
				decryptCipher.init(Cipher.DECRYPT_MODE, privatekey);
			} catch (InvalidKeyException e) {
				
				System.out.println(e.toString());
			}
			try {
				System.out.println();
				
				System.out.println("------writing the decrypted message to the file-------\n");
				byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes);
				try {
					out.write(decryptedFileBytes);
				} catch (IOException e) {
					
					System.out.println(e.toString());
				}
			} catch (IllegalBlockSizeException e) {
			
				System.out.println(e.toString());
				
			} catch (BadPaddingException e) {
				
				System.out.println(e.toString());
				
			}
		
			System.out.println("\t decryption is done!!!!");
			
			
		}
	}
	
	
	
