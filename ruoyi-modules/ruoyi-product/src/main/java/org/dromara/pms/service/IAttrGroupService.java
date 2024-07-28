package org.dromara.pms.service;

import org.dromara.pms.domain.vo.AttrGroupVo;
import org.dromara.pms.domain.bo.AttrGroupBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 属性分组Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface IAttrGroupService {

    /**
     * 查询属性分组
     *
     * @param attrGroupId 主键
     * @return 属性分组
     */
    AttrGroupVo queryById(Long attrGroupId);

    /**
     * 分页查询属性分组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 属性分组分页列表
     */
    TableDataInfo<AttrGroupVo> queryPageList(AttrGroupBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的属性分组列表
     *
     * @param bo 查询条件
     * @return 属性分组列表
     */
    List<AttrGroupVo> queryList(AttrGroupBo bo);

    /**
     * 新增属性分组
     *
     * @param bo 属性分组
     * @return 是否新增成功
     */
    Boolean insertByBo(AttrGroupBo bo);

    /**
     * 修改属性分组
     *
     * @param bo 属性分组
     * @return 是否修改成功
     */
    Boolean updateByBo(AttrGroupBo bo);

    /**
     * 校验并批量删除属性分组信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
