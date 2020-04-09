package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectGroupMapper;
import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.service.AiProjectGroupService;
import cn.idea360.oracle.service.AiProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AiProjectGroupServiceImpl implements AiProjectGroupService {

    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;
    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;
    @Autowired
    private AiProjectUserService aiProjectUserService;

    @Override
    public List<ProjectGroupDTO> pageProjectGroup(PageDTO pageDTO) throws Exception{

        // todo 分页参数必填，sql没校验
        List<AiProjectGroup> list = aiProjectGroupMapper.page(pageDTO);

        List<Long> gids = new ArrayList<>();
        for (AiProjectGroup a: list) {
            gids.add(a.getId());
        }

        List<AiProjectUser> aiProjectUsers = aiProjectUserMapper.listByGroupIds(gids);

        // 数据封装, 减少连表查询.key: groupId, value: uid
        HashMap<Long, List<String>> map = new HashMap<>();

        for (AiProjectUser u: aiProjectUsers) {
            List<String> uids = map.get(u.getGroupId());
            if (CollectionUtils.isEmpty(uids)) {
                uids = new ArrayList<>();
            }
            uids.add(u.getCustomerId());
        }

        List<ProjectGroupDTO> resp = new ArrayList<>();
        for (AiProjectGroup aiProjectGroup: list) {
            ProjectGroupDTO projectGroupDTO = new ProjectGroupDTO();
            projectGroupDTO.setId(aiProjectGroup.getId());
            projectGroupDTO.setGroupName(aiProjectGroup.getGroupName());
            projectGroupDTO.setCustomers(map.get(aiProjectGroup.getId()));
            resp.add(projectGroupDTO);
        }

        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception{

        // 插入AiProjectGroup
        AiProjectGroup aiProjectGroup = new AiProjectGroup();
        aiProjectGroup.setGroupName(aiProjectGroupReqDTO.getGroupName());
        aiProjectGroup.setCompanyId(aiProjectGroupReqDTO.getCompanyId());
        aiProjectGroup.setCreateTime(new Date());
        aiProjectGroup.setUpdateTime(new Date());
        aiProjectGroup.setCreater(aiProjectGroupReqDTO.getCreater());
        aiProjectGroup.setUpdater(aiProjectGroupReqDTO.getUpdater());

        synchronized (this) {
            int currRank = aiProjectGroupMapper.selectLastestRank();
            aiProjectGroup.setRank(++currRank);
            int insert = aiProjectGroupMapper.insert(aiProjectGroup);
        }

        // 插入AiProjectUser
        aiProjectUserService.saveOrUpdate(aiProjectGroupReqDTO.getCompanyId(), aiProjectGroup.getId(), aiProjectGroupReqDTO.getUserIds());

        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception {

        Long groupId = aiProjectGroupReqDTO.getId();
        AiProjectGroup aiProjectGroup = aiProjectGroupMapper.selectById(groupId);
        if (aiProjectGroup == null) {
            throw new NullPointerException("数据不存在");
        }

        aiProjectGroup.setGroupName(aiProjectGroupReqDTO.getGroupName());
        aiProjectGroup.setUpdateTime(new Date());
        aiProjectGroup.setUpdater(aiProjectGroupReqDTO.getUpdater());
        aiProjectGroupMapper.updateIgnoreNullById(aiProjectGroup);

        aiProjectUserService.saveOrUpdate(aiProjectGroupReqDTO.getCompanyId(), aiProjectGroup.getId(), aiProjectGroupReqDTO.getUserIds());
        return Boolean.TRUE;
    }

    @Override
    public boolean delProjectGroupById(Long groupId) throws Exception {
        int i = aiProjectGroupMapper.deleteById(groupId);
        return i > 0 ? Boolean.TRUE: Boolean.FALSE;
    }

    @Override
    public int totalRecord() {

        return aiProjectGroupMapper.totalRecord();
    }


}
