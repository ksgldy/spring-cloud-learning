package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.ProjectGroupDTO;

import java.util.List;

public interface AiProjectGroupService {

    List<ProjectGroupDTO> pageProjectGroup(PageDTO pageDTO);

}
