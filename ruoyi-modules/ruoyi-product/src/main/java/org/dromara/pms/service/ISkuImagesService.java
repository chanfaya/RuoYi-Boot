package org.dromara.pms.service;

import org.dromara.pms.domain.vo.SkuImagesVo;
import org.dromara.pms.domain.bo.SkuImagesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * sku图片Service接口
 *
 * @author chanfa
 * @date 2024-07-28
 */
public interface ISkuImagesService {

    /**
     * 查询sku图片
     *
     * @param id 主键
     * @return sku图片
     */
    SkuImagesVo queryById(Long id);

    /**
     * 分页查询sku图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return sku图片分页列表
     */
    TableDataInfo<SkuImagesVo> queryPageList(SkuImagesBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的sku图片列表
     *
     * @param bo 查询条件
     * @return sku图片列表
     */
    List<SkuImagesVo> queryList(SkuImagesBo bo);

    /**
     * 新增sku图片
     *
     * @param bo sku图片
     * @return 是否新增成功
     */
    Boolean insertByBo(SkuImagesBo bo);

    /**
     * 修改sku图片
     *
     * @param bo sku图片
     * @return 是否修改成功
     */
    Boolean updateByBo(SkuImagesBo bo);

    /**
     * 校验并批量删除sku图片信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
