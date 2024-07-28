package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.CategoryBrandRelation;
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
 * 品牌分类关联视图对象 pms_category_brand_relation
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = CategoryBrandRelation.class)
public class CategoryBrandRelationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 品牌id
     */
    @ExcelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 分类id
     */
    @ExcelProperty(value = "分类id")
    private Long catelogId;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String brandName;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String catelogName;


}
