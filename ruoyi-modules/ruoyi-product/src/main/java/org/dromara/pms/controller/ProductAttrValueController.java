package org.dromara.pms.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.pms.domain.vo.ProductAttrValueVo;
import org.dromara.pms.domain.bo.ProductAttrValueBo;
import org.dromara.pms.service.IProductAttrValueService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * spu属性值
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/productAttrValue")
public class ProductAttrValueController extends BaseController {

    private final IProductAttrValueService productAttrValueService;

    /**
     * 查询spu属性值列表
     */
    @SaCheckPermission("pms:productAttrValue:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAttrValueVo> list(ProductAttrValueBo bo, PageQuery pageQuery) {
        return productAttrValueService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出spu属性值列表
     */
    @SaCheckPermission("pms:productAttrValue:export")
    @Log(title = "spu属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAttrValueBo bo, HttpServletResponse response) {
        List<ProductAttrValueVo> list = productAttrValueService.queryList(bo);
        ExcelUtil.exportExcel(list, "spu属性值", ProductAttrValueVo.class, response);
    }

    /**
     * 获取spu属性值详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pms:productAttrValue:query")
    @GetMapping("/{id}")
    public R<ProductAttrValueVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(productAttrValueService.queryById(id));
    }

    /**
     * 新增spu属性值
     */
    @SaCheckPermission("pms:productAttrValue:add")
    @Log(title = "spu属性值", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAttrValueBo bo) {
        return toAjax(productAttrValueService.insertByBo(bo));
    }

    /**
     * 修改spu属性值
     */
    @SaCheckPermission("pms:productAttrValue:edit")
    @Log(title = "spu属性值", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAttrValueBo bo) {
        return toAjax(productAttrValueService.updateByBo(bo));
    }

    /**
     * 删除spu属性值
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pms:productAttrValue:remove")
    @Log(title = "spu属性值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(productAttrValueService.deleteWithValidByIds(List.of(ids), true));
    }
}
