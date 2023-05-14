package com.mt.cms.dto.newsComment;


import com.mt.cms.entity.newsComment.NewsComment;

import java.util.List;
                        import com.mt.common.system.entity.User;
                            public class NewsCommentEditDto{

    private NewsComment newsComment;


                                                                            //外键实体是：User
    private List<User> createIdUsers;
                                                                                    

    public  NewsComment getNewsComment()
    {
        return this.newsComment;
    }
    public  void setNewsComment(NewsComment newsComment)
    {
        this.newsComment = newsComment;
    }


                                                                            public List<User> getCreateIdUsers()
    {
        return this.createIdUsers;
    }
    public void setCreateIdUsers(List<User> createIdUsers)
    {
        this.createIdUsers = createIdUsers;
    }
                                                                                    }
