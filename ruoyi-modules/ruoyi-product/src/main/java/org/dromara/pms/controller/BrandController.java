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
import org.dromara.pms.domain.vo.BrandVo;
import org.dromara.pms.domain.bo.BrandBo;
import org.dromara.pms.service.IBrandService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 品牌
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/brand")
public class BrandController extends BaseController {

    private final IBrandService brandService;

    /**
     * 查询品牌列表
     */
    @SaCheckPermission("pms:brand:list")
    @GetMapping("/list")
    public TableDataInfo<BrandVo> list(BrandBo bo, PageQuery pageQuery) {
        return brandService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出品牌列表
     */
    @SaCheckPermission("pms:brand:export")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BrandBo bo, HttpServletResponse response) {
        List<BrandVo> list = brandService.queryList(bo);
        ExcelUtil.exportExcel(list, "品牌", BrandVo.class, response);
    }

    /**
     * 获取品牌详细信息
     *
     * @param brandId 主键
     */
    @SaCheckPermission("pms:brand:query")
    @GetMapping("/{brandId}")
    public R<BrandVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long brandId) {
        return R.ok(brandService.queryById(brandId));
    }

    /**
     * 新增品牌
     */
    @SaCheckPermission("pms:brand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BrandBo bo) {
        return toAjax(brandService.insertByBo(bo));
    }

    /**
     * 修改品牌
     */
    @SaCheckPermission("pms:brand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BrandBo bo) {
        return toAjax(brandService.updateByBo(bo));
    }

    /**
     * 删除品牌
     *
     * @param brandIds 主键串
     */
    @SaCheckPermission("pms:brand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] brandIds) {
        return toAjax(brandService.deleteWithValidByIds(List.of(brandIds), true));
    }
}
