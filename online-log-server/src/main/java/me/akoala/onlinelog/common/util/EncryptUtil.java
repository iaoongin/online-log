//package me.akoala.onlinelog.common.util;
//
//import cn.hutool.core.lang.UUID;
//import cn.hutool.crypto.digest.MD5;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class EncryptUtil {
//    public static String encrypt(String raw) {
//        return new MD5().digestHex(raw);
//    }
//
//    public static String createJwt(UserDetails user) {
//        return JWT.create().withAudience(user.getUsername())
//                .sign(Algorithm.HMAC256(EncryptUtil.encrypt(user.getPassword())));
//    }
//
//    public static String uuid(){
//        return UUID.fastUUID().toString(true);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(encrypt("123"));
//        System.out.println(encrypt("123"));
//    }
//}
