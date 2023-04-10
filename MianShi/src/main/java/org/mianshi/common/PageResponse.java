package org.mianshi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : nate.jiang
 * @date : 2019/12/26 10:19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = -5790241843522462958L;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前数据
     */
    private List<T> items;

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 每页的大小
     */
    private Integer pageSize;

    public PageResponse() {
    }

    public PageResponse(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResponse(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    /**
     * 有参总数，当前页，每页数量的构造方法
     * 计算总页数
     *
     * @param total
     * @param currentPage
     * @param pageSize
     */
    public PageResponse(Long total, Integer currentPage, Integer pageSize) {
        this.total = total;
        this.totalPage = (total.intValue() - 1) / pageSize + 1;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}