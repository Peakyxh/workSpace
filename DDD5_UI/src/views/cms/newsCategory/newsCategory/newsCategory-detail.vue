<!-- 新闻类别详情弹窗 -->
<template>
            <a-form
                    ref="form3"
                    class="ele-form-detail"
                    :model="newsCategoryModalApp.newsCategory"
                    :label-col="{md: {span: 2}, sm: {span: 4}, xs: {span: 6}}"
                    :wrapper-col="{md: {span: 22}, sm: {span: 20}, xs: {span: 18}}">
                <a-descriptions :title="newsCategoryModalApp.title" bordered>
                                    <a-descriptions-item label="标识">
                        {{ newsCategoryModalApp.newsCategory.Eid }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人">
                        {{ newsCategoryModalApp.newsCategory.creatorName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="名称">
                        {{ newsCategoryModalApp.newsCategory.name }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="状态">
                        {{ newsCategoryModalApp.newsCategory.status }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人ID">
                        {{ newsCategoryModalApp.newsCategory.creatorId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="备注">
                        {{ newsCategoryModalApp.newsCategory.remark }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建时间">
                        {{ newsCategoryModalApp.newsCategory.createDatetime }}
                    </a-descriptions-item>
                                </a-descriptions>
            </a-form>
</template>

<script>
    import {defineComponent, reactive, onMounted, inject} from "vue";
    import {useRoute} from "vue-router"
    import {NewsCategoryService} from "@/views/cms/newsCategory/newsCategory/newsCategoryService";

    export default defineComponent({
        setup() {
            const route = useRoute();
            let routeId = route.params.id;
            let newsCategoryListApp = inject('newsCategoryListApp', reactive({}));
            const newsCategoryModalApp = reactive({id: null, newsCategory: {}, display: false});

            onMounted(() => {
                    newsCategoryModalApp.newsCategory = {};
                if (routeId) {
                        newsCategoryModalApp.id = routeId;
                } else if (newsCategoryListApp.viewModalShowing) {
                        newsCategoryModalApp.id = newsCategoryListApp.currentId;
                        newsCategoryModalApp.display = true;
                }
                if (newsCategoryModalApp.id) {
                    getInfo(newsCategoryModalApp.id);
                }
            })

            const getInfo = (id) => {
                    NewsCategoryService.findNewsCategory(id).then((res) => {
                            newsCategoryModalApp.newsCategory = res.data;
                            newsCategoryModalApp.title = "查看 " + newsCategoryModalApp.newsCategory.name;
                })
            }

            return {
                    newsCategoryModalApp,
                routeId,
                getInfo,
            }
        }
    })
</script>

<style scoped>
</style>

