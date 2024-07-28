package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.SkuImages;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * sku图片业务对象 pms_sku_images
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SkuImages.class, reverseConvertGenerate = false)
public class SkuImagesBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * sku_id
     */
    @NotNull(message = "sku_id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 图片地址
     */
    @NotBlank(message = "图片地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imgUrl;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    @NotNull(message = "默认图[0 - 不是默认图，1 - 是默认图]不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long defaultImg;


}
