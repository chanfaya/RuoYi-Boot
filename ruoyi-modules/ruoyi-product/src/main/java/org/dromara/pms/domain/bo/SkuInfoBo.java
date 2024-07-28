package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.SkuInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * sku信息业务对象 pms_sku_info
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SkuInfo.class, reverseConvertGenerate = false)
public class SkuInfoBo extends BaseEntity {

    /**
     * skuId
     */
    @NotNull(message = "skuId不能为空", groups = { EditGroup.class })
    private Long skuId;

    /**
     * spuId
     */
    @NotNull(message = "spuId不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long spuId;

    /**
     * sku名称
     */
    @NotBlank(message = "sku名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuName;

    /**
     * sku介绍描述
     */
    @NotBlank(message = "sku介绍描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuDesc;

    /**
     * 所属分类id
     */
    @NotNull(message = "所属分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long catalogId;

    /**
     * 品牌id
     */
    @NotNull(message = "品牌id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long brandId;

    /**
     * 默认图片
     */
    @NotBlank(message = "默认图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuDefaultImg;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuTitle;

    /**
     * 副标题
     */
    @NotBlank(message = "副标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuSubtitle;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;

    /**
     * 销量
     */
    @NotNull(message = "销量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long saleCount;


}
