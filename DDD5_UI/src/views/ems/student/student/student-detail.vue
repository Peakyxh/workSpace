<!-- 学生详情弹窗 -->
<template>
            <a-form
                    ref="form3"
                    class="ele-form-detail"
                    :model="studentModalApp.student"
                    :label-col="{md: {span: 2}, sm: {span: 4}, xs: {span: 6}}"
                    :wrapper-col="{md: {span: 22}, sm: {span: 20}, xs: {span: 18}}">
                <a-descriptions :title="studentModalApp.title" bordered>
                                    <a-descriptions-item label="标识">
                        {{ studentModalApp.student.Eid }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人">
                        {{ studentModalApp.student.creatorName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="名称">
                        {{ studentModalApp.student.name }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="学生姓名">
                        {{ studentModalApp.student.studentName }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="身份证号">
                        {{ studentModalApp.student.studentIdNum }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="年龄">
                        {{ studentModalApp.student.studentAge }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="入学时间">
                        {{ studentModalApp.student.dateOfStartStudy }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="分数">
                        {{ studentModalApp.student.score }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="入职日期">
                        {{ studentModalApp.student.dateOfJoin }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="性别">
                        {{ studentModalApp.student.gender }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="状态">
                        {{ studentModalApp.student.status }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建人ID">
                        {{ studentModalApp.student.creatorId }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="备注">
                        {{ studentModalApp.student.remark }}
                    </a-descriptions-item>
                                    <a-descriptions-item label="创建时间">
                        {{ studentModalApp.student.createDatetime }}
                    </a-descriptions-item>
                                </a-descriptions>
            </a-form>
</template>

<script>
    import {defineComponent, reactive, onMounted, inject} from "vue";
    import {useRoute} from "vue-router"
    import {StudentService} from "@/views/ems/student/student/studentService";

    export default defineComponent({
        setup() {
            const route = useRoute();
            let routeId = route.params.id;
            let studentListApp = inject('studentListApp', reactive({}));
            const studentModalApp = reactive({id: null, student: {}, display: false});

            onMounted(() => {
                    studentModalApp.student = {};
                if (routeId) {
                        studentModalApp.id = routeId;
                } else if (studentListApp.viewModalShowing) {
                        studentModalApp.id = studentListApp.currentId;
                        studentModalApp.display = true;
                }
                if (studentModalApp.id) {
                    getInfo(studentModalApp.id);
                }
            })

            const getInfo = (id) => {
                    StudentService.findStudent(id).then((res) => {
                            studentModalApp.student = res.data;
                            studentModalApp.title = "查看 " + studentModalApp.student.name;
                })
            }

            return {
                    studentModalApp,
                routeId,
                getInfo,
            }
        }
    })
</script>

<style scoped>
</style>

