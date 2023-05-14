<!-- 新闻评论详情弹窗 -->
<template>
            <a-form
                    ref="form3"
                    class="ele-form-detail"
                    :model="newsCommentModalApp.newsComment"
                    :label-col="{md: {span: 2}, sm: {span: 4}, xs: {span: 6}}"
                    :wrapper-col="{md: {span: 22}, sm: {span: 20}, xs: {span: 18}}">
                <a-descriptions :title="newsCommentModalApp.title" bordered>
                                    <a-descriptions-item label="标识">
                        {{ newsCommentModalApp.newsComment.Eid }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人">
                        {{ newsCommentModalApp.newsComment.creatorName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="名称">
                        {{ newsCommentModalApp.newsComment.name }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="内容">
                        {{ newsCommentModalApp.newsComment.content }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="评论的文章id">
                        {{ newsCommentModalApp.newsComment.articleId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建者的id">
                        {{ newsCommentModalApp.newsComment.createIdId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="父评论id">
                        {{ newsCommentModalApp.newsComment.parentId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="状态">
                        {{ newsCommentModalApp.newsComment.status }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人ID">
                        {{ newsCommentModalApp.newsComment.creatorId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="备注">
                        {{ newsCommentModalApp.newsComment.remark }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建时间">
                        {{ newsCommentModalApp.newsComment.createDatetime }}
                    </a-descriptions-item>
                                </a-descriptions>
            </a-form>
</template>

<script>
    import {defineComponent, reactive, onMounted, inject} from "vue";
    import {useRoute} from "vue-router"
    import {NewsCommentService} from "@/views/cms/newsComment/newsComment/newsCommentService";

    export default defineComponent({
        setup() {
            const route = useRoute();
            let routeId = route.params.id;
            let newsCommentListApp = inject('newsCommentListApp', reactive({}));
            const newsCommentModalApp = reactive({id: null, newsComment: {}, display: false});

            onMounted(() => {
                    newsCommentModalApp.newsComment = {};
                if (routeId) {
                        newsCommentModalApp.id = routeId;
                } else if (newsCommentListApp.viewModalShowing) {
                        newsCommentModalApp.id = newsCommentListApp.currentId;
                        newsCommentModalApp.display = true;
                }
                if (newsCommentModalApp.id) {
                    getInfo(newsCommentModalApp.id);
                }
            })

            const getInfo = (id) => {
                    NewsCommentService.findNewsComment(id).then((res) => {
                            newsCommentModalApp.newsComment = res.data;
                            newsCommentModalApp.title = "查看 " + newsCommentModalApp.newsComment.name;
                })
            }

            return {
                    newsCommentModalApp,
                routeId,
                getInfo,
            }
        }
    })
</script>

<style scoped>
</style>

