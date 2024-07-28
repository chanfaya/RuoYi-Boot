package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.Category;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品三级分类业务对象 pms_category
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Category.class, reverseConvertGenerate = false)
public class CategoryBo extends BaseEntity {

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空", groups = { EditGroup.class })
    private Long catId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 父分类id
     */
    @NotNull(message = "父分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentCid;

    /**
     * 层级
     */
    @NotNull(message = "层级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @NotNull(message = "是否显示[0-不显示，1显示]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long showStatus;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    /**
     * 图标地址
     */
    @NotBlank(message = "图标地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String icon;

    /**
     * 计量单位
     */
    @NotBlank(message = "计量单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productUnit;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productCount;


}
