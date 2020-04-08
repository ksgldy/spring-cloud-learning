package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiProjectGroupMapper;
import cn.idea360.oracle.dao.AiProjectUserMapper;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import cn.idea360.oracle.model.AiProjectUser;
import cn.idea360.oracle.service.AiProjectGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiProjectGroupServiceImpl implements AiProjectGroupService {

    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;
    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;

    @Override
    public List<ProjectGroupDTO> pageProjectGroup(PageDTO pageDTO) {
        try {
            List<AiProjectGroup> list = aiProjectGroupMapper.page(pageDTO);

            List<Long> gids = new ArrayList<>();
            for (AiProjectGroup a: list) {
                gids.add(a.getId());
            }

            List<AiProjectUser> aiProjectUsers = aiProjectUserMapper.listByGroupIds(gids);
            // todo 数据封装

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
