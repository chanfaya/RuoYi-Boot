package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.AttrAttrgroupRelation;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 属性&属性分组关联业务对象 pms_attr_attrgroup_relation
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AttrAttrgroupRelation.class, reverseConvertGenerate = false)
public class AttrAttrgroupRelationBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 属性id
     */
    @NotNull(message = "属性id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrId;

    /**
     * 属性分组id
     */
    @NotNull(message = "属性分组id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    @NotNull(message = "属性组内排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attrSort;


}
