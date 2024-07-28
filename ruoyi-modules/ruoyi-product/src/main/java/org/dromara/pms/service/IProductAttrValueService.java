package org.dromara.pms.service;

import org.dromara.pms.domain.vo.ProductAttrValueVo;
import org.dromara.pms.domain.bo.ProductAttrValueBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * spu属性值Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface IProductAttrValueService {

    /**
     * 查询spu属性值
     *
     * @param id 主键
     * @return spu属性值
     */
    ProductAttrValueVo queryById(Long id);

    /**
     * 分页查询spu属性值列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return spu属性值分页列表
     */
    TableDataInfo<ProductAttrValueVo> queryPageList(ProductAttrValueBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的spu属性值列表
     *
     * @param bo 查询条件
     * @return spu属性值列表
     */
    List<ProductAttrValueVo> queryList(ProductAttrValueBo bo);

    /**
     * 新增spu属性值
     *
     * @param bo spu属性值
     * @return 是否新增成功
     */
    Boolean insertByBo(ProductAttrValueBo bo);

    /**
     * 修改spu属性值
     *
     * @param bo spu属性值
     * @return 是否修改成功
     */
    Boolean updateByBo(ProductAttrValueBo bo);

    /**
     * 校验并批量删除spu属性值信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
