<template>
  <div id="event-handling" class="demo">
    <p>{{ message }}</p>
    <h1>{{names}}</h1>
    <ul>
      <li v-for="name in names" v-bind:key="name">{{name}}<span v-if="name.length<5"> 😢</span></li>
    </ul>
    <button v-on:click="reverseMessage">Reverse Message</button>
    <div @click="add"> add </div>
    <input v-model='newName' :class='{error:isError}' @keyup='newNameChange'  />
    <button @click="showStudents">show Students</button>
    <table style="border-style: solid">
      <tr v-for="student in students"  v-bind:key="student.studentName" style="border-style: solid">
        <td>{{student.eid}}</td>
        <td>{{student.studentName}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
    import {StudentService} from "@/views/ems/student/student/studentService";
    import {VXETable} from "vxe-table";

    export default {
      name: "demo1",
      data() {
        return {
          message: 'Hello Vue.js!11111111111333311111',
          names:['xcy','zy','yy'],
          newName:'new Name',
          isError:false,
          students:[]
        }
      },
      methods: {
        reverseMessage() {
          this.message = this.message
            .split('')
            .reverse()
            .join('')
        },
        add(){
          this.names.push(this.newName)
        },
        newNameChange(){
          console.log(this.newName)
          this.isError = this.newName.length <5
          console.log(this.isError)
        },
        showStudents(){
          const queryParams = {}
          queryParams.currentPage = 1
          queryParams.pageSize = 100

          StudentService.findStudents(queryParams)
            .then((res)=>{

            this.students = res.data.datas
          })
          .catch(error => {
            VXETable.modal.message({content: `加载数据出错，原因是：${error.message}`, status: 'error'});

          })
        }
      }
    }
</script>

<style scoped>

  .demo {
    font-family: sans-serif;
    border: 1px solid #eee;
    border-radius: 2px;
    padding: 20px 30px;
    margin-top: 1em;
    margin-bottom: 40px;
    user-select: none;
    overflow-x: auto;
  }
  .error{

    border-color:red;

  }
</style>
