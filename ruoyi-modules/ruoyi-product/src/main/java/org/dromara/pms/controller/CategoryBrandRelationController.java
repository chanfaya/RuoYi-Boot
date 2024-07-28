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
import org.dromara.pms.domain.vo.CategoryBrandRelationVo;
import org.dromara.pms.domain.bo.CategoryBrandRelationBo;
import org.dromara.pms.service.ICategoryBrandRelationService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 品牌分类关联
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/categoryBrandRelation")
public class CategoryBrandRelationController extends BaseController {

    private final ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询品牌分类关联列表
     */
    @SaCheckPermission("pms:categoryBrandRelation:list")
    @GetMapping("/list")
    public TableDataInfo<CategoryBrandRelationVo> list(CategoryBrandRelationBo bo, PageQuery pageQuery) {
        return categoryBrandRelationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出品牌分类关联列表
     */
    @SaCheckPermission("pms:categoryBrandRelation:export")
    @Log(title = "品牌分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryBrandRelationBo bo, HttpServletResponse response) {
        List<CategoryBrandRelationVo> list = categoryBrandRelationService.queryList(bo);
        ExcelUtil.exportExcel(list, "品牌分类关联", CategoryBrandRelationVo.class, response);
    }

    /**
     * 获取品牌分类关联详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pms:categoryBrandRelation:query")
    @GetMapping("/{id}")
    public R<CategoryBrandRelationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(categoryBrandRelationService.queryById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @SaCheckPermission("pms:categoryBrandRelation:add")
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryBrandRelationBo bo) {
        return toAjax(categoryBrandRelationService.insertByBo(bo));
    }

    /**
     * 修改品牌分类关联
     */
    @SaCheckPermission("pms:categoryBrandRelation:edit")
    @Log(title = "品牌分类关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryBrandRelationBo bo) {
        return toAjax(categoryBrandRelationService.updateByBo(bo));
    }

    /**
     * 删除品牌分类关联
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pms:categoryBrandRelation:remove")
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(categoryBrandRelationService.deleteWithValidByIds(List.of(ids), true));
    }
}
