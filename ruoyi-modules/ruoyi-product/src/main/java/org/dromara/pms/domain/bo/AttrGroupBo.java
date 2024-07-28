package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.AttrGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 属性分组业务对象 pms_attr_group
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AttrGroup.class, reverseConvertGenerate = false)
public class AttrGroupBo extends BaseEntity {

    /**
     * 分组id
     */
    @NotNull(message = "分组id不能为空", groups = { EditGroup.class })
    private Long attrGroupId;

    /**
     * 组名
     */
    @NotBlank(message = "组名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrGroupName;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String descript;

    /**
     * 组图标
     */
    @NotBlank(message = "组图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String icon;

    /**
     * 所属分类id
     */
    @NotNull(message = "所属分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long catelogId;


}
