package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectGroupMapper;
import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.AiProjectGroupUserVo;
import cn.idea360.oracle.dto.Customer;
import cn.idea360.oracle.dto.FilterProjectUserDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class AiProjectUserServiceImpl implements AiProjectUserService {

    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;
    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;

    @Override
    public void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList){

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
    public AiProjectGroupUserRespVO filterAiProjectUser(FilterProjectUserDTO filterProjectUserDTO){

        // 获取全部客服
        List<Customer> customers = getCustomers();
        List<AiProjectUser> projectUserList = new ArrayList<>();
        List<AiProjectGroup> projectGroupList = new ArrayList<>();
        List<AiProjectUser> selfList = new ArrayList<>();

        // 获取已选客服
        try {
            List<Long> groupIds = null;
            if (filterProjectUserDTO.getGroupId() != null) {
                groupIds = Arrays.asList(filterProjectUserDTO.getGroupId());
            }
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
            projectUserList = aiProjectUserMapper.listByCustomerIds(cids);
            projectGroupList = aiProjectGroupMapper.listByCustomerIds(cids);

        } else {
            projectUserList = aiProjectUserMapper.listByGroupIds(null);
            projectGroupList = aiProjectGroupMapper.listByCustomerIds(null);
        }


        HashMap<Object, AiProjectGroup> projectGroupMap = new HashMap<>();
        HashMap<Object, AiProjectUser> projectUserMap = new HashMap<>();
        for (AiProjectUser aiProjectUser: projectUserList) {
            projectUserMap.put(aiProjectUser.getCustomerId(), aiProjectUser);
        }
        for (AiProjectGroup aiProjectGroup: projectGroupList) {
            projectGroupMap.put(aiProjectGroup.getId(), aiProjectGroup);
        }

        // 可选客服
        List<AiProjectGroupUserVo> data = new ArrayList();
        for (Customer customer: customers) {
            AiProjectGroupUserVo aiProjectGroupUserVo = new AiProjectGroupUserVo();
            aiProjectGroupUserVo.setUserId(customer.getId());
            aiProjectGroupUserVo.setName(customer.getName());
            aiProjectGroupUserVo.setDept(customer.getDept());
            aiProjectGroupUserVo.setGroupName(projectGroupMap.get(customer.getId())==null?null:projectGroupMap.get(customer.getId()).getGroupName());
            aiProjectGroupUserVo.setSelected(projectUserMap.get(customer.getId()) == null? false : true);
            data.add(aiProjectGroupUserVo);
        }

        // 已选客服
        for (AiProjectUser aiProjectUser: selfList) {
            AiProjectGroupUserVo aiProjectGroupUserVo = new AiProjectGroupUserVo();
        }

        // 数据封装
        AiProjectGroupUserRespVO aiProjectGroupUserRespVO = new AiProjectGroupUserRespVO();
//        aiProjectGroupUserRespVO.setCustomers(data);
//        aiProjectGroupUserRespVO.setSelfList(selfList);
        return aiProjectGroupUserRespVO;
    }


    // 原查询客服的接口
    private List<Customer> getCustomers() {
        Customer customer1 = new Customer("1", "admin", "dept1", 1);
        Customer customer2 = new Customer("2", "admin1", "dept1", 1);
        Customer customer3 = new Customer("3", "admin2", "dept2", 2);
        Customer customer30 = new Customer("30", "admin3", "dept3", 3);
        ArrayList<Customer> data = new ArrayList<>();
        data.add(customer1);
        data.add(customer2);
        data.add(customer3);
        data.add(customer30);
        return data;
    }
}
