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
import org.dromara.pms.domain.bo.ProductAttrValueBo;
import org.dromara.pms.domain.vo.ProductAttrValueVo;
import org.dromara.pms.domain.ProductAttrValue;
import org.dromara.pms.mapper.ProductAttrValueMapper;
import org.dromara.pms.service.IProductAttrValueService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * spu属性值Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class ProductAttrValueServiceImpl implements IProductAttrValueService {

    private final ProductAttrValueMapper baseMapper;

    /**
     * 查询spu属性值
     *
     * @param id 主键
     * @return spu属性值
     */
    @Override
    public ProductAttrValueVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询spu属性值列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return spu属性值分页列表
     */
    @Override
    public TableDataInfo<ProductAttrValueVo> queryPageList(ProductAttrValueBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAttrValue> lqw = buildQueryWrapper(bo);
        Page<ProductAttrValueVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的spu属性值列表
     *
     * @param bo 查询条件
     * @return spu属性值列表
     */
    @Override
    public List<ProductAttrValueVo> queryList(ProductAttrValueBo bo) {
        LambdaQueryWrapper<ProductAttrValue> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAttrValue> buildQueryWrapper(ProductAttrValueBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAttrValue> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSpuId() != null, ProductAttrValue::getSpuId, bo.getSpuId());
        lqw.eq(bo.getAttrId() != null, ProductAttrValue::getAttrId, bo.getAttrId());
        lqw.like(StringUtils.isNotBlank(bo.getAttrName()), ProductAttrValue::getAttrName, bo.getAttrName());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValue()), ProductAttrValue::getAttrValue, bo.getAttrValue());
        lqw.eq(bo.getAttrSort() != null, ProductAttrValue::getAttrSort, bo.getAttrSort());
        lqw.eq(bo.getQuickShow() != null, ProductAttrValue::getQuickShow, bo.getQuickShow());
        return lqw;
    }

    /**
     * 新增spu属性值
     *
     * @param bo spu属性值
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ProductAttrValueBo bo) {
        ProductAttrValue add = MapstructUtils.convert(bo, ProductAttrValue.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改spu属性值
     *
     * @param bo spu属性值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ProductAttrValueBo bo) {
        ProductAttrValue update = MapstructUtils.convert(bo, ProductAttrValue.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAttrValue entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除spu属性值信息
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
