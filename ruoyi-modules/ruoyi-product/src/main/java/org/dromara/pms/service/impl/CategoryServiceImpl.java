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
import org.dromara.pms.domain.bo.CategoryBo;
import org.dromara.pms.domain.vo.CategoryVo;
import org.dromara.pms.domain.Category;
import org.dromara.pms.mapper.CategoryMapper;
import org.dromara.pms.service.ICategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品三级分类Service业务层处理
 *
 * @author chanfa
 * @date 2024-07-28
 */
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper baseMapper;

    /**
     * 查询商品三级分类
     *
     * @param catId 主键
     * @return 商品三级分类
     */
    @Override
    public CategoryVo queryById(Long catId){
        return baseMapper.selectVoById(catId);
    }

    /**
     * 分页查询商品三级分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品三级分类分页列表
     */
    @Override
    public TableDataInfo<CategoryVo> queryPageList(CategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        Page<CategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品三级分类列表
     *
     * @param bo 查询条件
     * @return 商品三级分类列表
     */
    @Override
    public List<CategoryVo> queryList(CategoryBo bo) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Category> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Category::getName, bo.getName());
        lqw.eq(bo.getParentCid() != null, Category::getParentCid, bo.getParentCid());
        lqw.eq(bo.getCatLevel() != null, Category::getCatLevel, bo.getCatLevel());
        lqw.eq(bo.getShowStatus() != null, Category::getShowStatus, bo.getShowStatus());
        lqw.eq(bo.getSort() != null, Category::getSort, bo.getSort());
        lqw.eq(StringUtils.isNotBlank(bo.getIcon()), Category::getIcon, bo.getIcon());
        lqw.eq(StringUtils.isNotBlank(bo.getProductUnit()), Category::getProductUnit, bo.getProductUnit());
        lqw.eq(bo.getProductCount() != null, Category::getProductCount, bo.getProductCount());
        return lqw;
    }

    /**
     * 新增商品三级分类
     *
     * @param bo 商品三级分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CategoryBo bo) {
        Category add = MapstructUtils.convert(bo, Category.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCatId(add.getCatId());
        }
        return flag;
    }

    /**
     * 修改商品三级分类
     *
     * @param bo 商品三级分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CategoryBo bo) {
        Category update = MapstructUtils.convert(bo, Category.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Category entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品三级分类信息
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
