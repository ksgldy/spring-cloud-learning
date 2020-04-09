package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;

import java.util.List;

public interface AiProjectGroupService {

    List<ProjectGroupDTO> pageProjectGroup(PageDTO pageDTO) throws Exception;

    boolean addProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception;

    boolean updateProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO) throws Exception;

    boolean delProjectGroupById(Long groupId) throws Exception;

    int totalRecord();

}
