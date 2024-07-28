package org.dromara.pms.service;

import org.dromara.pms.domain.vo.CommentReplayVo;
import org.dromara.pms.domain.bo.CommentReplayBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品评价回复关系Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface ICommentReplayService {

    /**
     * 查询商品评价回复关系
     *
     * @param id 主键
     * @return 商品评价回复关系
     */
    CommentReplayVo queryById(Long id);

    /**
     * 分页查询商品评价回复关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品评价回复关系分页列表
     */
    TableDataInfo<CommentReplayVo> queryPageList(CommentReplayBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品评价回复关系列表
     *
     * @param bo 查询条件
     * @return 商品评价回复关系列表
     */
    List<CommentReplayVo> queryList(CommentReplayBo bo);

    /**
     * 新增商品评价回复关系
     *
     * @param bo 商品评价回复关系
     * @return 是否新增成功
     */
    Boolean insertByBo(CommentReplayBo bo);

    /**
     * 修改商品评价回复关系
     *
     * @param bo 商品评价回复关系
     * @return 是否修改成功
     */
    Boolean updateByBo(CommentReplayBo bo);

    /**
     * 校验并批量删除商品评价回复关系信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
