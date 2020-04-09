package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.vo.AiProjectGroupUserRespVO;

import java.util.Collection;

public interface AiProjectUserService {

    void saveOrUpdate(Integer companyId, Long groupId, Collection<String> customerIdList);

    AiProjectGroupUserRespVO filterAiProjectUser(AiProjectUserQueryDTO aiProjectUserQueryDTO);
}
