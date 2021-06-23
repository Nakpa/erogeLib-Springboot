package com.library.eroge.erogelib.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.CommonEnumBaseError;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BizExceptionHandler;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.utils.deModel.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class TokenUtil {

//    @Value("${EXPIRE_TIME}")
    private final static long EXPIRE_TIME = 24 * 60;    // 失效时间

//    @Value("${ISSUER}")
    private final static String ISSUER = "EROGE-LIB";       // auth

//    @Value("${TOKEN_SECRET}")
    private final static String TOKEN_SECRET = "Nikaidoushinku";  //密钥

    /* *
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(UserInfoDTO user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME*60*1000);
            token = JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("userAcco", user.getUserAccount())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));

            log.info(" >>>>>>>>>>>>>>>> token生成成功: {}" , token);
            log.info(" >>>>>>>>>>>>>>>> 用户名: {}" , user.getUserAccount());
        } catch (Exception e){
            log.error("TokenUtil/signException" , e);
            throw new BizExceptionHandler(CommonEnumBaseError.BODY_NOT_MATCH.getResultCode() ,
                    CommonEnumBaseError.BODY_NOT_MATCH.getResultMsg());
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            log.info(">>>>>>>>>>>>> TOKEN认证通过：");
            log.info(">>>>>>>>>>>>> userAccount: " + jwt.getClaim("userAcco").asString());
            log.info(">>>>>>>>>>>>> TOKEN过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            log.error("TokenUtil/verify" , e);
            return false;
        }
    }

    public static TokenMap encrToken(String token) {
        TokenMap tokenMap = new TokenMap();
        String key = UUID.randomUUID().toString().replaceAll("-","");
        tokenMap.setToken(token);
        tokenMap.setKey(key);
        return tokenMap;
    }

}
