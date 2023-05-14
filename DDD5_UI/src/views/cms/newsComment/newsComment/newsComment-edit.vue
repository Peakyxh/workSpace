
<!-- 新闻评论编辑弹窗 -->
<template>
    <div class="ele-body">
        <a-card :title="newsCommentModalApp.title" :bordered="false">
            <a-form
                    ref="form2"
                    :model="newsCommentModalApp.newsComment"
                    :rules="newsCommentModalApp.rules"
                    :label-col="{md: {span: 7}, sm: {span: 24}}"
                    :wrapper-col="{md: {span: 17}, sm: {span: 24}}">
                <a-row :gutter="16">
                                                                                                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="内容:" name="content">
                                                                                    <a-input
                                                    v-model:value="newsCommentModalApp.newsComment.content"
                                                    placeholder="请输入内容"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="评论的文章id:" name="articleId">
                                                                                    <a-input-number
                                                    v-model:value="newsCommentModalApp.newsComment.articleId"
                                                    placeholder="请输入评论的文章id"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="创建者的id:" name="createIdId">
                                                                                    <m-entity-select
                                                                                                    :default-value="newsCommentModalApp.newsComment.createIdName"
                                                    v-model:value="newsCommentModalApp.newsComment.createIdId"
                                                    placeholder="请选择创建者的id"
                                                    module="newsComment"
                                                    entity="User"
                                                    class="ele-fluid"
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="父评论id:" name="parentId">
                                                                                    <a-input-number
                                                    v-model:value="newsCommentModalApp.newsComment.parentId"
                                                    placeholder="请输入父评论id"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                                                                                                                        
                    <a-col :md="12" :sm="24" :xs="24">
                        <a-form-item :wrapper-col="{md: {offset: 6}}" style="margin-bottom: -20px">
                            <!--class="ele-pull-right"-->
                            <div v-if="newsCommentModalApp.display">
                                <teleport to="#newsCommentEditModalFooter">
                                    <div>
                                        <a-space size="middle">
                                            <a-button @click="newsCommentListApp.editModalShowing=false">取消
                                            </a-button>
                                            <a-button
                                                    type="primary"
                                                    @click="onSubmit(newsCommentModalApp.newsComment)"
                                                    :loading="loading">提交
                                            </a-button>
                                            <!--<a-button v-if="!eid"
                                                      type="dashed"
                                                      @click="continueSubmit"
                                                      :loading="loading">继续提交
                                            </a-button>-->
                                        </a-space>
                                    </div>
                                </teleport>
                            </div>
                            <div v-if="!newsCommentModalApp.display">
                                <a-space size="middle">
                                    <a-button @click="onBack()">返回列表</a-button>
                                    <a-button
                                            type="primary"
                                            @click="onSubmit(newsCommentModalApp.newsComment)"
                                            :loading="loading">提交
                                    </a-button>
                                    <!--<a-button v-if="!eid"
                                              type="dashed"
                                              @click="continueSubmit"
                                              :loading="loading">继续提交
                                    </a-button>-->
                                </a-space>
                            </div>

                        </a-form-item>
                    </a-col>
                </a-row>
            </a-form>
        </a-card>

    </div>

</template>

<script>
    import {defineComponent, inject, reactive, onMounted} from 'vue'
    import {useRoute, useRouter} from "vue-router"
    import {NewsCommentService} from "@/views/cms/newsComment/newsComment/newsCommentService";
    import {VXETable} from 'vxe-table'
    import {useStore} from "vuex";
    import regions from 'ele-admin-pro/packages/regions.js';

                                                                                                                                                                                                                                                        
                                                        import MEntitySelect from "@/components/MEntity/entitySelect";
        
    export default defineComponent({
        components: {
                                                                                        MEntitySelect,
                    },
        setup() {
            // 省市区数据
            const cityData = regions
            const route = useRoute();
            const router = useRouter();
            const store = useStore();
            let routeId = route.params.id;
            let newsCommentListApp = inject('newsCommentListApp', reactive({}));
            const newsCommentModalApp = reactive({
                id: null,
                    newsComment: {},
                isEdit: false,
                display: false
            });
                newsCommentModalApp.rules = {
                                                                                                                                                                            content: [
                                                                                                                {
                            required:                                 true ,
                                type: 'string',
                                message: '请输入内容',
                                trigger: 'blur'
                            }
                        ],
                                                                                    articleId: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入评论的文章id',
                                trigger: 'blur'
                            }
                        ],
                                                                                    createIdId: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入创建者的id',
                                trigger: 'blur'
                            }
                        ],
                                                                                    createIdName: [
                                                                                    {
                            required:  false ,
                                type: 'string',
                                message: '请输入创建者的id',
                                trigger: 'blur'
                            }
                        ],
                                                                                    parentId: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入父评论id',
                                trigger: 'blur'
                            }
                        ],
                                                                                                                                                                                                }

            onMounted(() => {
                //清理数据
                    newsCommentModalApp.newsComment = reactive({});
                if (routeId) {//路由
                        newsCommentModalApp.id = routeId;
                        newsCommentModalApp.display = false;
                    findNewsComment(newsCommentModalApp.id);
                } else if (newsCommentListApp.editModalForEdit) {//修改弹窗
                        newsCommentModalApp.id = newsCommentListApp.currentId;
                        newsCommentModalApp.display = true;
                } else if (!newsCommentListApp.editModalForEdit && newsCommentListApp.addModalForEdit) {//新增弹窗
                        newsCommentModalApp.display = true;
                } else {
                        newsCommentModalApp.title = "新增学生";
                }
                if (newsCommentModalApp.id) findNewsComment(newsCommentModalApp.id);
            })

            const findNewsComment = (id) => {
                    NewsCommentService.findNewsCommentForView(id).then((res) => {
                        newsCommentModalApp.newsComment = res.data;
                    //TODO:为编辑准备.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                newsCommentModalApp.title = "编辑学生 " + newsCommentModalApp.newsComment.name;
                }).catch(error => {
                    VXETable.modal.message({content: `查找学生出错，原因是：${error.message}`, status: 'error'});
                })
            }

            /*TODO:提交 新增&编辑*/
            const onSubmit = (data) => {
                // debugger;
                //当为数组时用逗号连接
                                                                                                                                                                                                                                                                                                                                                                                                                                            if (data.eid) {
                    //修改
                        NewsCommentService.updateNewsComment(data).then((res) => {
                        console.log(res);
                        VXETable.modal.message({content: '操作成功', status: 'success'});
                        setTimeout(onBack, 3000);
                    }).catch(error => {
                        VXETable.modal.message({content: `系统错误，原因是：${error.message}`, status: 'error'});
                    })
                } else {
                    //新增
                        NewsCommentService.saveNewsComment(data).then((res) => {
                        console.log(res);
                        VXETable.modal.message({content: '操作成功', status: 'success'});
                        setTimeout(onBack, 3000);
                    }).catch(error => {
                        VXETable.modal.message({content: `系统错误，原因是：${error.message}`, status: 'error'});
                    })
                }
            }

            /*TODO:继续提交*/
            const continueSubmit = () => {
                VXETable.modal.message({content: 'error 提示', status: 'error'});
            }

            /*TODO:返回列表*/
            const onBack = () => {
                router.push(`/cms/newsComment/newsComment`);
                store.dispatch('user/tabRemove', route.fullPath);

            }
                                                                                                                                                                                                                                                                                                                                            return {
                cityData,
                routeId,
                    newsCommentListApp,
                    newsCommentModalApp,
                onSubmit,
                continueSubmit,
                onBack,
                                                                                                                                                                                                                                                                                                                                                                                                                                        }

        },
    })
</script>

<style scoped>

</style>
