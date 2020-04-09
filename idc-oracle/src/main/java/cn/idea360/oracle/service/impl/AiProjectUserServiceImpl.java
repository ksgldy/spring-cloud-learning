package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectGroupMapper;
import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.model.JsDepartment;
import cn.idea360.oracle.model.JsUser;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.service.JsDepartmentService;
import cn.idea360.oracle.service.JsUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AiProjectUserServiceImpl implements AiProjectUserService {

    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;
    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;
    @Autowired
    private JsUserService jsUserService;
    @Autowired
    private JsDepartmentService jsDepartmentService;

    @Override
    public void saveOrUpdate(Integer companyId, Long groupId, Collection<String> customerIdList){

        // 先删除group与customer关系
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        try {
            int i = aiProjectUserMapper.deleteByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public AiProjectGroupUserRespVO filterAiProjectUser(AiProjectUserQueryDTO aiProjectUserQueryDTO) {

        HashMap<String, Object> params = new HashMap<>();

        if (aiProjectUserQueryDTO != null) {
            Integer departmentId = aiProjectUserQueryDTO.getDepartmentId();
            String searchField = aiProjectUserQueryDTO.getSearchField();
            String keyword = aiProjectUserQueryDTO.getKeyword();

            if (departmentId != null && departmentId >=0) {
                params.put("departmentId", departmentId);
            }
            if (!StringUtils.isEmpty(searchField)) {
                if ("userId".equals(searchField)) {
                    params.put("userId", searchField);
                } else if ("realName".equals(searchField)){
                    params.put("realName", searchField);
                }
            }
            if (!StringUtils.isEmpty(keyword)) {
                params.put("keyword", keyword);
            }
        }
        // 获取全部客服
        List<JsUser> jsUsers = jsUserService.listByMap(params);

        // 获取部门id集合
        Set<Integer> departmentIdSet = new HashSet<>();
        for (JsUser jsUser: jsUsers) {
            Integer departmentId = jsUser.getDepartmentId();
            departmentIdSet.add(departmentId);
        }

        // 获取相关部门
        List<JsDepartment> jsDepartments = jsDepartmentService.listByDepartmentIds(departmentIdSet);


        return null;
    }

//    @Override
//    public AiProjectGroupUserRespVO filterAiProjectUser(AiProjectUserQueryDTO aiProjectUserQueryDTO){
//
//        // 获取全部客服
//        List<Customer> customers = getCustomers();
//        List<AiProjectUser> projectUserList = new ArrayList<>();
//        List<AiProjectGroup> projectGroupList = new ArrayList<>();
//        List<AiProjectUser> selfList = new ArrayList<>();
//
//        // 获取已选客服
//        try {
//            List<Long> groupIds = null;
//            if (aiProjectUserQueryDTO.getGroupId() != null) {
//                groupIds = Arrays.asList(aiProjectUserQueryDTO.getGroupId());
//            }
//            selfList = aiProjectUserMapper.listByGroupIds(groupIds);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 所有分组客服
//        if (null != aiProjectUserQueryDTO && (aiProjectUserQueryDTO.getDepartmentId() != null || (aiProjectUserQueryDTO.getSearchField() != null && !StringUtils.isEmpty(aiProjectUserQueryDTO.getKeyword())))) {
//            List<String> cids = new ArrayList<>();
//            for (Customer c:customers) {
//                cids.add(c.getId());
//            }
//            projectUserList = aiProjectUserMapper.listByCustomerIds(cids);
//            projectGroupList = aiProjectGroupMapper.listByCustomerIds(cids);
//
//        } else {
//            projectUserList = aiProjectUserMapper.listByGroupIds(null);
//            projectGroupList = aiProjectGroupMapper.listByCustomerIds(null);
//        }
//
//
//        HashMap<Object, AiProjectGroup> projectGroupMap = new HashMap<>();
//        HashMap<Object, AiProjectUser> projectUserMap = new HashMap<>();
//        for (AiProjectUser aiProjectUser: projectUserList) {
//            projectUserMap.put(aiProjectUser.getCustomerId(), aiProjectUser);
//        }
//        for (AiProjectGroup aiProjectGroup: projectGroupList) {
//            projectGroupMap.put(aiProjectGroup.getId(), aiProjectGroup);
//        }
//
//        // 可选客服
//        List<AiProjectGroupUserVo> data = new ArrayList();
//        for (Customer customer: customers) {
//            AiProjectGroupUserVo aiProjectGroupUserVo = new AiProjectGroupUserVo();
//            aiProjectGroupUserVo.setUserId(customer.getId());
//            aiProjectGroupUserVo.setName(customer.getName());
//            aiProjectGroupUserVo.setDept(customer.getDept());
//            aiProjectGroupUserVo.setGroupName(projectGroupMap.get(customer.getId())==null?null:projectGroupMap.get(customer.getId()).getGroupName());
//            aiProjectGroupUserVo.setSelected(projectUserMap.get(customer.getId()) == null? false : true);
//            data.add(aiProjectGroupUserVo);
//        }
//
//        // 已选客服
//        for (AiProjectUser aiProjectUser: selfList) {
//            AiProjectGroupUserVo aiProjectGroupUserVo = new AiProjectGroupUserVo();
//        }
//
//        // 数据封装
//        AiProjectGroupUserRespVO aiProjectGroupUserRespVO = new AiProjectGroupUserRespVO();
//        aiProjectGroupUserRespVO.setCustomers(data);
//        aiProjectGroupUserRespVO.setSelfList(selfList);
//        return null;
//    }


}
