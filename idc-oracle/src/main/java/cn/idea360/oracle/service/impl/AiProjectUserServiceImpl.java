package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.service.AiProjectUserService;

import java.util.List;

public class AiProjectUserServiceImpl implements AiProjectUserService {
    @Override
    public void saveOrUpdate(Long groupId, List<Long> customerIdList) {

        // 先删除group与customer关系

        // 不作为
        if(customerIdList == null || customerIdList.size() == 0){
            return ;
        }


        // 批量保存group与customer关系
    }
}
