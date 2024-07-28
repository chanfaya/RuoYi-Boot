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
import org.dromara.pms.domain.bo.BrandBo;
import org.dromara.pms.domain.vo.BrandVo;
import org.dromara.pms.domain.Brand;
import org.dromara.pms.mapper.BrandMapper;
import org.dromara.pms.service.IBrandService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 品牌Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements IBrandService {

    private final BrandMapper baseMapper;

    /**
     * 查询品牌
     *
     * @param brandId 主键
     * @return 品牌
     */
    @Override
    public BrandVo queryById(Long brandId){
        return baseMapper.selectVoById(brandId);
    }

    /**
     * 分页查询品牌列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 品牌分页列表
     */
    @Override
    public TableDataInfo<BrandVo> queryPageList(BrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        Page<BrandVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的品牌列表
     *
     * @param bo 查询条件
     * @return 品牌列表
     */
    @Override
    public List<BrandVo> queryList(BrandBo bo) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Brand> buildQueryWrapper(BrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Brand> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Brand::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getLogo()), Brand::getLogo, bo.getLogo());
        lqw.eq(StringUtils.isNotBlank(bo.getDescript()), Brand::getDescript, bo.getDescript());
        lqw.eq(bo.getShowStatus() != null, Brand::getShowStatus, bo.getShowStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getFirstLetter()), Brand::getFirstLetter, bo.getFirstLetter());
        lqw.eq(bo.getSort() != null, Brand::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增品牌
     *
     * @param bo 品牌
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BrandBo bo) {
        Brand add = MapstructUtils.convert(bo, Brand.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBrandId(add.getBrandId());
        }
        return flag;
    }

    /**
     * 修改品牌
     *
     * @param bo 品牌
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BrandBo bo) {
        Brand update = MapstructUtils.convert(bo, Brand.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Brand entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除品牌信息
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
