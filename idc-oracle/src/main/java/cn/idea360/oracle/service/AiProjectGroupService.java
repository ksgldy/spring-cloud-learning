package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.vo.AiProjectGroupDetailRespVO;

public interface AiProjectGroupService {

    PageRespDTO pageProjectGroup(PageDTO pageDTO);

    boolean addProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO);

    boolean updateProjectGroup(AiProjectGroupReqDTO aiProjectGroupReqDTO);

    boolean delProjectGroupById(Long groupId);

    int totalRecord();

    AiProjectGroupDetailRespVO currGroupDataByGroupId(Long groupId);

}
