package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.Attr;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品属性业务对象 pms_attr
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Attr.class, reverseConvertGenerate = false)
public class AttrBo extends BaseEntity {

    /**
     * 属性id
     */
    @NotNull(message = "属性id不能为空", groups = { EditGroup.class })
    private Long attrId;

    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @NotNull(message = "是否需要检索[0-不需要，1-需要]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long searchType;

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    @NotNull(message = "值类型[0-为单个值，1-可以选择多个值]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long valueType;

    /**
     * 属性图标
     */
    @NotBlank(message = "属性图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    @NotBlank(message = "可选值列表[用逗号分隔]不能为空", groups = { AddGroup.class, EditGroup.class })
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @NotNull(message = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @NotNull(message = "启用状态[0 - 禁用，1 - 启用]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long enable;

    /**
     * 所属分类
     */
    @NotNull(message = "所属分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long catelogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @NotNull(message = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long showDesc;


}
