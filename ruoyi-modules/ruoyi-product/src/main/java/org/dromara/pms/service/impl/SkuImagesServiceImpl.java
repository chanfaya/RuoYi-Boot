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
import org.dromara.pms.domain.bo.SkuImagesBo;
import org.dromara.pms.domain.vo.SkuImagesVo;
import org.dromara.pms.domain.SkuImages;
import org.dromara.pms.mapper.SkuImagesMapper;
import org.dromara.pms.service.ISkuImagesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * sku图片Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class SkuImagesServiceImpl implements ISkuImagesService {

    private final SkuImagesMapper baseMapper;

    /**
     * 查询sku图片
     *
     * @param id 主键
     * @return sku图片
     */
    @Override
    public SkuImagesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询sku图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return sku图片分页列表
     */
    @Override
    public TableDataInfo<SkuImagesVo> queryPageList(SkuImagesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SkuImages> lqw = buildQueryWrapper(bo);
        Page<SkuImagesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的sku图片列表
     *
     * @param bo 查询条件
     * @return sku图片列表
     */
    @Override
    public List<SkuImagesVo> queryList(SkuImagesBo bo) {
        LambdaQueryWrapper<SkuImages> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SkuImages> buildQueryWrapper(SkuImagesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SkuImages> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSkuId() != null, SkuImages::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getImgUrl()), SkuImages::getImgUrl, bo.getImgUrl());
        lqw.eq(bo.getImgSort() != null, SkuImages::getImgSort, bo.getImgSort());
        lqw.eq(bo.getDefaultImg() != null, SkuImages::getDefaultImg, bo.getDefaultImg());
        return lqw;
    }

    /**
     * 新增sku图片
     *
     * @param bo sku图片
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SkuImagesBo bo) {
        SkuImages add = MapstructUtils.convert(bo, SkuImages.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改sku图片
     *
     * @param bo sku图片
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SkuImagesBo bo) {
        SkuImages update = MapstructUtils.convert(bo, SkuImages.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SkuImages entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除sku图片信息
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
