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
import org.dromara.pms.domain.vo.AttrAttrgroupRelationVo;
import org.dromara.pms.domain.bo.AttrAttrgroupRelationBo;
import org.dromara.pms.service.IAttrAttrgroupRelationService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 属性&属性分组关联
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/attrAttrgroupRelation")
public class AttrAttrgroupRelationController extends BaseController {

    private final IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联列表
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:list")
    @GetMapping("/list")
    public TableDataInfo<AttrAttrgroupRelationVo> list(AttrAttrgroupRelationBo bo, PageQuery pageQuery) {
        return attrAttrgroupRelationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出属性&属性分组关联列表
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:export")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AttrAttrgroupRelationBo bo, HttpServletResponse response) {
        List<AttrAttrgroupRelationVo> list = attrAttrgroupRelationService.queryList(bo);
        ExcelUtil.exportExcel(list, "属性&属性分组关联", AttrAttrgroupRelationVo.class, response);
    }

    /**
     * 获取属性&属性分组关联详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:query")
    @GetMapping("/{id}")
    public R<AttrAttrgroupRelationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(attrAttrgroupRelationService.queryById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:add")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AttrAttrgroupRelationBo bo) {
        return toAjax(attrAttrgroupRelationService.insertByBo(bo));
    }

    /**
     * 修改属性&属性分组关联
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:edit")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AttrAttrgroupRelationBo bo) {
        return toAjax(attrAttrgroupRelationService.updateByBo(bo));
    }

    /**
     * 删除属性&属性分组关联
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pms:attrAttrgroupRelation:remove")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(attrAttrgroupRelationService.deleteWithValidByIds(List.of(ids), true));
    }
}
