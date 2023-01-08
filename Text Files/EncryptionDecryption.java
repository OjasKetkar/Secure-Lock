package textFiles;
import Main.App;
import Main.App.*;
import java.io.*;
import java.util.Scanner;
/*import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;*/
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.*;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

public class EncryptionDecryption
{
    public static void encryptDecrypt(String key, int cipherMode, File in, File out)
    throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
    IOException
    {
        FileInputStream fis=new FileInputStream(in);
        FileOutputStream fos=new FileOutputStream(out);
        
        DESKeySpec desKeySpec=new DESKeySpec(key.getBytes()); //geenratees the object using the first 8bytes of the key for the DES key
        
        SecretKeyFactory skf= SecretKeyFactory.getInstance("DES");
        //to convert opaque cryp. keys into transperant representations
        SecretKey secretKey=skf.generateSecret(desKeySpec);
         
        Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding"); //to rturn the specifieed signature algorithm 
        
        if(cipherMode==Cipher.ENCRYPT_MODE) //to cipher to encryption mode
        {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG")); //pseudo random number generator algorithm
            CipherInputStream cis=new CipherInputStream(fis,cipher);
            write(cis,fos);
        }
        else if(cipherMode==Cipher.DECRYPT_MODE)
        {
           cipher.init(Cipher.DECRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG")); 
           CipherOutputStream cos=new CipherOutputStream(fos, cipher);
           write(fis, cos);
        }
         
    }
    private static void write(InputStream in, OutputStream out)throws IOException
    {
        byte[] buffer=new byte[64];
        int numOfBytesRead;
        while((numOfBytesRead=in.read(buffer))!=-1)
        {
        out.write(buffer,0,numOfBytesRead);
    }
    out.close();
    in.close();
}	
  public static void main(String[] args) {
    // System.out.println(App.initialize);+
	  Scanner sc=new Scanner(System.in);
	  System.out.println("For encryption enter choice as 1: ");
		 System.out.println("For decryption enter choice as 2: ");
		int choice=sc.nextInt();
        File plaintext = new File("{path of the file to be encrypted}");  //file path which has plain text
	File encrypted = new File("{path of the encrypted file}\\name of the encrypted file"); //blank file path which will contain encrypted text after encryption
	if(choice==1)
	{
    try {
        encryptDecrypt("12345678", Cipher.ENCRYPT_MODE, plaintext, encrypted);
        System.out.println("Encryption complete");
	
    } catch(Exception e) {
        e.printStackTrace(); // used to hndle exceptions and errors
    }
	}

if(choice==2)
{
File encrypted2=new File("{path to the encrypted file}"); //encrypted file
File decrypted=new File("{path to the decrpyted file\\name of the decrypted file to be created}"); //empty text file which will contain decrypted text after applying decryption

try
{
	encryptDecrypt("12345678",Cipher.DECRYPT_MODE,encrypted2,decrypted);
	System.out.println("Decryption Complete:");
}
catch(InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IOException e)
{
	e.printStackTrace();
}

}
  }
}
