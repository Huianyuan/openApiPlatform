package com.yupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.model.entity.UserInterfaceInfo;
import com.yupi.project.service.UserInterfaceInfoService;
import com.yupi.project.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

import java.security.UnresolvedPermission;


/**
 * @author lhy
 * @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
 * @createDate 2023-04-18 19:33:35
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        //创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId()<=0 || userInterfaceInfo.getUserId()<=0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum()<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        //校验
        if(interfaceInfoId<=0||userId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        updateWrapper.gt("leftNum",0);//大于0
        updateWrapper.setSql("leftNum=leftNum-1,totalNum=totalNum+1");
        return this.update(updateWrapper);
    }
}




