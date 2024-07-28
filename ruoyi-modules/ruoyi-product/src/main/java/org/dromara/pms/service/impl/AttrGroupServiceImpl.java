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
import org.dromara.pms.domain.bo.AttrGroupBo;
import org.dromara.pms.domain.vo.AttrGroupVo;
import org.dromara.pms.domain.AttrGroup;
import org.dromara.pms.mapper.AttrGroupMapper;
import org.dromara.pms.service.IAttrGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 属性分组Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class AttrGroupServiceImpl implements IAttrGroupService {

    private final AttrGroupMapper baseMapper;

    /**
     * 查询属性分组
     *
     * @param attrGroupId 主键
     * @return 属性分组
     */
    @Override
    public AttrGroupVo queryById(Long attrGroupId){
        return baseMapper.selectVoById(attrGroupId);
    }

    /**
     * 分页查询属性分组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 属性分组分页列表
     */
    @Override
    public TableDataInfo<AttrGroupVo> queryPageList(AttrGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AttrGroup> lqw = buildQueryWrapper(bo);
        Page<AttrGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的属性分组列表
     *
     * @param bo 查询条件
     * @return 属性分组列表
     */
    @Override
    public List<AttrGroupVo> queryList(AttrGroupBo bo) {
        LambdaQueryWrapper<AttrGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AttrGroup> buildQueryWrapper(AttrGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AttrGroup> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAttrGroupName()), AttrGroup::getAttrGroupName, bo.getAttrGroupName());
        lqw.eq(bo.getSort() != null, AttrGroup::getSort, bo.getSort());
        lqw.eq(StringUtils.isNotBlank(bo.getDescript()), AttrGroup::getDescript, bo.getDescript());
        lqw.eq(StringUtils.isNotBlank(bo.getIcon()), AttrGroup::getIcon, bo.getIcon());
        lqw.eq(bo.getCatelogId() != null, AttrGroup::getCatelogId, bo.getCatelogId());
        return lqw;
    }

    /**
     * 新增属性分组
     *
     * @param bo 属性分组
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AttrGroupBo bo) {
        AttrGroup add = MapstructUtils.convert(bo, AttrGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAttrGroupId(add.getAttrGroupId());
        }
        return flag;
    }

    /**
     * 修改属性分组
     *
     * @param bo 属性分组
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AttrGroupBo bo) {
        AttrGroup update = MapstructUtils.convert(bo, AttrGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AttrGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除属性分组信息
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
