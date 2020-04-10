package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiProjectUserQueryDTO;
import cn.idea360.oracle.vo.AiProjectGroupUserVO;

import java.util.Collection;
import java.util.List;

public interface AiProjectUserService {

    void saveOrUpdate(Integer companyId, Long groupId, Collection<String> customerIdList);

    List<AiProjectGroupUserVO> filterAiProjectUser(AiProjectUserQueryDTO aiProjectUserQueryDTO);

    int removeByGroupId(Long groupId);

    List<AiProjectGroupUserVO> listByGroupId(Long groupId);


}
