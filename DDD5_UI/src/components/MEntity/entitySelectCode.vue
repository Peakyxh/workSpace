<template>
  <a-select
    allowClear
    :show-search="{filter}"
    @search="onSearch"
    @dropdownVisibleChange="onDropdownVisibleChange"
    notFoundContent="未找到相关内容，请重试"
  >
    <a-select-option
      v-for="item in objects"
      :key="item.name"
      :value="item.eid">{{ item.numCode }}
    </a-select-option>
  </a-select>
</template>

<script>
import axios from "axios";

export default {
  name: "MEntitySelectCode",
  props: {
    module: String,
    entity: String,
    system: {
      type:Boolean,
      default: false
    },
  },
  data() {
    return {
      objects: [],
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      // http://localhost:8081/api/sys/user/page?page=1&limit=10
      // get
      if(this.system){
        axios({
          url: `/${this.module}/${this.entity}/page?page=1&limit=10`,
          method: 'GET',
        }).then(res => {
          this.objects = res.data.data;
        }).catch(error => {
          console.log(error);
        })
      }else {
        const queryParams = {currentPage: 1, filters: {}, orderBys: [], pageSize: 10};
        axios({
          url: `/erp/${this.module}/${this.entity}/find${this.entity}s`,
          method: 'POST',
          data: queryParams
        }).then(res => {
          this.objects = res.data.datas;
        }).catch(error => {
          console.log(error);
        })
      }
    },
    onSearch(value) {
      //字符串首字母大写
      // var entityNameUp=this.entity.trim().toLowerCase().replace(this.entity[0], this.entity[0].toUpperCase());
      //字符串首字母小写
      var entityNameLow = this.entity.trim().toLowerCase().replace(this.entity[0], this.entity[0].toLowerCase());
      // console.log(this.entity + ' 首字母转小写为 ' + entityNameLow);
      // var reg = /^[\u0391-\uFFE5A-Za-z]+$/;
      var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]+$/;//中文数字字母
      // console.log(value);
      if (reg.test(value)) {
        axios({
          url: `/erp/${this.module}/${this.entity}/find${this.entity}sWithIdNameByName?${entityNameLow}Name=${value}`,
          method: 'POST'
        }).then(res => {
          this.objects=res.data;
        }).catch(error => {
          console.log(error);
        })
      }
    },
    //    @change="onChange"
    // onChange(value) {
    //   this.objects.forEach((item) => {
    //     if (item.name === value) {
    //       this.$emit(`getEntity${this.entity}Select`, {eid: item.eid, name: item.name});
    //     }
    //   })
    // },
    onDropdownVisibleChange() {
        this.init()
    }
  }
}
</script>

<style scoped>

</style>
