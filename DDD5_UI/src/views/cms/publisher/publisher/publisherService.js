
import axios from 'axios';

/**
 * 发布者信息
 */
export class PublisherService {
    // 分页查询列表
    static findPublishers (queryParams) {
        return axios({
            url: '/cms/publisher/Publisher/findPublishers',
            method: 'POST',
            data: queryParams
        });
    }

    //根据ID查询指定的发布者信息
    static findPublisher (publisherId) {
        const query = {
            publisherId
        }
        return axios({
            url: '/cms/publisher/Publisher/findPublisher',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的发布者信息(包含外键名称)
    static findPublisherForView (publisherId) {
        const query = {
            publisherId
        }
        return axios({
            url: '/cms/publisher/Publisher/findPublisherForView',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的发布者信息(包含外键名称)
    static findPublisherForEdit (publisherId) {
        const query = {
            publisherId
        }
        return axios({
            url: '/cms/publisher/Publisher/findPublisherForEdit',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的发布者信息(只提取ID 和 Name)
    static findPublishersWithIdNameById (publisherId) {
        const query = {
            publisherId
        }
        return axios({
            url: '/cms/publisher/Publisher/findPublishersWithIdNameById',
            method: 'POST',
            params: query,
        });
    }

    //根据名称查询发布者信息集合(只提取ID 和 Name)
    static findPublishersWithIdNameByName (publisherName) {
        const query = {
            publisherName
        }
        return axios({
            url: '/cms/publisher/Publisher/findPublishersWithIdNameByName',
            method: 'POST',
            params: query,
        });
    }

    //新增保存发布者信息
    static savePublisher (publisher) {
        const query = {}
        return axios({
            url: '/cms/publisher/Publisher/savePublisher',
            method: 'POST',
            params: query,
            data: publisher
        });
    }

    //修改保存发布者信息
    static updatePublisher (publisher) {
        const query = {}
        return axios({
            url: '/cms/publisher/Publisher/updatePublisher',
            method: 'POST',
            params: query,
            data: publisher
        });
    }

    //根据ID查询指定的发布者信息(只提取ID 和 Name)
    static deletePublisher (publisherId) {
        const query = {
            publisherId
        }
        return axios({
            url: '/cms/publisher/Publisher/deletePublisher',
            method: 'POST',
            params: query,
        });
    }
}
