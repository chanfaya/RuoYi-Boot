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
import org.dromara.pms.domain.vo.CommentReplayVo;
import org.dromara.pms.domain.bo.CommentReplayBo;
import org.dromara.pms.service.ICommentReplayService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品评价回复关系
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pms/commentReplay")
public class CommentReplayController extends BaseController {

    private final ICommentReplayService commentReplayService;

    /**
     * 查询商品评价回复关系列表
     */
    @SaCheckPermission("pms:commentReplay:list")
    @GetMapping("/list")
    public TableDataInfo<CommentReplayVo> list(CommentReplayBo bo, PageQuery pageQuery) {
        return commentReplayService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品评价回复关系列表
     */
    @SaCheckPermission("pms:commentReplay:export")
    @Log(title = "商品评价回复关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommentReplayBo bo, HttpServletResponse response) {
        List<CommentReplayVo> list = commentReplayService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品评价回复关系", CommentReplayVo.class, response);
    }

    /**
     * 获取商品评价回复关系详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pms:commentReplay:query")
    @GetMapping("/{id}")
    public R<CommentReplayVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(commentReplayService.queryById(id));
    }

    /**
     * 新增商品评价回复关系
     */
    @SaCheckPermission("pms:commentReplay:add")
    @Log(title = "商品评价回复关系", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommentReplayBo bo) {
        return toAjax(commentReplayService.insertByBo(bo));
    }

    /**
     * 修改商品评价回复关系
     */
    @SaCheckPermission("pms:commentReplay:edit")
    @Log(title = "商品评价回复关系", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommentReplayBo bo) {
        return toAjax(commentReplayService.updateByBo(bo));
    }

    /**
     * 删除商品评价回复关系
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pms:commentReplay:remove")
    @Log(title = "商品评价回复关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(commentReplayService.deleteWithValidByIds(List.of(ids), true));
    }
}
