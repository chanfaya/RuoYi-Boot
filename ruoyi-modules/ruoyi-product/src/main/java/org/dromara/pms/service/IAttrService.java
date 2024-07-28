package org.dromara.pms.service;

import org.dromara.pms.domain.vo.AttrVo;
import org.dromara.pms.domain.bo.AttrBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface IAttrService {

    /**
     * 查询商品属性
     *
     * @param attrId 主键
     * @return 商品属性
     */
    AttrVo queryById(Long attrId);

    /**
     * 分页查询商品属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性分页列表
     */
    TableDataInfo<AttrVo> queryPageList(AttrBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品属性列表
     *
     * @param bo 查询条件
     * @return 商品属性列表
     */
    List<AttrVo> queryList(AttrBo bo);

    /**
     * 新增商品属性
     *
     * @param bo 商品属性
     * @return 是否新增成功
     */
    Boolean insertByBo(AttrBo bo);

    /**
     * 修改商品属性
     *
     * @param bo 商品属性
     * @return 是否修改成功
     */
    Boolean updateByBo(AttrBo bo);

    /**
     * 校验并批量删除商品属性信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
