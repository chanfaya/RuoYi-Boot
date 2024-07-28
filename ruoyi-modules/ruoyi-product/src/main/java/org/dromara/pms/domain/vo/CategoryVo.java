package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.Category;
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
 * 商品三级分类视图对象 pms_category
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Category.class)
public class CategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @ExcelProperty(value = "分类id")
    private Long catId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String name;

    /**
     * 父分类id
     */
    @ExcelProperty(value = "父分类id")
    private Long parentCid;

    /**
     * 层级
     */
    @ExcelProperty(value = "层级")
    private Long catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @ExcelProperty(value = "是否显示[0-不显示，1显示]")
    private Long showStatus;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 图标地址
     */
    @ExcelProperty(value = "图标地址")
    private String icon;

    /**
     * 计量单位
     */
    @ExcelProperty(value = "计量单位")
    private String productUnit;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量")
    private Long productCount;


}
