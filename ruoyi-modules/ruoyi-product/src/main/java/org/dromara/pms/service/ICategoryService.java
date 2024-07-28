package org.dromara.pms.service;

import org.dromara.pms.domain.vo.CategoryVo;
import org.dromara.pms.domain.bo.CategoryBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品三级分类Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface ICategoryService {

    /**
     * 查询商品三级分类
     *
     * @param catId 主键
     * @return 商品三级分类
     */
    CategoryVo queryById(Long catId);

    /**
     * 分页查询商品三级分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品三级分类分页列表
     */
    TableDataInfo<CategoryVo> queryPageList(CategoryBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品三级分类列表
     *
     * @param bo 查询条件
     * @return 商品三级分类列表
     */
    List<CategoryVo> queryList(CategoryBo bo);

    /**
     * 新增商品三级分类
     *
     * @param bo 商品三级分类
     * @return 是否新增成功
     */
    Boolean insertByBo(CategoryBo bo);

    /**
     * 修改商品三级分类
     *
     * @param bo 商品三级分类
     * @return 是否修改成功
     */
    Boolean updateByBo(CategoryBo bo);

    /**
     * 校验并批量删除商品三级分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
