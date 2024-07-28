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
import org.dromara.pms.domain.vo.AttrGroupVo;
import org.dromara.pms.domain.bo.AttrGroupBo;
import org.dromara.pms.service.IAttrGroupService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 属性分组
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/attrGroup")
public class AttrGroupController extends BaseController {

    private final IAttrGroupService attrGroupService;

    /**
     * 查询属性分组列表
     */
    @SaCheckPermission("pms:attrGroup:list")
    @GetMapping("/list")
    public TableDataInfo<AttrGroupVo> list(AttrGroupBo bo, PageQuery pageQuery) {
        return attrGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出属性分组列表
     */
    @SaCheckPermission("pms:attrGroup:export")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AttrGroupBo bo, HttpServletResponse response) {
        List<AttrGroupVo> list = attrGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "属性分组", AttrGroupVo.class, response);
    }

    /**
     * 获取属性分组详细信息
     *
     * @param attrGroupId 主键
     */
    @SaCheckPermission("pms:attrGroup:query")
    @GetMapping("/{attrGroupId}")
    public R<AttrGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long attrGroupId) {
        return R.ok(attrGroupService.queryById(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @SaCheckPermission("pms:attrGroup:add")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AttrGroupBo bo) {
        return toAjax(attrGroupService.insertByBo(bo));
    }

    /**
     * 修改属性分组
     */
    @SaCheckPermission("pms:attrGroup:edit")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AttrGroupBo bo) {
        return toAjax(attrGroupService.updateByBo(bo));
    }

    /**
     * 删除属性分组
     *
     * @param attrGroupIds 主键串
     */
    @SaCheckPermission("pms:attrGroup:remove")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{attrGroupIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] attrGroupIds) {
        return toAjax(attrGroupService.deleteWithValidByIds(List.of(attrGroupIds), true));
    }
}
