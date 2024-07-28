package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.SkuImages;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * sku图片视图对象 pms_sku_images
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SkuImages.class)
public class SkuImagesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * sku_id
     */
    @ExcelProperty(value = "sku_id")
    private Long skuId;

    /**
     * 图片地址
     */
    @ExcelProperty(value = "图片地址")
    private String imgUrl;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    @ExcelProperty(value = "默认图[0 - 不是默认图，1 - 是默认图]")
    private Long defaultImg;


}
