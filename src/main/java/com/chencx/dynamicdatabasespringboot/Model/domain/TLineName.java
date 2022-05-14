package com.chencx.dynamicdatabasespringboot.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 机组表
 * @TableName T_LINE_NAME
 */
@TableName(value ="T_LINE_NAME",schema="TGSG")
@Data
public class TLineName implements Serializable {
    /**
     * 机组代码
     */
    @TableId
    private String LINENO;

    /**
     * 是否校验
     */
    private Object ISCHECKED;

    /**
     * 机组名称
     */
    private String LINENAME;

    /**
     * 机组编号
     */
    private String LINECODE;

    /**
     * 机组描述
     */
    private String LINECOMMENT;

    private static final long serialVersionUID = -5214349116664547548L;
}