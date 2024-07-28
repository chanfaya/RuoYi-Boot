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
import org.dromara.pms.domain.vo.SkuInfoVo;
import org.dromara.pms.domain.bo.SkuInfoBo;
import org.dromara.pms.service.ISkuInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * sku信息
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/skuInfo")
public class SkuInfoController extends BaseController {

    private final ISkuInfoService skuInfoService;

    /**
     * 查询sku信息列表
     */
    @SaCheckPermission("pms:skuInfo:list")
    @GetMapping("/list")
    public TableDataInfo<SkuInfoVo> list(SkuInfoBo bo, PageQuery pageQuery) {
        return skuInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出sku信息列表
     */
    @SaCheckPermission("pms:skuInfo:export")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SkuInfoBo bo, HttpServletResponse response) {
        List<SkuInfoVo> list = skuInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "sku信息", SkuInfoVo.class, response);
    }

    /**
     * 获取sku信息详细信息
     *
     * @param skuId 主键
     */
    @SaCheckPermission("pms:skuInfo:query")
    @GetMapping("/{skuId}")
    public R<SkuInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long skuId) {
        return R.ok(skuInfoService.queryById(skuId));
    }

    /**
     * 新增sku信息
     */
    @SaCheckPermission("pms:skuInfo:add")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SkuInfoBo bo) {
        return toAjax(skuInfoService.insertByBo(bo));
    }

    /**
     * 修改sku信息
     */
    @SaCheckPermission("pms:skuInfo:edit")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SkuInfoBo bo) {
        return toAjax(skuInfoService.updateByBo(bo));
    }

    /**
     * 删除sku信息
     *
     * @param skuIds 主键串
     */
    @SaCheckPermission("pms:skuInfo:remove")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{skuIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] skuIds) {
        return toAjax(skuInfoService.deleteWithValidByIds(List.of(skuIds), true));
    }
}
