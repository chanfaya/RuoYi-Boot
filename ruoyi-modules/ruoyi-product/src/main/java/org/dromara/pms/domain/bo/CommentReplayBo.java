package org.dromara.pms.domain.bo;

import org.dromara.pms.domain.CommentReplay;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品评价回复关系业务对象 pms_comment_replay
 *
 * @author chanfa
 * @date 2024-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CommentReplay.class, reverseConvertGenerate = false)
public class CommentReplayBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 评论id
     */
    @NotNull(message = "评论id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commentId;

    /**
     * 回复id
     */
    @NotNull(message = "回复id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long replyId;


}
