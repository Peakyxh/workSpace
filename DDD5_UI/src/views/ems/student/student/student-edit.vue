
<!-- 学生编辑弹窗 -->
<template>
    <div class="ele-body">
        <a-card :title="studentModalApp.title" :bordered="false">
            <a-form
                    ref="form2"
                    :model="studentModalApp.student"
                    :rules="studentModalApp.rules"
                    :label-col="{md: {span: 7}, sm: {span: 24}}"
                    :wrapper-col="{md: {span: 17}, sm: {span: 24}}">
                <a-row :gutter="16">
                                                                                                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="学生姓名:" name="studentName">
                                                                                    <a-input
                                                    v-model:value="studentModalApp.student.studentName"
                                                    placeholder="请输入学生姓名"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="身份证号:" name="studentIdNum">
                                                                                    <a-input
                                                    v-model:value="studentModalApp.student.studentIdNum"
                                                    placeholder="请输入身份证号"
                                                    :maxlength="255"
                                                    allow-clear
                                            />
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="年龄:" name="studentAge">
                                                                                    <a-input-number
                                                    v-model:value="studentModalApp.student.studentAge"
                                                    placeholder="请输入年龄"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="入学时间:" name="dateOfStartStudy">
                                                                                    <a-date-picker
                                                    v-model:value="studentModalApp.student.dateOfStartStudy"
                                                    placeholder="请选择入学时间"
                                                    value-format="YYYY-MM-DD H:m:s"
                                                    class="ele-fluid"/>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="分数:" name="score">
                                                                                    <a-input-number
                                                    v-model:value="studentModalApp.student.score"
                                                    placeholder="请输入分数"
                                                    :min="0"
                                                    :step="1"
                                                    class="ele-fluid"
                                            ></a-input-number>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="入职日期:" name="dateOfJoin">
                                                                                    <a-date-picker
                                                    v-model:value="studentModalApp.student.dateOfJoin"
                                                    placeholder="请选择入职日期"
                                                    value-format="YYYY-MM-DD H:m:s"
                                                    class="ele-fluid"/>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                            
                                <a-col :lg="6" :md="12" :sm="24" :xs="24">
                                    <a-form-item label="性别:" name="gender">
                                                                                    <m-dict-select
                                                    v-model:value="studentModalApp.student.gender"
                                                    placeholder="请选择性别"
                                                    dict="Gender"/>
                                                                            </a-form-item>
                                </a-col>
                                                                                                                                                                                                                                                        
                    <a-col :md="12" :sm="24" :xs="24">
                        <a-form-item :wrapper-col="{md: {offset: 6}}" style="margin-bottom: -20px">
                            <!--class="ele-pull-right"-->
                            <div v-if="studentModalApp.display">
                                <teleport to="#studentEditModalFooter">
                                    <div>
                                        <a-space size="middle">
                                            <a-button @click="studentListApp.editModalShowing=false">取消
                                            </a-button>
                                            <a-button
                                                    type="primary"
                                                    @click="onSubmit(studentModalApp.student)"
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
                            <div v-if="!studentModalApp.display">
                                <a-space size="middle">
                                    <a-button @click="onBack()">返回列表</a-button>
                                    <a-button
                                            type="primary"
                                            @click="onSubmit(studentModalApp.student)"
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
    import {StudentService} from "@/views/ems/student/student/studentService";
    import {VXETable} from 'vxe-table'
    import {useStore} from "vuex";
    import regions from 'ele-admin-pro/packages/regions.js';

                                                                                                                                                                                                                                                                                                                    
                        import MDictSelect from "@/components/MDict/dictSelect";
                                        
    export default defineComponent({
        components: {
                                                                MDictSelect,
                                            },
        setup() {
            // 省市区数据
            const cityData = regions
            const route = useRoute();
            const router = useRouter();
            const store = useStore();
            let routeId = route.params.id;
            let studentListApp = inject('studentListApp', reactive({}));
            const studentModalApp = reactive({
                id: null,
                    student: {},
                isEdit: false,
                display: false
            });
                studentModalApp.rules = {
                                                                                                                                                                            studentName: [
                                                                                                                {
                            required:                                 true ,
                                type: 'string',
                                message: '请输入学生姓名',
                                trigger: 'blur'
                            }
                        ],
                                                                                    studentIdNum: [
                                                                                                                {
                            required:                                 true ,
                                type: 'string',
                                message: '请输入身份证号',
                                trigger: 'blur'
                            }
                        ],
                                                                                    studentAge: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入年龄',
                                trigger: 'blur'
                            }
                        ],
                                                                                    dateOfStartStudy: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入入学时间',
                                trigger: 'blur'
                            }
                        ],
                                                                                    score: [
                                                                                                                {
                            required:  false ,
                                type: 'number',
                                message: '请输入分数',
                                trigger: 'blur'
                            }
                        ],
                                                                                    dateOfJoin: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入入职日期',
                                trigger: 'blur'
                            }
                        ],
                                                                                    gender: [
                                                                                                                {
                            required:  false ,
                                type: 'string',
                                message: '请输入性别',
                                trigger: 'blur'
                            }
                        ],
                                                                                                                                                                                                }

            onMounted(() => {
                //清理数据
                    studentModalApp.student = reactive({});
                if (routeId) {//路由
                        studentModalApp.id = routeId;
                        studentModalApp.display = false;
                    findStudent(studentModalApp.id);
                } else if (studentListApp.editModalForEdit) {//修改弹窗
                        studentModalApp.id = studentListApp.currentId;
                        studentModalApp.display = true;
                } else if (!studentListApp.editModalForEdit && studentListApp.addModalForEdit) {//新增弹窗
                        studentModalApp.display = true;
                } else {
                        studentModalApp.title = "新增学生";
                }
                if (studentModalApp.id) findStudent(studentModalApp.id);
            })

            const findStudent = (id) => {
                    StudentService.findStudentForView(id).then((res) => {
                        studentModalApp.student = res.data;
                    //TODO:为编辑准备.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    studentModalApp.title = "编辑学生 " + studentModalApp.student.name;
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
                        StudentService.updateStudent(data).then((res) => {
                        console.log(res);
                        VXETable.modal.message({content: '操作成功', status: 'success'});
                        setTimeout(onBack, 3000);
                    }).catch(error => {
                        VXETable.modal.message({content: `系统错误，原因是：${error.message}`, status: 'error'});
                    })
                } else {
                    //新增
                        StudentService.saveStudent(data).then((res) => {
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
                router.push(`/ems/student/student`);
                store.dispatch('user/tabRemove', route.fullPath);

            }
                                                                                                                                                                                                                                                                                                                                                                                                                                return {
                cityData,
                routeId,
                    studentListApp,
                    studentModalApp,
                onSubmit,
                continueSubmit,
                onBack,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }

        },
    })
</script>

<style scoped>

</style>
