package danandla.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Properties;

@Stateless
public class PasswordKitchen {
    private final String pepper;

    public PasswordKitchen(){
       pepper = getPepper();
    }

    public byte[] doHash(String password, byte[] salt) {
        if(pepper.equals("")) return null;
        byte[] hash = null;
        try{
            password += pepper;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e){
            System.out.println("can't do hash");
        }
        return hash;
    }

    public byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private String getPepper(){
        Properties prop = new Properties();
        String foundPepper = "";
        String fileName = "/home/danandla/BOTAY/web-programming/labs/web-4/web-lab-4/back/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            foundPepper = prop.getProperty("app.pepper");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find config file");
        } catch (IOException ex) {
            System.out.println("Unknown error while finding pepper");
        }
        return foundPepper;
    }
}
