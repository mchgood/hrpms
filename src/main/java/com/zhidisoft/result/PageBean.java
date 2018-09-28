package com.zhidisoft.result;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 分页结果Bean，考虑easyui数据表格以及MyBatis分页特性，提供了order和offset属性
 * 
 * @author Lu Jianliang
 *
 * @param <E>
 */
public class PageBean<E> {
	/** 当前页码 */
	@JsonIgnore
	private Integer page = 1;
	
	/** 每页记录数 */
	@JsonIgnore
	private Integer rows = 10;
	
	/** 总记录数,此数据符合easyui-datagrid需求 */
	private Integer total;
	
	/** 分页结果,此数据符合easyui-datagrid需求 */
	@JsonProperty("rows")
	private List<E> result;
	
	/** 排序字段名,此数据符合easyui-datagrid需求 */
	@JsonIgnore
	private String sort;
	
	/** 排序方式,此数据符合easyui-datagrid需求 */
	@JsonIgnore
	private String order = "desc";
	
	/** 起始索引,此数据符合mybatis需求 */
	@JsonIgnore
	private Integer offset = 0;
	
	/** 查询条件 */
	@JsonIgnore
	private Map<String, Object> condition;
	
	public PageBean(){
		
	}


	public PageBean(Integer page, Integer rows) {
		if (page == null || page < 1) {
			this.page = 1;
		} else {
			this.page = page;
		}
		if (rows == null || rows < 1) {
			this.rows = 15;
		} else {
			this.rows = rows;
		}
		this.offset = (page - 1) * rows;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getRows() {
		return rows;
	}


	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public List<E> getResult() {
		return result;
	}


	public void setResult(List<E> result) {
		this.result = result;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public Integer getOffset() {
		return ( page - 1 ) * rows;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}


	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}


	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", total=" + total + ", result=" + result + ", sort="
				+ sort + ", order=" + order + ", offset=" + offset + ", condition=" + condition + "]";
	}

	
}
