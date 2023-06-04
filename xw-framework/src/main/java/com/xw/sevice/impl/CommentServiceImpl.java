package com.xw.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Comment;
import com.xw.domain.vo.CommentVo;
import com.xw.domain.vo.PageVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.CommentMapper;
import com.xw.sevice.CommentService;
import com.xw.sevice.UserService;
import com.xw.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-05-31 18:21:31
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Override
    public ResponseResult commentList(String commentType,
                                      Long articleId,
                                      Integer pageNum,
                                      Integer pageSize) {
        //查询文章的根评论
        //对articleId进行判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //只有是评论接口的时候才会需要根据文章id查询
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),
                Comment::getArticleId,articleId);
        //根评论的rootId==-1
        queryWrapper.eq(Comment::getRootId,-1);
        //评论类型为需要的类型
        queryWrapper.eq(Comment::getType,commentType);
        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page = page(page, queryWrapper);
        List<Comment> comments = page.getRecords();
        //vo转换
        List<CommentVo> commentVoList = toCommentVoList(comments);

        //查询所有根评论对应的子评论集合并且复制给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> list = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(list);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    //根据根评论的Id查询所对应的子评论集合
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        //转换成为vo类
        List<CommentVo> commentVoList = toCommentVoList(list);
        return commentVoList;
    }


    //数据转换    commentList====>> commentVoList
    private List<CommentVo> toCommentVoList (List<Comment> list ){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //处理其他属性
        //遍历并查询封装返回
        for (CommentVo commentVo : commentVos) {
            //通过creatyBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            //设置评论者的昵称
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId()!=-1){
                //被评论的用户的昵称
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        //mp设置了自动填充字段，配置了会自动更新字段
        save(comment);
        return ResponseResult.okResult();
    }

}

