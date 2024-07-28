package org.dromara.pms.service;

import org.dromara.pms.domain.vo.AttrAttrgroupRelationVo;
import org.dromara.pms.domain.bo.AttrAttrgroupRelationBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 属性&属性分组关联Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface IAttrAttrgroupRelationService {

    /**
     * 查询属性&属性分组关联
     *
     * @param id 主键
     * @return 属性&属性分组关联
     */
    AttrAttrgroupRelationVo queryById(Long id);

    /**
     * 分页查询属性&属性分组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 属性&属性分组关联分页列表
     */
    TableDataInfo<AttrAttrgroupRelationVo> queryPageList(AttrAttrgroupRelationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的属性&属性分组关联列表
     *
     * @param bo 查询条件
     * @return 属性&属性分组关联列表
     */
    List<AttrAttrgroupRelationVo> queryList(AttrAttrgroupRelationBo bo);

    /**
     * 新增属性&属性分组关联
     *
     * @param bo 属性&属性分组关联
     * @return 是否新增成功
     */
    Boolean insertByBo(AttrAttrgroupRelationBo bo);

    /**
     * 修改属性&属性分组关联
     *
     * @param bo 属性&属性分组关联
     * @return 是否修改成功
     */
    Boolean updateByBo(AttrAttrgroupRelationBo bo);

    /**
     * 校验并批量删除属性&属性分组关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
