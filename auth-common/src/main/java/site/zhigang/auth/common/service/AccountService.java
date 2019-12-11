package site.zhigang.auth.common.service;

import site.zhigang.auth.common.bean.AccountInfo;

/**
 * Create by Zg.Li · 2019/12/11
 */
public interface AccountService {
    AccountInfo getAccountInfo(String token);
    AccountInfo getAccountInfoById(int accountId);
}
