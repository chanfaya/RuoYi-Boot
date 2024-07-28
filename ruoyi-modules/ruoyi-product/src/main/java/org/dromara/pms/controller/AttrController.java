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
import org.dromara.pms.domain.vo.AttrVo;
import org.dromara.pms.domain.bo.AttrBo;
import org.dromara.pms.service.IAttrService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品属性
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/attr")
public class AttrController extends BaseController {

    private final IAttrService attrService;

    /**
     * 查询商品属性列表
     */
    @SaCheckPermission("pms:attr:list")
    @GetMapping("/list")
    public TableDataInfo<AttrVo> list(AttrBo bo, PageQuery pageQuery) {
        return attrService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品属性列表
     */
    @SaCheckPermission("pms:attr:export")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AttrBo bo, HttpServletResponse response) {
        List<AttrVo> list = attrService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品属性", AttrVo.class, response);
    }

    /**
     * 获取商品属性详细信息
     *
     * @param attrId 主键
     */
    @SaCheckPermission("pms:attr:query")
    @GetMapping("/{attrId}")
    public R<AttrVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long attrId) {
        return R.ok(attrService.queryById(attrId));
    }

    /**
     * 新增商品属性
     */
    @SaCheckPermission("pms:attr:add")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AttrBo bo) {
        return toAjax(attrService.insertByBo(bo));
    }

    /**
     * 修改商品属性
     */
    @SaCheckPermission("pms:attr:edit")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AttrBo bo) {
        return toAjax(attrService.updateByBo(bo));
    }

    /**
     * 删除商品属性
     *
     * @param attrIds 主键串
     */
    @SaCheckPermission("pms:attr:remove")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{attrIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] attrIds) {
        return toAjax(attrService.deleteWithValidByIds(List.of(attrIds), true));
    }
}
