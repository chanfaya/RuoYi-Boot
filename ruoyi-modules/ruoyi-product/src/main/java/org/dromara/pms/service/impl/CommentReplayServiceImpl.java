package org.dromara.pms.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.pms.domain.bo.CommentReplayBo;
import org.dromara.pms.domain.vo.CommentReplayVo;
import org.dromara.pms.domain.CommentReplay;
import org.dromara.pms.mapper.CommentReplayMapper;
import org.dromara.pms.service.ICommentReplayService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品评价回复关系Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class CommentReplayServiceImpl implements ICommentReplayService {

    private final CommentReplayMapper baseMapper;

    /**
     * 查询商品评价回复关系
     *
     * @param id 主键
     * @return 商品评价回复关系
     */
    @Override
    public CommentReplayVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询商品评价回复关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品评价回复关系分页列表
     */
    @Override
    public TableDataInfo<CommentReplayVo> queryPageList(CommentReplayBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommentReplay> lqw = buildQueryWrapper(bo);
        Page<CommentReplayVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品评价回复关系列表
     *
     * @param bo 查询条件
     * @return 商品评价回复关系列表
     */
    @Override
    public List<CommentReplayVo> queryList(CommentReplayBo bo) {
        LambdaQueryWrapper<CommentReplay> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommentReplay> buildQueryWrapper(CommentReplayBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommentReplay> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommentId() != null, CommentReplay::getCommentId, bo.getCommentId());
        lqw.eq(bo.getReplyId() != null, CommentReplay::getReplyId, bo.getReplyId());
        return lqw;
    }

    /**
     * 新增商品评价回复关系
     *
     * @param bo 商品评价回复关系
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CommentReplayBo bo) {
        CommentReplay add = MapstructUtils.convert(bo, CommentReplay.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品评价回复关系
     *
     * @param bo 商品评价回复关系
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CommentReplayBo bo) {
        CommentReplay update = MapstructUtils.convert(bo, CommentReplay.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommentReplay entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品评价回复关系信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
