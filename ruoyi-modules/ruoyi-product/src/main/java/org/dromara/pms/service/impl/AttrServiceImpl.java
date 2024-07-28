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
import org.dromara.pms.domain.bo.AttrBo;
import org.dromara.pms.domain.vo.AttrVo;
import org.dromara.pms.domain.Attr;
import org.dromara.pms.mapper.AttrMapper;
import org.dromara.pms.service.IAttrService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品属性Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class AttrServiceImpl implements IAttrService {

    private final AttrMapper baseMapper;

    /**
     * 查询商品属性
     *
     * @param attrId 主键
     * @return 商品属性
     */
    @Override
    public AttrVo queryById(Long attrId){
        return baseMapper.selectVoById(attrId);
    }

    /**
     * 分页查询商品属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性分页列表
     */
    @Override
    public TableDataInfo<AttrVo> queryPageList(AttrBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Attr> lqw = buildQueryWrapper(bo);
        Page<AttrVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品属性列表
     *
     * @param bo 查询条件
     * @return 商品属性列表
     */
    @Override
    public List<AttrVo> queryList(AttrBo bo) {
        LambdaQueryWrapper<Attr> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Attr> buildQueryWrapper(AttrBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Attr> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAttrName()), Attr::getAttrName, bo.getAttrName());
        lqw.eq(bo.getSearchType() != null, Attr::getSearchType, bo.getSearchType());
        lqw.eq(bo.getValueType() != null, Attr::getValueType, bo.getValueType());
        lqw.eq(StringUtils.isNotBlank(bo.getIcon()), Attr::getIcon, bo.getIcon());
        lqw.eq(StringUtils.isNotBlank(bo.getValueSelect()), Attr::getValueSelect, bo.getValueSelect());
        lqw.eq(bo.getAttrType() != null, Attr::getAttrType, bo.getAttrType());
        lqw.eq(bo.getEnable() != null, Attr::getEnable, bo.getEnable());
        lqw.eq(bo.getCatelogId() != null, Attr::getCatelogId, bo.getCatelogId());
        lqw.eq(bo.getShowDesc() != null, Attr::getShowDesc, bo.getShowDesc());
        return lqw;
    }

    /**
     * 新增商品属性
     *
     * @param bo 商品属性
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AttrBo bo) {
        Attr add = MapstructUtils.convert(bo, Attr.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAttrId(add.getAttrId());
        }
        return flag;
    }

    /**
     * 修改商品属性
     *
     * @param bo 商品属性
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AttrBo bo) {
        Attr update = MapstructUtils.convert(bo, Attr.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Attr entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品属性信息
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
