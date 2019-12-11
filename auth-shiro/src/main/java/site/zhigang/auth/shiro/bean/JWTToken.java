package site.zhigang.auth.shiro.bean;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Create by Zg.Li · 2019/12/11
 */
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 1250166508152483573L;

    @Getter
    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
