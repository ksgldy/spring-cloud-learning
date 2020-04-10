package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectGroupMapper;
import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.model.JsDepartment;
import cn.idea360.oracle.model.JsUser;
import cn.idea360.oracle.service.AiProjectUserService;
import cn.idea360.oracle.service.JsDepartmentService;
import cn.idea360.oracle.service.JsUserService;
import cn.idea360.oracle.vo.AiProjectGroupUserVO;
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
        this.removeByGroupId(groupId);

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
    public List<AiProjectGroupUserVO> filterAiProjectUser(AiProjectUserQueryDTO aiProjectUserQueryDTO) {

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
        Set<String> userIdSet = new HashSet<>();
        for (JsUser jsUser: jsUsers) {
            Integer departmentId = jsUser.getDepartmentId();
            String userId = jsUser.getUserId();
            if (departmentId != null) {
                departmentIdSet.add(departmentId);
            }
            if (!StringUtils.isEmpty(userId)) {
                userIdSet.add(userId);
            }
        }

        // 获取相关部门
        List<JsDepartment> jsDepartments = jsDepartmentService.listByDepartmentIds(departmentIdSet);
        HashMap<Integer, JsDepartment> jsDepartmentMap = new HashMap<>();
        for (JsDepartment jsDepartment: jsDepartments) {
            jsDepartmentMap.put(jsDepartment.getDepartmentId(), jsDepartment);
        }

        // 获取AI分组
        List<AiProjectGroup> aiProjectGroups = aiProjectGroupMapper.selectList();
        HashMap<Long, AiProjectGroup> aiProjectGroupMap = new HashMap<>();
        for (AiProjectGroup aiProjectGroup: aiProjectGroups) {
            aiProjectGroupMap.put(aiProjectGroup.getId(), aiProjectGroup);
        }

        // 获取AI分组客服
        List<AiProjectUser> aiProjectUsers = aiProjectUserMapper.listByCustomerIds(userIdSet);
        HashMap<String, AiProjectUser> aiProjectUserMap = new HashMap<>();
        for (AiProjectUser aiProjectUser: aiProjectUsers) {
            aiProjectUserMap.put(aiProjectUser.getCustomerId(), aiProjectUser);
        }

        // 可选客服人员
        List<AiProjectGroupUserVO> allCustomers = new ArrayList<>();
        for (JsUser jsUser: jsUsers) {
            AiProjectGroupUserVO aiProjectGroupUserVO = new AiProjectGroupUserVO();
            aiProjectGroupUserVO.setId(jsUser.getUserId());
            aiProjectGroupUserVO.setRealName(jsUser.getRealName());

            Integer departmentId = jsUser.getDepartmentId();
            aiProjectGroupUserVO.setDepartmentName(jsDepartmentMap.get(departmentId) == null? null : jsDepartmentMap.get(departmentId).getName());
            Long groupId = aiProjectUserMap.get(jsUser.getUserId()) == null? null: aiProjectUserMap.get(jsUser.getUserId()).getGroupId();
            aiProjectGroupUserVO.setGroupName(aiProjectGroupMap.get(groupId) == null? null : aiProjectGroupMap.get(groupId).getGroupName());
            aiProjectGroupUserVO.setSelected(aiProjectUserMap.get(jsUser.getUserId()) == null? false : true);
            allCustomers.add(aiProjectGroupUserVO);
        }

//        AiProjectGroupUserRespVO result = new AiProjectGroupUserRespVO();
//        result.setAllCustomers(allCustomers);
//
//        // 已选客服人员
//        List<AiProjectUser> currAiProjectUserList = new ArrayList<>();
//        HashMap<String, AiProjectUser> currAiProjectUserMap = new HashMap<>();
//        if (aiProjectUserQueryDTO != null && aiProjectUserQueryDTO.getGroupId() != null && aiProjectUserQueryDTO.getGroupId() > 0L) {
//            currAiProjectUserList = aiProjectUserMapper.listByGroupId(aiProjectUserQueryDTO.getGroupId());
//            currAiProjectUserMap = new HashMap<>();
//            for (AiProjectUser aiProjectUser:currAiProjectUserList) {
//                currAiProjectUserMap.put(aiProjectUser.getCustomerId(), aiProjectUser);
//            }
//        }
//
//
//        List<AiProjectGroupUserVO> currCustomers = new ArrayList<>();
//        for (JsUser jsUser: jsUsers) {
//
//            if (currAiProjectUserMap.containsKey(jsUser.getUserId())) {
//                AiProjectGroupUserVO aiProjectGroupUserVO = new AiProjectGroupUserVO();
//                aiProjectGroupUserVO.setId(jsUser.getUserId());
//                aiProjectGroupUserVO.setRealName(jsUser.getRealName());
//                Long groupId = aiProjectUserQueryDTO.getGroupId();
//                aiProjectGroupUserVO.setGroupName(aiProjectGroupMap.get(groupId).getGroupName());
//                aiProjectGroupUserVO.setDepartmentName(jsDepartmentMap.get(jsUser.getDepartmentId()).getName());
//                aiProjectGroupUserVO.setSelected(true);
//                currCustomers.add(aiProjectGroupUserVO);
//            }
//        }
//        result.setCurrCustomers(currCustomers);

        return allCustomers;
    }

    @Override
    public int removeByGroupId(Long groupId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        int result = aiProjectUserMapper.deleteByMap(map);
        return result;
    }

    @Override
    public List<AiProjectGroupUserVO> listByGroupId(Long groupId) {
        if (groupId == null || groupId <= 0L) {
            return new ArrayList<>();
        }

//        AiProjectGroup aiProjectGroup = aiProjectGroupMapper.selectById(groupId);

        // 获取当前组客服
        HashMap<String, AiProjectUser> aiProjectUserHashMap = new HashMap<>();
        List<String> userIds = new ArrayList();
        List<AiProjectUser> currAiProjectUserList = aiProjectUserMapper.listByGroupId(groupId);
        for (AiProjectUser aiProjectUser:currAiProjectUserList) {
            userIds.add(aiProjectUser.getCustomerId());
            aiProjectUserHashMap.put(aiProjectUser.getCustomerId(), aiProjectUser);
        }

        // 获取关联客服

        List<Integer> departmentIds = new ArrayList<>();
        List<JsUser> jsUsers = jsUserService.listByIds(userIds);
        for(JsUser jsUser: jsUsers) {
            departmentIds.add(jsUser.getDepartmentId());
        }

        // 获取部门信息
        HashMap<Integer, JsDepartment> jsDepartmentHashMap = new HashMap<>();
        List<JsDepartment> jsDepartments = jsDepartmentService.listByDepartmentIds(departmentIds);
        for(JsDepartment jsDepartment: jsDepartments) {
            jsDepartmentHashMap.put(jsDepartment.getDepartmentId(), jsDepartment);
        }

        // 封装数据
        List<AiProjectGroupUserVO> currCustomers = new ArrayList<>();
        for (JsUser jsUser: jsUsers) {
            AiProjectGroupUserVO aiProjectGroupUserVO = new AiProjectGroupUserVO();
            aiProjectGroupUserVO.setId(jsUser.getUserId());
            aiProjectGroupUserVO.setRealName(jsUser.getRealName());
            aiProjectGroupUserVO.setDepartmentName(jsDepartmentHashMap.get(jsUser.getDepartmentId()).getName());
            aiProjectGroupUserVO.setSelected(true);
            currCustomers.add(aiProjectGroupUserVO);
        }

        return currCustomers;
    }

}
