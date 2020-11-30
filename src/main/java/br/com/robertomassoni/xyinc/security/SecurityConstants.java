package br.com.robertomassoni.xyinc.security;


public interface SecurityConstants {
    String SECRET = "SecretKeyToGenJWTs";
    String TOKEN_PREFIX = "Bearer ";
    long EXPIRATION_TIME = 864_000_000; // 10 days
}
