package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.ProductAttrValue;
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
 * spu属性值视图对象 pms_product_attr_value
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ProductAttrValue.class)
public class ProductAttrValueVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long spuId;

    /**
     * 属性id
     */
    @ExcelProperty(value = "属性id")
    private Long attrId;

    /**
     * 属性名
     */
    @ExcelProperty(value = "属性名")
    private String attrName;

    /**
     * 属性值
     */
    @ExcelProperty(value = "属性值")
    private String attrValue;

    /**
     * 顺序
     */
    @ExcelProperty(value = "顺序")
    private Long attrSort;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    @ExcelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
    private Long quickShow;


}
