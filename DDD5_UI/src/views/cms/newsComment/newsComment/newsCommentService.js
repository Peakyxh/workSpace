
import axios from 'axios';

/**
 * 新闻评论
 */
export class NewsCommentService {
    // 分页查询列表
    static findNewsComments (queryParams) {
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsComments',
            method: 'POST',
            data: queryParams
        });
    }

    //根据ID查询指定的新闻评论
    static findNewsComment (newsCommentId) {
        const query = {
            newsCommentId
        }
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsComment',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻评论(包含外键名称)
    static findNewsCommentForView (newsCommentId) {
        const query = {
            newsCommentId
        }
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsCommentForView',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻评论(包含外键名称)
    static findNewsCommentForEdit (newsCommentId) {
        const query = {
            newsCommentId
        }
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsCommentForEdit',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻评论(只提取ID 和 Name)
    static findNewsCommentsWithIdNameById (newsCommentId) {
        const query = {
            newsCommentId
        }
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsCommentsWithIdNameById',
            method: 'POST',
            params: query,
        });
    }

    //根据名称查询新闻评论集合(只提取ID 和 Name)
    static findNewsCommentsWithIdNameByName (newsCommentName) {
        const query = {
            newsCommentName
        }
        return axios({
            url: '/cms/newsComment/NewsComment/findNewsCommentsWithIdNameByName',
            method: 'POST',
            params: query,
        });
    }

    //新增保存新闻评论
    static saveNewsComment (newsComment) {
        const query = {}
        return axios({
            url: '/cms/newsComment/NewsComment/saveNewsComment',
            method: 'POST',
            params: query,
            data: newsComment
        });
    }

    //修改保存新闻评论
    static updateNewsComment (newsComment) {
        const query = {}
        return axios({
            url: '/cms/newsComment/NewsComment/updateNewsComment',
            method: 'POST',
            params: query,
            data: newsComment
        });
    }

    //根据ID查询指定的新闻评论(只提取ID 和 Name)
    static deleteNewsComment (newsCommentId) {
        const query = {
            newsCommentId
        }
        return axios({
            url: '/cms/newsComment/NewsComment/deleteNewsComment',
            method: 'POST',
            params: query,
        });
    }
}
