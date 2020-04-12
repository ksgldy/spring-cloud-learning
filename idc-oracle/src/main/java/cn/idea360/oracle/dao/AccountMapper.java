package cn.idea360.oracle.dao;

import cn.idea360.oracle.common.Page;
import cn.idea360.oracle.model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AccountMapper {

    /**
     * 插入单条数据
     * @param account
     * @return 返回主键id
     */
    int insert(Account account);

    /**
     * 批量插入list
     * @param data
     */
    void insertBatch(@Param("coll") Collection<Account> data);

    /**
     * 更新数据
     * @param account
     * @return
     */
    int updateIgnoreNullById(@Param("et") Account account);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int removeById(@Param("id") Integer id);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Account selectById(@Param("id") Integer id);

    /**
     * 根据其他字段查询数据
     * @param columnMap
     * @return
     */
    Account selectByMap(@Param("cm") Map<String, Object> columnMap);

    /**
     * 根据id数组批量查询数据
     * @param idArray
     * @return
     */
    List<Account> selectByIds(@Param("coll") Integer[] idArray);

    /**
     * 根据分页参数查询数据
     * @param page
     * @return
     */
    List<Account> selectPage(@Param("page") Page page);

}
