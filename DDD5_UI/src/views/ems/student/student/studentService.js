
import axios from 'axios';

/**
 * 学生
 */
export class StudentService {
    // 分页查询列表
    static findStudents (queryParams) {
        return axios({
            url: '/ems/student/Student/findStudents',
            method: 'POST',
            data: queryParams
        });
    }

    //根据ID查询指定的学生
    static findStudent (studentId) {
        const query = {
            studentId
        }
        return axios({
            url: '/ems/student/Student/findStudent',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的学生(包含外键名称)
    static findStudentForView (studentId) {
        const query = {
            studentId
        }
        return axios({
            url: '/ems/student/Student/findStudentForView',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的学生(包含外键名称)
    static findStudentForEdit (studentId) {
        const query = {
            studentId
        }
        return axios({
            url: '/ems/student/Student/findStudentForEdit',
            method: 'POST',
            params: query,
        });
    }

    //根据ID查询指定的学生(只提取ID 和 Name)
    static findStudentsWithIdNameById (studentId) {
        const query = {
            studentId
        }
        return axios({
            url: '/ems/student/Student/findStudentsWithIdNameById',
            method: 'POST',
            params: query,
        });
    }

    //根据名称查询学生集合(只提取ID 和 Name)
    static findStudentsWithIdNameByName (studentName) {
        const query = {
            studentName
        }
        return axios({
            url: '/ems/student/Student/findStudentsWithIdNameByName',
            method: 'POST',
            params: query,
        });
    }

    //新增保存学生
    static saveStudent (student) {
        const query = {}
        return axios({
            url: '/ems/student/Student/saveStudent',
            method: 'POST',
            params: query,
            data: student
        });
    }

    //修改保存学生
    static updateStudent (student) {
        const query = {}
        return axios({
            url: '/ems/student/Student/updateStudent',
            method: 'POST',
            params: query,
            data: student
        });
    }

    //根据ID查询指定的学生(只提取ID 和 Name)
    static deleteStudent (studentId) {
        const query = {
            studentId
        }
        return axios({
            url: '/ems/student/Student/deleteStudent',
            method: 'POST',
            params: query,
        });
    }
}
