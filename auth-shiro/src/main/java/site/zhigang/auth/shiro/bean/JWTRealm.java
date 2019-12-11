package site.zhigang.auth.shiro.bean;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import site.zhigang.auth.common.bean.AccountInfo;
import site.zhigang.auth.common.service.AccountService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by Zg.Li · 2019/12/11
 */
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        int accountId = ((AccountInfo) principals.getPrimaryPrincipal()).getId();
        AccountInfo accountInfo = accountService.getAccountInfoById(accountId);
        Set<String> permissionSet = accountInfo.getPermissionInfos().stream()
                .flatMap(permissionInfo -> permissionInfo.getPermissions().stream())
                .collect(Collectors.toSet());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = ((JWTToken) authenticationToken).getToken();
        Integer accountId = accountService.getAccountInfo(token).getId();
        if (accountId == null) {
            throw new AuthenticationException("The token is invalid!");
        }
        AccountInfo accountInfo = accountService.getAccountInfoById(accountId);
        if (accountInfo == null) {
            throw new AuthenticationException("The user does not exist!");
        }

        return new SimpleAuthenticationInfo(accountInfo, token, getName());
    }
}
