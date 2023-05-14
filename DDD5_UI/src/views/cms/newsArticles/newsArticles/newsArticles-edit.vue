
<!-- 新闻文章编辑弹窗 -->
<template>
    <div class="ele-body">
        <a-card :title="newsArticlesModalApp.title" :bordered="false">
            <a-form
                    ref="form2"
                    :model="newsArticlesModalApp.newsArticles"
                    :rules="newsArticlesModalApp.rules"
                    :label-col="{md: {span: 7}, sm: {span: 24}}"
                    :wrapper-col="{md: {span: 17}, sm: {span: 24}}">
                <a-row :gutter="16">
                                                                                                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻标题:" name="title">
                                                                                    <a-input
                                                    v-model:value="newsArticlesModalApp.newsArticles.title"
                                                    placeholder="请输入新闻标题"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻概要:" name="summary">
                                                                                    <a-input
                                                    v-model:value="newsArticlesModalApp.newsArticles.summary"
                                                    placeholder="请输入新闻概要"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻内容:" name="context">
                                                                                    <a-input
                                                    v-model:value="newsArticlesModalApp.newsArticles.context"
                                                    placeholder="请输入新闻内容"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻类别id:" name="categoryId">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.categoryId"
                                                    placeholder="请输入新闻类别id"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻缩略图:" name="thumbnail">
                                                                                    <a-input
                                                    v-model:value="newsArticlesModalApp.newsArticles.thumbnail"
                                                    placeholder="请输入新闻缩略图"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="评论数:" name="comment">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.comment"
                                                    placeholder="请输入评论数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="浏览数:" name="view">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.view"
                                                    placeholder="请输入浏览数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="点赞数:" name="thumb">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.thumb"
                                                    placeholder="请输入点赞数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="收藏数:" name="collect">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.collect"
                                                    placeholder="请输入收藏数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻类型:" name="type">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.type"
                                                    placeholder="请输入新闻类型"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="状态:" name="status">
                                                                                    <a-input
                                                    v-model:value="newsArticlesModalApp.newsArticles.status"
                                                    placeholder="请输入状态"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="是否置顶:" name="isTop">
                                                                                    <a-input-number
                                                    v-model:value="newsArticlesModalApp.newsArticles.isTop"
                                                    placeholder="请输入是否置顶"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="作者姓名:" name="publisherNameId">
                                                                                    <m-entity-select
                                                                                                    :default-value="newsArticlesModalApp.newsArticles.publisherNameName"
                                                    v-model:value="newsArticlesModalApp.newsArticles.publisherNameId"
                                                    placeholder="请选择作者姓名"
                                                    module="newsArticles"
                                                    entity="Publisher"
                                                    class="ele-fluid"
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                                                                            
                    <a-col :md="12" :sm="24" :xs="24">
                        <a-form-item :wrapper-col="{md: {offset: 6}}" style="margin-bottom: -20px">
                            <!--class="ele-pull-right"-->
                            <div v-if="newsArticlesModalApp.display">
                                <teleport to="#newsArticlesEditModalFooter">
                                    <div>
                                        <a-space size="middle">
                                            <a-button @click="newsArticlesListApp.editModalShowing=false">取消
                                            </a-button>
                                            <a-button
                                                    type="primary"
                                                    @click="onSubmit(newsArticlesModalApp.newsArticles)"
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
                            <div v-if="!newsArticlesModalApp.display">
                                <a-space size="middle">
                                    <a-button @click="onBack()">返回列表</a-button>
                                    <a-button
                                            type="primary"
                                            @click="onSubmit(newsArticlesModalApp.newsArticles)"
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
    import {NewsArticlesService} from "@/views/cms/newsArticles/newsArticles/newsArticlesService";
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
            let newsArticlesListApp = inject('newsArticlesListApp', reactive({}));
            const newsArticlesModalApp = reactive({
                id: null,
                    newsArticles: {},
                isEdit: false,
                display: false
            });
                newsArticlesModalApp.rules = {
                                                                                                                                                                            title: [
                                                                                                                {
                            required:                                 true ,
                                type: 'string',
                                message: '请输入新闻标题',
                                trigger: 'blur'
                            }
                        ],
                                                                                    summary: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入新闻概要',
                                trigger: 'blur'
                            }
                        ],
                                                                                    context: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入新闻内容',
                                trigger: 'blur'
                            }
                        ],
                                                                                    categoryId: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入新闻类别id',
                                trigger: 'blur'
                            }
                        ],
                                                                                    thumbnail: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入新闻缩略图',
                                trigger: 'blur'
                            }
                        ],
                                                                                    comment: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入评论数',
                                trigger: 'blur'
                            }
                        ],
                                                                                    view: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入浏览数',
                                trigger: 'blur'
                            }
                        ],
                                                                                    thumb: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入点赞数',
                                trigger: 'blur'
                            }
                        ],
                                                                                    collect: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入收藏数',
                                trigger: 'blur'
                            }
                        ],
                                                                                    type: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入新闻类型',
                                trigger: 'blur'
                            }
                        ],
                                                                                                                        isTop: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入是否置顶',
                                trigger: 'blur'
                            }
                        ],
                                                                                    publisherNameName: [
                                                                                    {
                            required:  false ,
                                type: 'string',
                                message: '请输入作者姓名',
                                trigger: 'blur'
                            }
                        ],
                                                                                    publisherNameId: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入作者姓名',
                                trigger: 'blur'
                            }
                        ],
                                                                                                                                                            }

            onMounted(() => {
                //清理数据
                    newsArticlesModalApp.newsArticles = reactive({});
                if (routeId) {//路由
                        newsArticlesModalApp.id = routeId;
                        newsArticlesModalApp.display = false;
                    findNewsArticles(newsArticlesModalApp.id);
                } else if (newsArticlesListApp.editModalForEdit) {//修改弹窗
                        newsArticlesModalApp.id = newsArticlesListApp.currentId;
                        newsArticlesModalApp.display = true;
                } else if (!newsArticlesListApp.editModalForEdit && newsArticlesListApp.addModalForEdit) {//新增弹窗
                        newsArticlesModalApp.display = true;
                } else {
                        newsArticlesModalApp.title = "新增学生";
                }
                if (newsArticlesModalApp.id) findNewsArticles(newsArticlesModalApp.id);
            })

            const findNewsArticles = (id) => {
                    NewsArticlesService.findNewsArticlesForView(id).then((res) => {
                        newsArticlesModalApp.newsArticles = res.data;
                    //TODO:为编辑准备.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                newsArticlesModalApp.title = "编辑学生 " + newsArticlesModalApp.newsArticles.name;
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
                        NewsArticlesService.updateNewsArticles(data).then((res) => {
                        console.log(res);
                        VXETable.modal.message({content: '操作成功', status: 'success'});
                        setTimeout(onBack, 3000);
                    }).catch(error => {
                        VXETable.modal.message({content: `系统错误，原因是：${error.message}`, status: 'error'});
                    })
                } else {
                    //新增
                        NewsArticlesService.saveNewsArticles(data).then((res) => {
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
                router.push(`/cms/newsArticles/newsArticles`);
                store.dispatch('user/tabRemove', route.fullPath);

            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            return {
                cityData,
                routeId,
                    newsArticlesListApp,
                    newsArticlesModalApp,
                onSubmit,
                continueSubmit,
                onBack,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }

        },
    })
</script>

<style scoped>

</style>
