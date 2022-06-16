package com.chencx.dynamicdatabasespringboot.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName SQLMAP
 */
@TableName(value ="SQLMAP")
@Data
public class Sqlmap implements Serializable {
    /**
     * 
     */
    @TableId
    private String name;

    /**
     * 
     */
    private String poolName;

    /**
     * 
     */
    private String sqlText;

    /**
     * 
     */
    private String args;
    /**
     *
     */
    private String comment;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}