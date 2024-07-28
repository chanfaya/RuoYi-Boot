package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.ProductAttrValue;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * spu属性值业务对象 pms_product_attr_value
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ProductAttrValue.class, reverseConvertGenerate = false)
public class ProductAttrValueBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long spuId;

    /**
     * 属性id
     */
    @NotNull(message = "属性id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrId;

    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrName;

    /**
     * 属性值
     */
    @NotBlank(message = "属性值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrValue;

    /**
     * 顺序
     */
    @NotNull(message = "顺序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrSort;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    @NotNull(message = "快速展示【是否展示在介绍上；0-否 1-是】不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long quickShow;


}
