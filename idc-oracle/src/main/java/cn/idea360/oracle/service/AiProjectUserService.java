package cn.idea360.oracle.service;

import java.util.List;

public interface AiProjectUserService {

    void saveOrUpdate(Long groupId, List<Long> customerIdList);
}
