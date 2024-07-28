package org.dromara.pms.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 属性&属性分组关联对象 pms_attr_attrgroup_relation
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelation extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性分组id
     */
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    private Long attrSort;


}
