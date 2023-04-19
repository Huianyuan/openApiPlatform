package com.yupi.project.service;

import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lhy
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2023-04-18 19:33:35
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * @description 接口调用统计
     *
     * @param interfaceInfoId 接口id
     * @param userId 用户id
     * @return: int
    */
    boolean invokeCount(long interfaceInfoId,long userId);
}
