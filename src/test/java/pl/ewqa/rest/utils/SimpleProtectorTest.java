package pl.ewqa.rest.utils;

import org.junit.Assert;
import org.junit.Test;

public class SimpleProtectorTest {
    @Test
    public void encryptedMethodTest() throws Exception {
        String password = "Some_password";
        String encryptedPassword = SimpleProtector.encrypt(password);
        String decryptedPassword = SimpleProtector.decrypt(encryptedPassword);

        System.out.println("My password: " + password);
        System.out.println("Encrypted password: " + encryptedPassword);
        System.out.println("Decrypted password: "+ decryptedPassword);
        Assert.assertEquals(password, decryptedPassword);
    }

}