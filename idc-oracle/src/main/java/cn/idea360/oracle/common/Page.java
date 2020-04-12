package cn.idea360.oracle.common;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Mapper进一步实现可以基于拦截器实现
 * @param <T>
 */
@Data
public class Page<T> {

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页
     */
    private long current = 1;
    /**
     * KEY/VALUE 条件
     */
    private Map<Object, Object> condition;

    /**
     * oracle分页： start
     */
    private long startIndex = 1;
    /**
     * oracle分页： end
     */
    private long endIndex = 10;

    public Page() {
    }

    public Page(long current, long size) {
        this(current, size, 0);
    }

    public Page(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.startIndex = (getCurrent()-1)*size+1;
        this.endIndex = getCurrent()*size;
    }

    /**
     * 计算当前分页偏移量
     */
    public long offset() {
        return getCurrent() > 0 ? (getCurrent() - 1) * getSize() : 0;
    }

    /**
     * 当前分页总页数
     */
    public long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * oracle分页开始
     * @return
     */
    public long getStartIndex() {
        return (getCurrent()-1)*size+1;
    }

    /**
     * oracle分页结束
     * @return
     */
    public long getEndIndex() {
        return getCurrent()*size;
    }

}
