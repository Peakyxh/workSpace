
<!-- 发布者信息编辑弹窗 -->
<template>
    <div class="ele-body">
        <a-card :title="publisherModalApp.title" :bordered="false">
            <a-form
                    ref="form2"
                    :model="publisherModalApp.publisher"
                    :rules="publisherModalApp.rules"
                    :label-col="{md: {span: 7}, sm: {span: 24}}"
                    :wrapper-col="{md: {span: 17}, sm: {span: 24}}">
                <a-row :gutter="16">
                                                                                                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="联系电话:" name="phone">
                                                                                    <a-input
                                                    v-model:value="publisherModalApp.publisher.phone"
                                                    placeholder="请输入联系电话"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="地址:" name="address">
                                                                                    <a-input
                                                    v-model:value="publisherModalApp.publisher.address"
                                                    placeholder="请输入地址"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="新闻条数:" name="count">
                                                                                    <a-input-number
                                                    v-model:value="publisherModalApp.publisher.count"
                                                    placeholder="请输入新闻条数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                                                                                                                        
                    <a-col :md="12" :sm="24" :xs="24">
                        <a-form-item :wrapper-col="{md: {offset: 6}}" style="margin-bottom: -20px">
                            <!--class="ele-pull-right"-->
                            <div v-if="publisherModalApp.display">
                                <teleport to="#publisherEditModalFooter">
                                    <div>
                                        <a-space size="middle">
                                            <a-button @click="publisherListApp.editModalShowing=false">取消
                                            </a-button>
                                            <a-button
                                                    type="primary"
                                                    @click="onSubmit(publisherModalApp.publisher)"
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
                            <div v-if="!publisherModalApp.display">
                                <a-space size="middle">
                                    <a-button @click="onBack()">返回列表</a-button>
                                    <a-button
                                            type="primary"
                                            @click="onSubmit(publisherModalApp.publisher)"
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
    import {PublisherService} from "@/views/cms/publisher/publisher/publisherService";
    import {VXETable} from 'vxe-table'
    import {useStore} from "vuex";
    import regions from 'ele-admin-pro/packages/regions.js';

                                                                                                                                                                                                                        
                                                
    export default defineComponent({
        components: {
                                                                                },
        setup() {
            // 省市区数据
            const cityData = regions
            const route = useRoute();
            const router = useRouter();
            const store = useStore();
            let routeId = route.params.id;
            let publisherListApp = inject('publisherListApp', reactive({}));
            const publisherModalApp = reactive({
                id: null,
                    publisher: {},
                isEdit: false,
                display: false
            });
                publisherModalApp.rules = {
                                                                                                                                                                            phone: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入联系电话',
                                trigger: 'blur'
                            }
                        ],
                                                                                    address: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入地址',
                                trigger: 'blur'
                            }
                        ],
                                                                                    count: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入新闻条数',
                                trigger: 'blur'
                            }
                        ],
                                                                                                                                                                                                }

            onMounted(() => {
                //清理数据
                    publisherModalApp.publisher = reactive({});
                if (routeId) {//路由
                        publisherModalApp.id = routeId;
                        publisherModalApp.display = false;
                    findPublisher(publisherModalApp.id);
                } else if (publisherListApp.editModalForEdit) {//修改弹窗
                        publisherModalApp.id = publisherListApp.currentId;
                        publisherModalApp.display = true;
                } else if (!publisherListApp.editModalForEdit && publisherListApp.addModalForEdit) {//新增弹窗
                        publisherModalApp.display = true;
                } else {
                        publisherModalApp.title = "新增学生";
                }
                if (publisherModalApp.id) findPublisher(publisherModalApp.id);
            })

            const findPublisher = (id) => {
                    PublisherService.findPublisherForView(id).then((res) => {
                        publisherModalApp.publisher = res.data;
                    //TODO:为编辑准备.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    publisherModalApp.title = "编辑学生 " + publisherModalApp.publisher.name;
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
                        PublisherService.updatePublisher(data).then((res) => {
                        console.log(res);
                        VXETable.modal.message({content: '操作成功', status: 'success'});
                        setTimeout(onBack, 3000);
                    }).catch(error => {
                        VXETable.modal.message({content: `系统错误，原因是：${error.message}`, status: 'error'});
                    })
                } else {
                    //新增
                        PublisherService.savePublisher(data).then((res) => {
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
                router.push(`/cms/publisher/publisher`);
                store.dispatch('user/tabRemove', route.fullPath);

            }
                                                                                                                                                                                                                                                                                                                return {
                cityData,
                routeId,
                    publisherListApp,
                    publisherModalApp,
                onSubmit,
                continueSubmit,
                onBack,
                                                                                                                                                                                                                                                                                                                                                                                                    }

        },
    })
</script>

<style scoped>

</style>
