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
import org.dromara.pms.domain.bo.AttrAttrgroupRelationBo;
import org.dromara.pms.domain.vo.AttrAttrgroupRelationVo;
import org.dromara.pms.domain.AttrAttrgroupRelation;
import org.dromara.pms.mapper.AttrAttrgroupRelationMapper;
import org.dromara.pms.service.IAttrAttrgroupRelationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 属性&属性分组关联Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class AttrAttrgroupRelationServiceImpl implements IAttrAttrgroupRelationService {

    private final AttrAttrgroupRelationMapper baseMapper;

    /**
     * 查询属性&属性分组关联
     *
     * @param id 主键
     * @return 属性&属性分组关联
     */
    @Override
    public AttrAttrgroupRelationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询属性&属性分组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 属性&属性分组关联分页列表
     */
    @Override
    public TableDataInfo<AttrAttrgroupRelationVo> queryPageList(AttrAttrgroupRelationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AttrAttrgroupRelation> lqw = buildQueryWrapper(bo);
        Page<AttrAttrgroupRelationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的属性&属性分组关联列表
     *
     * @param bo 查询条件
     * @return 属性&属性分组关联列表
     */
    @Override
    public List<AttrAttrgroupRelationVo> queryList(AttrAttrgroupRelationBo bo) {
        LambdaQueryWrapper<AttrAttrgroupRelation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AttrAttrgroupRelation> buildQueryWrapper(AttrAttrgroupRelationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AttrAttrgroupRelation> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAttrId() != null, AttrAttrgroupRelation::getAttrId, bo.getAttrId());
        lqw.eq(bo.getAttrGroupId() != null, AttrAttrgroupRelation::getAttrGroupId, bo.getAttrGroupId());
        lqw.eq(bo.getAttrSort() != null, AttrAttrgroupRelation::getAttrSort, bo.getAttrSort());
        return lqw;
    }

    /**
     * 新增属性&属性分组关联
     *
     * @param bo 属性&属性分组关联
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AttrAttrgroupRelationBo bo) {
        AttrAttrgroupRelation add = MapstructUtils.convert(bo, AttrAttrgroupRelation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改属性&属性分组关联
     *
     * @param bo 属性&属性分组关联
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AttrAttrgroupRelationBo bo) {
        AttrAttrgroupRelation update = MapstructUtils.convert(bo, AttrAttrgroupRelation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AttrAttrgroupRelation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除属性&属性分组关联信息
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
