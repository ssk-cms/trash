package com.trash.collection.trash.user.JWTUtils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
public class JwtProperties {

    @Value("${fq.jwt.secret}")
    private String secret;
    @Value("${fq.jwt.pubKeyPath}")
    private String pubKeyPath;
    @Value("${fq.jwt.priKeyPath}")
    private String priKeyPath;
    @Value("${fq.jwt.expire}")
    private int expire;
    @Value("${fq.jwt.cookieName}")
    private String cookieName;

    private PublicKey publicKey; // 公钥
    private PrivateKey privateKey; // 私钥
    //类一旦实例化后，就应该读取公钥和私钥
    @PostConstruct
    public void init() throws Exception {
        // 公钥私钥如果不存在，先生成
        File pubPath = new File(pubKeyPath);
        File priPath = new File(priKeyPath);
        if (!pubPath.exists() || !priPath.exists()) {
            // 生成公钥和私钥
            RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
        }
        // 读取公钥和私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}
