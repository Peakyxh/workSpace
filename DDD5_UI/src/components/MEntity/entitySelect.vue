<template>
  <!--  :getPopupContainer="triggerNode => triggerNode.parentNode"-->
  <a-select
    allowClear
    show-search
    option-filter-prop="label"
    option-label-prop="label"
    @select="onChange"
    @dropdownVisibleChange="onDropdownVisibleChange"
    notFoundContent="未找到相关内容，请重试"
    @focus="onFocus"
    @deselect="onDeselect"
    @popupScroll="handlePopupScroll"

  >
    <template #dropdownRender="{ menuNode: menu }">
      <v-nodes :vnodes="menu"/>
      <a-divider style="margin: 4px 0"/>
      <span
        style="padding: 4px 8px; cursor: pointer"
        @mousedown="e => e.preventDefault()"
        @click="onPrev">
        <a-button size="small" type="text">上一页</a-button>
      </span>
      <span
        style="padding: 4px 8px; cursor: pointer"
        @mousedown="e => e.preventDefault()"
        @click="onNext">
        <a-button size="small" type="text">下一页</a-button>
      </span>
    </template>
    <a-select-option
      v-for="item in objects"
      :key="item[getValue]"
      :value="item[getValue]"
      :label="item[showOptionsName]"
    >
      <template v-if="popoverContent.length>0">
        <a-popover placement="left" :title="title" :content="content">
          <template #title>
            {{ popoverTitle }}
          </template>
          <template #content>
            <a-descriptions layout="vertical" bordered>
              <a-descriptions-item v-for="(popover,key) in popoverContent" :key="key" :label="popover.title">
                {{ item[popover.field] }}
              </a-descriptions-item>
            </a-descriptions>
          </template>
          {{ item[showOptionsName] }}
        </a-popover>
      </template>
      <template v-else>
        {{ item[showOptionsName] }}
      </template>
    </a-select-option>
  </a-select>
</template>

<script>
import axios from "axios";
import _ from 'lodash';

export default {
  components: {
    VNodes: (_, {attrs}) => {
      return attrs.vnodes;
    },
  },
  name: "MEntitySelect",
  props: {
    module: String,
    entity: String,
    sys: {
      type: String,
      default: () => {
        return 'erp';
      }
    },
    //选择默认值
    getValue: {
      type: String,
      default: () => {
        return 'eid';
      }
    },
    //显示值
    showOptionsName: {
      type: String,
      default: () => {
        return 'name';
      }
    },
    //前端固定过滤单字段
    fixedFilterOpt: {
      type: Object,
      default: () => {
        return null;
      }
    },
    //切换系统模块 “1” 切换到lis模块“2”
    system: {
      type: String,
      default: '0'
    },

    popoverTitle: {
      type: String,
      default: () => {
        return '标题';
      }
    },
    popoverContent: {
      type: Array,
      default: () => {
        return [];
      }
    },
    customObjects: {
      type: Array,
      default: () => {
        return [];
      }
    },
    queryFilters: {
      type: Object,
    }

  },
  data() {
    return {
      objects: [],
      queryParams: {currentPage: 1, filters: {}, orderBys: [], pageSize: 50},
      totalPage:1,//默认是一页
      tempObj: [],
    }
  },
  mounted() {
    if (this.customObjects.length > 0) {
      //页数长度获取
      this.totalPage=Math.ceil(this.customObjects.length/this.queryParams.pageSize);

      this.objects = this.customObjects;
    } else {
      this.init()
    }
  },

  methods: {
    init() {
      // http://localhost:8081/api/sys/user/page?page=1&limit=10
      // get
      // console.log("query")
      // console.log(this.fixedFilterOpt);
      // console.log("0000")
      // console.log(this.queryFilters)
      if (this.queryFilters != null) {
        this.queryParams.filters = this.queryFilters
      }
      if (this.system && this.system === '1') {
        axios({
          url: `/${this.module}/${this.entity}`,
          method: 'GET',
        }).then(res => {
          this.objects = res.data.data;
        }).catch(error => {
          console.log(error);
        })
      } else {
        axios({
          url: `/${this.sys}/${this.module}/${this.entity}/find${this.entity}s`,
          method: 'POST',
          data: this.queryParams
        }).then(res => {
          this.objects = res.data.datas;

          //页数长度获取
          this.totalPage=Math.ceil(res.data.totalCount/this.queryParams.pageSize);

          //单字段过滤器
          if (this.fixedFilterOpt !== null) {
            this.tempObj = this.objects;
            //先清空数据
            this.objects = [];
            let tempArr = [];
            this.tempObj.forEach((item) => {
              if (item[Object.keys(this.fixedFilterOpt)[0]] === Object.values(this.fixedFilterOpt)[0]) {
                tempArr.push(item);
              }
            })
            this.objects = tempArr;
          }
        }).catch(error => {
          console.log(error);
        })
      }
    }
    ,
    /* onSearch(value) {

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
           this.objects = res.data;
         }).catch(error => {
           console.log(error);
         })
       }
     },*/
    onChange(value) {
      this.objects.forEach(item => {
        if (item.eid === value) {
          this.$emit(`getEntity${this.entity}Select`, item);
        }
      })
    }
    ,
    onDeselect(value) {
      console.log("取消选择");
      console.log(value);
      this.$emit(`getEntity${this.entity}DeSelect`, value);
    }
    ,
    onFocus: _.debounce(function () {
      if (this.customObjects.length > 0) {
        this.objects = this.customObjects;
      } else {
        this.init()
      }
    }, 500),
    onDropdownVisibleChange: _.debounce(function () {
      if (this.customObjects.length > 0) {
        this.objects = this.customObjects;
      } else {
        this.init()
      }
    }, 500),
    onNext() {
      if(this.queryParams.currentPage<this.totalPage){
        this.queryParams.currentPage += 1;
        this.init();
      }
    }
    ,
    onPrev() {
      if (this.queryParams.currentPage > 1) {
        this.queryParams.currentPage -= 1;
      }
      this.init();
    }
    ,
//滚动时触发（防止抖动）
    handlePopupScroll: _.debounce(function () {
      if (this.customObjects.length > 0) {
        this.objects = this.customObjects;
      } else {
        this.init()
      }
    }, 500),
  }
}
</script>

<style scoped>

</style>
