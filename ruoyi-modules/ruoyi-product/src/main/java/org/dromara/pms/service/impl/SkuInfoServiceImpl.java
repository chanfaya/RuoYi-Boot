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
import org.dromara.pms.domain.bo.SkuInfoBo;
import org.dromara.pms.domain.vo.SkuInfoVo;
import org.dromara.pms.domain.SkuInfo;
import org.dromara.pms.mapper.SkuInfoMapper;
import org.dromara.pms.service.ISkuInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * sku信息Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class SkuInfoServiceImpl implements ISkuInfoService {

    private final SkuInfoMapper baseMapper;

    /**
     * 查询sku信息
     *
     * @param skuId 主键
     * @return sku信息
     */
    @Override
    public SkuInfoVo queryById(Long skuId){
        return baseMapper.selectVoById(skuId);
    }

    /**
     * 分页查询sku信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return sku信息分页列表
     */
    @Override
    public TableDataInfo<SkuInfoVo> queryPageList(SkuInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SkuInfo> lqw = buildQueryWrapper(bo);
        Page<SkuInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的sku信息列表
     *
     * @param bo 查询条件
     * @return sku信息列表
     */
    @Override
    public List<SkuInfoVo> queryList(SkuInfoBo bo) {
        LambdaQueryWrapper<SkuInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SkuInfo> buildQueryWrapper(SkuInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SkuInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSpuId() != null, SkuInfo::getSpuId, bo.getSpuId());
        lqw.like(StringUtils.isNotBlank(bo.getSkuName()), SkuInfo::getSkuName, bo.getSkuName());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuDesc()), SkuInfo::getSkuDesc, bo.getSkuDesc());
        lqw.eq(bo.getCatalogId() != null, SkuInfo::getCatalogId, bo.getCatalogId());
        lqw.eq(bo.getBrandId() != null, SkuInfo::getBrandId, bo.getBrandId());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuDefaultImg()), SkuInfo::getSkuDefaultImg, bo.getSkuDefaultImg());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuTitle()), SkuInfo::getSkuTitle, bo.getSkuTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuSubtitle()), SkuInfo::getSkuSubtitle, bo.getSkuSubtitle());
        lqw.eq(bo.getPrice() != null, SkuInfo::getPrice, bo.getPrice());
        lqw.eq(bo.getSaleCount() != null, SkuInfo::getSaleCount, bo.getSaleCount());
        return lqw;
    }

    /**
     * 新增sku信息
     *
     * @param bo sku信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SkuInfoBo bo) {
        SkuInfo add = MapstructUtils.convert(bo, SkuInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSkuId(add.getSkuId());
        }
        return flag;
    }

    /**
     * 修改sku信息
     *
     * @param bo sku信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SkuInfoBo bo) {
        SkuInfo update = MapstructUtils.convert(bo, SkuInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SkuInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除sku信息信息
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
