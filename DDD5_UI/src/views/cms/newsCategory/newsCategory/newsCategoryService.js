
import axios from 'axios';

/**
 * 新闻类别
 */
export class NewsCategoryService {
    // 分页查询列表
    static findNewsCategorys (queryParams) {
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategorys',
            method: 'POST',
            data: queryParams
        });
    }

    //根据ID查询指定的新闻类别
    static findNewsCategory (newsCategoryId) {
        const query = {
            newsCategoryId
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategory',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻类别(包含外键名称)
    static findNewsCategoryForView (newsCategoryId) {
        const query = {
            newsCategoryId
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategoryForView',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻类别(包含外键名称)
    static findNewsCategoryForEdit (newsCategoryId) {
        const query = {
            newsCategoryId
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategoryForEdit',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的新闻类别(只提取ID 和 Name)
    static findNewsCategorysWithIdNameById (newsCategoryId) {
        const query = {
            newsCategoryId
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategorysWithIdNameById',
            method: 'POST',
            params: query,
        });
    }

    //根据名称查询新闻类别集合(只提取ID 和 Name)
    static findNewsCategorysWithIdNameByName (newsCategoryName) {
        const query = {
            newsCategoryName
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/findNewsCategorysWithIdNameByName',
            method: 'POST',
            params: query,
        });
    }

    //新增保存新闻类别
    static saveNewsCategory (newsCategory) {
        const query = {}
        return axios({
            url: '/cms/newsCategory/NewsCategory/saveNewsCategory',
            method: 'POST',
            params: query,
            data: newsCategory
        });
    }

    //修改保存新闻类别
    static updateNewsCategory (newsCategory) {
        const query = {}
        return axios({
            url: '/cms/newsCategory/NewsCategory/updateNewsCategory',
            method: 'POST',
            params: query,
            data: newsCategory
        });
    }

    //根据ID查询指定的新闻类别(只提取ID 和 Name)
    static deleteNewsCategory (newsCategoryId) {
        const query = {
            newsCategoryId
        }
        return axios({
            url: '/cms/newsCategory/NewsCategory/deleteNewsCategory',
            method: 'POST',
            params: query,
        });
    }
}
