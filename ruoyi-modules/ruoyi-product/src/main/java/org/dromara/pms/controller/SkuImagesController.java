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
import org.dromara.pms.domain.vo.SkuImagesVo;
import org.dromara.pms.domain.bo.SkuImagesBo;
import org.dromara.pms.service.ISkuImagesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * sku图片
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/skuImages")
public class SkuImagesController extends BaseController {

    private final ISkuImagesService skuImagesService;

    /**
     * 查询sku图片列表
     */
    @SaCheckPermission("pms:skuImages:list")
    @GetMapping("/list")
    public TableDataInfo<SkuImagesVo> list(SkuImagesBo bo, PageQuery pageQuery) {
        return skuImagesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出sku图片列表
     */
    @SaCheckPermission("pms:skuImages:export")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SkuImagesBo bo, HttpServletResponse response) {
        List<SkuImagesVo> list = skuImagesService.queryList(bo);
        ExcelUtil.exportExcel(list, "sku图片", SkuImagesVo.class, response);
    }

    /**
     * 获取sku图片详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pms:skuImages:query")
    @GetMapping("/{id}")
    public R<SkuImagesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(skuImagesService.queryById(id));
    }

    /**
     * 新增sku图片
     */
    @SaCheckPermission("pms:skuImages:add")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SkuImagesBo bo) {
        return toAjax(skuImagesService.insertByBo(bo));
    }

    /**
     * 修改sku图片
     */
    @SaCheckPermission("pms:skuImages:edit")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SkuImagesBo bo) {
        return toAjax(skuImagesService.updateByBo(bo));
    }

    /**
     * 删除sku图片
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pms:skuImages:remove")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(skuImagesService.deleteWithValidByIds(List.of(ids), true));
    }
}
