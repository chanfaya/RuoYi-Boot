package org.dromara.pms.service;

import org.dromara.pms.domain.vo.CategoryBrandRelationVo;
import org.dromara.pms.domain.bo.CategoryBrandRelationBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 品牌分类关联Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface ICategoryBrandRelationService {

    /**
     * 查询品牌分类关联
     *
     * @param id 主键
     * @return 品牌分类关联
     */
    CategoryBrandRelationVo queryById(Long id);

    /**
     * 分页查询品牌分类关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 品牌分类关联分页列表
     */
    TableDataInfo<CategoryBrandRelationVo> queryPageList(CategoryBrandRelationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的品牌分类关联列表
     *
     * @param bo 查询条件
     * @return 品牌分类关联列表
     */
    List<CategoryBrandRelationVo> queryList(CategoryBrandRelationBo bo);

    /**
     * 新增品牌分类关联
     *
     * @param bo 品牌分类关联
     * @return 是否新增成功
     */
    Boolean insertByBo(CategoryBrandRelationBo bo);

    /**
     * 修改品牌分类关联
     *
     * @param bo 品牌分类关联
     * @return 是否修改成功
     */
    Boolean updateByBo(CategoryBrandRelationBo bo);

    /**
     * 校验并批量删除品牌分类关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
