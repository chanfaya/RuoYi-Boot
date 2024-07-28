package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.Brand;
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
 * 品牌视图对象 pms_brand
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Brand.class)
public class BrandVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @ExcelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 品牌名
     */
    @ExcelProperty(value = "品牌名")
    private String name;

    /**
     * 品牌logo地址
     */
    @ExcelProperty(value = "品牌logo地址")
    private String logo;

    /**
     * 介绍
     */
    @ExcelProperty(value = "介绍")
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ExcelProperty(value = "显示状态[0-不显示；1-显示]")
    private Long showStatus;

    /**
     * 检索首字母
     */
    @ExcelProperty(value = "检索首字母")
    private String firstLetter;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;


}
