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
import org.dromara.pms.domain.vo.CategoryVo;
import org.dromara.pms.domain.bo.CategoryBo;
import org.dromara.pms.service.ICategoryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品三级分类
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/category")
public class CategoryController extends BaseController {

    private final ICategoryService categoryService;

    /**
     * 查询商品三级分类列表
     */
    @SaCheckPermission("pms:category:list")
    @GetMapping("/list")
    public TableDataInfo<CategoryVo> list(CategoryBo bo, PageQuery pageQuery) {
        return categoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品三级分类列表
     */
    @SaCheckPermission("pms:category:export")
    @Log(title = "商品三级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryBo bo, HttpServletResponse response) {
        List<CategoryVo> list = categoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品三级分类", CategoryVo.class, response);
    }

    /**
     * 获取商品三级分类详细信息
     *
     * @param catId 主键
     */
    @SaCheckPermission("pms:category:query")
    @GetMapping("/{catId}")
    public R<CategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long catId) {
        return R.ok(categoryService.queryById(catId));
    }

    /**
     * 新增商品三级分类
     */
    @SaCheckPermission("pms:category:add")
    @Log(title = "商品三级分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(categoryService.insertByBo(bo));
    }

    /**
     * 修改商品三级分类
     */
    @SaCheckPermission("pms:category:edit")
    @Log(title = "商品三级分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(categoryService.updateByBo(bo));
    }

    /**
     * 删除商品三级分类
     *
     * @param catIds 主键串
     */
    @SaCheckPermission("pms:category:remove")
    @Log(title = "商品三级分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{catIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] catIds) {
        return toAjax(categoryService.deleteWithValidByIds(List.of(catIds), true));
    }
}
