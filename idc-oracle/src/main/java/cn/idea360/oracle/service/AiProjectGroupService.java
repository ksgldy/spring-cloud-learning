package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;

import java.util.List;

public interface AiProjectGroupService {

    List<ProjectGroupDTO> pageProjectGroup(PageDTO pageDTO) throws Exception;

    void addProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception;

    void updateProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception;

    boolean delProjectGroupById(Long groupId) throws Exception;

}
