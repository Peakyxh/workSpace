<!-- 村民信息详情弹窗 -->
<template>
            <a-form
                    ref="form3"
                    class="ele-form-detail"
                    :model="villagerModalApp.villager"
                    :label-col="{md: {span: 2}, sm: {span: 4}, xs: {span: 6}}"
                    :wrapper-col="{md: {span: 22}, sm: {span: 20}, xs: {span: 18}}">
                <a-descriptions :title="villagerModalApp.title" bordered>
                                    <a-descriptions-item label="标识">
                        {{ villagerModalApp.villager.Eid }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人">
                        {{ villagerModalApp.villager.creatorName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="名称">
                        {{ villagerModalApp.villager.name }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="年龄">
                        {{ villagerModalApp.villager.age }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="性别">
                        {{ villagerModalApp.villager.gender }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="手机号">
                        {{ villagerModalApp.villager.phoneNumber }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="地址">
                        {{ villagerModalApp.villager.address }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="状态">
                        {{ villagerModalApp.villager.status }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人ID">
                        {{ villagerModalApp.villager.creatorId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="备注">
                        {{ villagerModalApp.villager.remark }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建时间">
                        {{ villagerModalApp.villager.createDatetime }}
                    </a-descriptions-item>
                                </a-descriptions>
            </a-form>
</template>

<script>
    import {defineComponent, reactive, onMounted, inject} from "vue";
    import {useRoute} from "vue-router"
    import {VillagerService} from "@/views/cms/villager/villager/villagerService";

    export default defineComponent({
        setup() {
            const route = useRoute();
            let routeId = route.params.id;
            let villagerListApp = inject('villagerListApp', reactive({}));
            const villagerModalApp = reactive({id: null, villager: {}, display: false});

            onMounted(() => {
                    villagerModalApp.villager = {};
                if (routeId) {
                        villagerModalApp.id = routeId;
                } else if (villagerListApp.viewModalShowing) {
                        villagerModalApp.id = villagerListApp.currentId;
                        villagerModalApp.display = true;
                }
                if (villagerModalApp.id) {
                    getInfo(villagerModalApp.id);
                }
            })

            const getInfo = (id) => {
                    VillagerService.findVillager(id).then((res) => {
                            villagerModalApp.villager = res.data;
                            villagerModalApp.title = "查看 " + villagerModalApp.villager.name;
                })
            }

            return {
                    villagerModalApp,
                routeId,
                getInfo,
            }
        }
    })
</script>

<style scoped>
</style>

