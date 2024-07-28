package org.dromara.pms.domain.vo;

import org.dromara.pms.domain.AttrGroup;
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
 * 属性分组视图对象 pms_attr_group
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AttrGroup.class)
public class AttrGroupVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @ExcelProperty(value = "分组id")
    private Long attrGroupId;

    /**
     * 组名
     */
    @ExcelProperty(value = "组名")
    private String attrGroupName;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String descript;

    /**
     * 组图标
     */
    @ExcelProperty(value = "组图标")
    private String icon;

    /**
     * 所属分类id
     */
    @ExcelProperty(value = "所属分类id")
    private Long catelogId;


}
