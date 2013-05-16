package org.blue.taskflow.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author blue
 */

@MappedSuperclass
public class IdEntity {
	protected Long id;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //根据不同的数据选取不同的主键生成规则
	//@SequenceGenerator(name = "SEQ_TASKFLOW", sequenceName = "SEQ_TASKFLOW", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TASKFLOW")
	//@Column(name = "ID", nullable = false, length = 19)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getId());
	}
}
