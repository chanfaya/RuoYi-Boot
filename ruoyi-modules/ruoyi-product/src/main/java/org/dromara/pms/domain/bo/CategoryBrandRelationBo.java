package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.CategoryBrandRelation;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 品牌分类关联业务对象 pms_category_brand_relation
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CategoryBrandRelation.class, reverseConvertGenerate = false)
public class CategoryBrandRelationBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 品牌id
     */
    @NotNull(message = "品牌id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long brandId;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long catelogId;

    /**
     * 
     */
    @NotBlank(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private String brandName;

    /**
     * 
     */
    @NotBlank(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private String catelogName;


}
