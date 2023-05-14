<!-- 发布者信息详情弹窗 -->
<template>
            <a-form
                    ref="form3"
                    class="ele-form-detail"
                    :model="publisherModalApp.publisher"
                    :label-col="{md: {span: 2}, sm: {span: 4}, xs: {span: 6}}"
                    :wrapper-col="{md: {span: 22}, sm: {span: 20}, xs: {span: 18}}">
                <a-descriptions :title="publisherModalApp.title" bordered>
                                    <a-descriptions-item label="标识">
                        {{ publisherModalApp.publisher.Eid }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人">
                        {{ publisherModalApp.publisher.creatorName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="名称">
                        {{ publisherModalApp.publisher.name }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="联系电话">
                        {{ publisherModalApp.publisher.phone }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="地址">
                        {{ publisherModalApp.publisher.address }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="新闻条数">
                        {{ publisherModalApp.publisher.count }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="状态">
                        {{ publisherModalApp.publisher.status }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人ID">
                        {{ publisherModalApp.publisher.creatorId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="备注">
                        {{ publisherModalApp.publisher.remark }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建时间">
                        {{ publisherModalApp.publisher.createDatetime }}
                    </a-descriptions-item>
                                </a-descriptions>
            </a-form>
</template>

<script>
    import {defineComponent, reactive, onMounted, inject} from "vue";
    import {useRoute} from "vue-router"
    import {PublisherService} from "@/views/cms/publisher/publisher/publisherService";

    export default defineComponent({
        setup() {
            const route = useRoute();
            let routeId = route.params.id;
            let publisherListApp = inject('publisherListApp', reactive({}));
            const publisherModalApp = reactive({id: null, publisher: {}, display: false});

            onMounted(() => {
                    publisherModalApp.publisher = {};
                if (routeId) {
                        publisherModalApp.id = routeId;
                } else if (publisherListApp.viewModalShowing) {
                        publisherModalApp.id = publisherListApp.currentId;
                        publisherModalApp.display = true;
                }
                if (publisherModalApp.id) {
                    getInfo(publisherModalApp.id);
                }
            })

            const getInfo = (id) => {
                    PublisherService.findPublisher(id).then((res) => {
                            publisherModalApp.publisher = res.data;
                            publisherModalApp.title = "查看 " + publisherModalApp.publisher.name;
                })
            }

            return {
                    publisherModalApp,
                routeId,
                getInfo,
            }
        }
    })
</script>

<style scoped>
</style>

