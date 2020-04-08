package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.service.AiProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AiProjectUserServiceImpl implements AiProjectUserService {

    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;

    @Override
    public void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList) {

        // 先删除group与customer关系
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        try {
            int i = aiProjectUserMapper.deleteByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 不作为
        if(customerIdList == null || customerIdList.size() == 0){
            return ;
        }

        ArrayList<AiProjectUser> data = new ArrayList<>();
        // 批量保存group与customer关系
        for (String customerId: customerIdList) {
            AiProjectUser aiProjectUser = new AiProjectUser();
            aiProjectUser.setGroupId(groupId);
            aiProjectUser.setCustomerId(customerId);
            aiProjectUser.setCompanyId(companyId);

            data.add(aiProjectUser);
        }

        try {
            aiProjectUserMapper.insertBatch(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
