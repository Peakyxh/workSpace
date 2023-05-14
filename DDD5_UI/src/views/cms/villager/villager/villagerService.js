
import axios from 'axios';

/**
 * 村民信息
 */
export class VillagerService {
    // 分页查询列表
    static findVillagers (queryParams) {
        return axios({
            url: '/cms/villager/Villager/findVillagers',
            method: 'POST',
            data: queryParams
        });
    }

    //根据ID查询指定的村民信息
    static findVillager (villagerId) {
        const query = {
            villagerId
        }
        return axios({
            url: '/cms/villager/Villager/findVillager',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的村民信息(包含外键名称)
    static findVillagerForView (villagerId) {
        const query = {
            villagerId
        }
        return axios({
            url: '/cms/villager/Villager/findVillagerForView',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的村民信息(包含外键名称)
    static findVillagerForEdit (villagerId) {
        const query = {
            villagerId
        }
        return axios({
            url: '/cms/villager/Villager/findVillagerForEdit',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的村民信息(只提取ID 和 Name)
    static findVillagersWithIdNameById (villagerId) {
        const query = {
            villagerId
        }
        return axios({
            url: '/cms/villager/Villager/findVillagersWithIdNameById',
            method: 'POST',
            params: query,
        });
    }

    //根据名称查询村民信息集合(只提取ID 和 Name)
    static findVillagersWithIdNameByName (villagerName) {
        const query = {
            villagerName
        }
        return axios({
            url: '/cms/villager/Villager/findVillagersWithIdNameByName',
            method: 'POST',
            params: query,
        });
    }

    //新增保存村民信息
    static saveVillager (villager) {
        const query = {}
        return axios({
            url: '/cms/villager/Villager/saveVillager',
            method: 'POST',
            params: query,
            data: villager
        });
    }

    //修改保存村民信息
    static updateVillager (villager) {
        const query = {}
        return axios({
            url: '/cms/villager/Villager/updateVillager',
            method: 'POST',
            params: query,
            data: villager
        });
    }

    //根据ID查询指定的村民信息(只提取ID 和 Name)
    static deleteVillager (villagerId) {
        const query = {
            villagerId
        }
        return axios({
            url: '/cms/villager/Villager/deleteVillager',
            method: 'POST',
            params: query,
        });
    }
}
