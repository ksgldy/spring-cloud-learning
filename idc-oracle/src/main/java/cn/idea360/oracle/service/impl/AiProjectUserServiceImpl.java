package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.Customer;
import cn.idea360.oracle.dto.FilterProjectUserDTO;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.service.AiProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AiProjectUserServiceImpl implements AiProjectUserService {

    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;

    @Override
    public void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList) throws Exception{

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

        aiProjectUserMapper.insertBatch(data);
    }

    @Override
    public List<AiProjectUser> filterAiProjectUser(FilterProjectUserDTO filterProjectUserDTO) throws Exception{

        // 获取全部客服
        List<Customer> customers = getCustomers();
        List<AiProjectUser> groupList = new ArrayList<>();
        List<AiProjectUser> selfList = null;

        // 获取自己组分组客服
        try {
            ArrayList<Long> groupIds = new ArrayList<>();
            groupIds.add(filterProjectUserDTO.getGroupId());
            selfList = aiProjectUserMapper.listByGroupIds(groupIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 所有分组客服
        if (null != filterProjectUserDTO && (filterProjectUserDTO.getDepartmentId() != null || (filterProjectUserDTO.getSearchField() != null && !StringUtils.isEmpty(filterProjectUserDTO.getKeyword())))) {
            List<String> cids = new ArrayList<>();
            for (Customer c:customers) {
                cids.add(c.getId());
            }
            groupList = aiProjectUserMapper.listByCustomerIds(cids);

        } else {
            groupList = aiProjectUserMapper.listByGroupIds(null);
        }
        // todo 后台拼装好返回前端
        return null;
    }


    // 原查询客服的接口
    private List<Customer> getCustomers() {
        return null;
    }
}
