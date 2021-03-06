package com.pocketcloud.auth.common.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author Zg.Li · 2019/12/11
 */
@Data
public class AccountInfo {
    // 用户ID
    private Integer id;
    /**
     * 所有模块的权限集
     */
    private List<PermissionInfo> permissionInfos;
}
