package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.FilterProjectUserDTO;
import cn.idea360.oracle.model.AiProjectUser;

import java.util.List;

public interface AiProjectUserService {

    void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList);

    List<AiProjectUser> filterAiProjectUser(FilterProjectUserDTO filterProjectUserDTO);
}
