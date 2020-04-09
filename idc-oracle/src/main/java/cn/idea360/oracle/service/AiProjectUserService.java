package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.FilterProjectUserDTO;
import cn.idea360.oracle.vo.AiProjectUserRespVO;

import java.util.List;

public interface AiProjectUserService {

    void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList) throws Exception;

    AiProjectUserRespVO filterAiProjectUser(FilterProjectUserDTO filterProjectUserDTO) throws Exception;
}
