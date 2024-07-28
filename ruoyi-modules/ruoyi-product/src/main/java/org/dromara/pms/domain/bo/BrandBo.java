package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.Brand;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 品牌业务对象 pms_brand
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Brand.class, reverseConvertGenerate = false)
public class BrandBo extends BaseEntity {

    /**
     * 品牌id
     */
    @NotNull(message = "品牌id不能为空", groups = { EditGroup.class })
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 品牌logo地址
     */
    @NotBlank(message = "品牌logo地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String logo;

    /**
     * 介绍
     */
    @NotBlank(message = "介绍不能为空", groups = { AddGroup.class, EditGroup.class })
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(message = "显示状态[0-不显示；1-显示]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long showStatus;

    /**
     * 检索首字母
     */
    @NotBlank(message = "检索首字母不能为空", groups = { AddGroup.class, EditGroup.class })
    private String firstLetter;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;


}
