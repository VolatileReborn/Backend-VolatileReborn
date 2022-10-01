package com.example.BackendVolatile.util.jwtUtil;
import com.example.BackendVolatile.dto.userDTO.LoginDTO;
import com.example.BackendVolatile.vo.userVO.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * JwtToken生成的工具类
 *
 */
@Component
public class JwtTokenUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String secret = "P:7H.MDC_hMr.8-462--o2W*bCQMJ?7M8";
    private static final int expiration = 3600 * 24 * 100;

//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;


    /**
     * 根据负责生成JWT的token
     */
    public String generateToken(Map<String, Object> claims){
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

    }

//    public static void main(String[] args){
//        LoginVO loginVO = new LoginVO();
//        loginVO.setNickname("张三");
//        loginVO.setRole(2);
//        System.out.println(generateToken(loginVO,5));
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoi5byg5LiJIiwiaWF0IjoxNjQ3MDc0MDY0LCJleHAiOjE2NDcxNjA0OTcsImNyZWF0ZWQiOjE2NDcwNzQwOTc4NTN9.sEyflm3jE8hXU573gfFs_z1p14lsT01ZyOueekhvfVs";
//        boolean valid = validateToken(token);
//        System.out.println(valid);
//        if(valid){
//            System.out.println(getExpiredDateFromToken(token));
//            System.out.println(getUserNameFromToken(token));
//            String newToken = refreshToken(token);
//            System.out.println(newToken);
//            System.out.println(getExpiredDateFromToken(newToken));
//            System.out.println(getIdFromToken(newToken));
//        }
//        else{
//            System.out.println("token无效请重新登陆");
//        }
//
//    }

    public String generateToken(LoginVO loginVO, long userId){
        return Jwts.builder().setId("" + userId)
                .setSubject(loginVO.getNickname())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     * @param token
     * @return
     */

    public Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e){
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }


    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }
        catch (Exception e){
            username = null;
        }
        return username;
    }


    public long getIdFromToken(String token){
        long id;
        try{
            Claims claims = getClaimsFromToken(token);
            id = Long.parseLong(claims.getId());
        }
        catch (Exception e){
            id = -1;
        }
        return id;
    }

    /**
     * 验证token是否还有效
     *
     * @param token 客户端传入的token
     */

    public boolean validateToken(String token){
        Claims claims = getClaimsFromToken(token);
        if(claims == null){
            return false;
        }
        Date expiredDate =  claims.getExpiration();
        return !expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
         Claims claims = getClaimsFromToken(token);
         return claims.getExpiration();
    }


    public Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

//    /**
//     * 判断token是否可以被刷新
//     */
//    public boolean canRefresh(String token) {
//        return !isTokenExpired(token);
//    }


    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

//    /**
//     * 判断token是否已经失效
//     */
//    private boolean isTokenExpired(String token) {
//        Date expiredDate = getExpiredDateFromToken(token);
//        return expiredDate.before(new Date());
//    }


//    /**
//     * 根据用户信息生成token
//     */
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }

}
