package cn.idea360.oracle.service;

import java.util.List;

public interface AiProjectUserService {

    void saveOrUpdate(Integer companyId, Long groupId, List<String> customerIdList);
}
