package com.example.BackendVolatile.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class EncryptionUtils {

    public String md5Encrypt(String srcStr) {
        return encrypt("MD5", srcStr);
    }

    public String sha1Encrypt(String srcStr) {
        return encrypt("SHA-1", srcStr);
    }

    public String sha256Encrypt(String srcStr) {
        return encrypt("SHA-256", srcStr);
    }

    public String sha384Encrypt(String srcStr) {
        return encrypt("SHA-384", srcStr);
    }

    public String sha512Encrypt(String srcStr) {
        return encrypt("SHA-512", srcStr);
    }

    public String encrypt(String algorithm, String srcStr) {
        try {
            StringBuilder result = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1)
                    result.append("0");
                result.append(hex);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        String str = "111111";
//        System.out.println(md5Encrypt(str));
//        System.out.println(sha1Encrypt(str));
//        System.out.println(sha256Encrypt(str));
//        System.out.println(sha384Encrypt(str));
//        System.out.println(sha512Encrypt(str));
//		打印结果：
//		96e79218965eb72c92a549dd5a330112
//		3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d
//		bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a
//		1b0268a40ae44c012946c974d60bf5291e7bb7c63cdb72a904d9283e3dc0a34de9afebe4035665768aaa503a4e7a30c3
//		b0412597dcea813655574dc54a5b74967cf85317f0332a2591be7953a016f8de56200eb37d5ba593b1e4aa27cea5ca27100f94dccd5b04bae5cadd4454dba67d
    }

}