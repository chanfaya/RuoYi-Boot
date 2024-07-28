package org.dromara.pms.domain.vo;

import java.math.BigDecimal;
import org.dromara.pms.domain.SkuInfo;
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
 * sku信息视图对象 pms_sku_info
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SkuInfo.class)
public class SkuInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * skuId
     */
    @ExcelProperty(value = "skuId")
    private Long skuId;

    /**
     * spuId
     */
    @ExcelProperty(value = "spuId")
    private Long spuId;

    /**
     * sku名称
     */
    @ExcelProperty(value = "sku名称")
    private String skuName;

    /**
     * sku介绍描述
     */
    @ExcelProperty(value = "sku介绍描述")
    private String skuDesc;

    /**
     * 所属分类id
     */
    @ExcelProperty(value = "所属分类id")
    private Long catalogId;

    /**
     * 品牌id
     */
    @ExcelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 默认图片
     */
    @ExcelProperty(value = "默认图片")
    private String skuDefaultImg;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String skuTitle;

    /**
     * 副标题
     */
    @ExcelProperty(value = "副标题")
    private String skuSubtitle;

    /**
     * 价格
     */
    @ExcelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 销量
     */
    @ExcelProperty(value = "销量")
    private Long saleCount;


}
