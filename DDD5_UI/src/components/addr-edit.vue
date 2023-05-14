<template>
  <div>
    <a-input-group>
      <a-row :gutter="16">
        <a-col :md="16" :sm="16" :xs="16">
          <a-input v-model:value="addrDetailInfo" :size="large"/>
        </a-col>
        <a-col :md="8" :sm="8" :xs="8">
          <a-button type="primary" :size="large" @click="showModal">编辑</a-button>
        </a-col>
      </a-row>
    </a-input-group>
    <!-- 模态框部分-表单   -->
    <a-modal v-model:visible="visibleFlag"
             width="80%"
             title="地址信息编辑"
             wrapClassName=""
             @ok="okFn"
             @cancel="cancelFn">
      <!--表单-->
      <a-row :gutter="16">
        <a-col :md="16" :sm="16" :xs="16">
          <ele-map-picker
            :addr="addrDetailInfo"
            v-model:visible="showPickerFlag"
            :need-city="true"
            @done="onChooseFn"/>
        </a-col>
        <a-col :md="8" :sm="8" :xs="8">
          <a-card title="编辑地址">
            <a-form :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol" :labelAlign="left">
              <a-form-item label="地址简称">
                <a-input v-model:value="formData.addrAbbreviated" placeholder=""/>
              </a-form-item>
              <a-form-item label="地址类别">
                <a-select v-model:value="formData.addrType" placeholder="请选择默认值">
                  <a-select-option value="c1">供应商</a-select-option>
                  <a-select-option value="c2">生产商</a-select-option>
                  <a-select-option value="c3">客户</a-select-option>
                  <a-select-option value="c4">工程</a-select-option>
                  <a-select-option value="c5">施工部位</a-select-option>
                </a-select>
              </a-form-item>
              <!--   业务id           -->
              <a-input v-model:value="formData.entiyId" type="hidden"/>
              <!--    省市区三级联动        -->
              <a-form-item label="请选择省市区">
                <a-cascader
                  v-model:value="formData.cityValue"
                  :options="cityData.cityData"
                  placeholder="请选择省市区"
                  popup-class-name="ele-pop-wrap-higher"/>
              </a-form-item>
              <a-form-item label="街道地址">
                <a-textarea v-model:value="formData.streetAddr"/>


              </a-form-item>
              <a-form-item label="详细地址" :rows="6">
                <a-textarea v-model:value="formData.detailAddr"/>
              </a-form-item>
              <!-- 经度纬度隐藏域  lon 经度 lat维度 -->
              <a-input type="hidden" v-model:value="formData.lng"></a-input>
              <a-input type="hidden" v-model:value="formData.lat"></a-input>
              <a-form-item label="邮编">
                <a-input v-model:value="formData.postCode">
                </a-input>
              </a-form-item>
            </a-form>
          </a-card>

        </a-col>
      </a-row>
    </a-modal>
    <!-- 地图位置选择弹窗 -->

  </div>
</template>
<script>
  //导入地图相关
  import EleMapPicker from 'ele-admin-pro/packages/ele-map-picker';
  import {defineComponent, ref, reactive, onMounted,inject} from 'vue'
  import regions from 'ele-admin-pro/packages/regions.js';

  export default defineComponent({
      name: 'addr-edit',
      emits:['getDetailAddr'],
    setup(props,context){

    //省市区数据
    let cityData = reactive(regions);
    //城市拼接字符串
    let cityStr = ref('');
    //flag
    let visibleFlag = ref(false);
    //flag
    let showPickerFlag = ref(true);
    //文本框内容
    let addrDetailInfo = ref('');
    //表单数据
    let formData = reactive({});
    //地图获取数据
    let locationData = reactive({});
      //接收变量
      addrDetailInfo.value=inject('addrInfo');
      let entiyId=inject('entiyId');
      formData.entiyId=entiyId.value;
      console.log(addrDetailInfo);

    //生命周期
    onMounted(() => {
    })
    //显示展示框
    let showModal = () => {
      //Modal.info();
      visibleFlag.value = true;

    }
    //ok按钮事件
    let okFn = (e) => {
      console.log(e);
      //TODO
      //赋值给子组件文本框,刷新数据
      addrDetailInfo.value=formData.addrAbbreviated+formData.detailAddr;
      //将表单数据传递给父组件
      context.emit('getDetailAddr',formData);
      //formData中含有数据库必要字段。
      //console.log(formData);


      visibleFlag.value = false;
    }
    //取消按钮事件
    let cancelFn = () => {
      return false;
    }
    //地图选点回调事件
    let onChooseFn = (location) => {
      //地址数据
      locationData = location;
      console.log(locationData);
      if (locationData != '') {
        if (locationData.city != '') {
          let cityData = locationData.city;

          if (cityData.province == "重庆市" || cityData.province == "北京市" || cityData.province == "上海市" || cityData.province == "天津市") {
            cityData.city = cityData.province;
            cityData.province = cityData.province.substring(0, 2);
          }
          //地址简称
          formData.addrAbbreviated = locationData.name;
          //街道名称
          formData.streetAddr = locationData.address;
          //赋值三级联动框
          let cityTemp = [cityData.province, cityData.city, cityData.district];
          // console.log(cityTemp);
          cityStr.value = cityTemp.join('');
          formData.cityValue = regions.getValue(cityTemp);
          //拆分省市区分别为字符串(名称)
          formData.province = cityTemp[0];
          formData.city = cityTemp[1];
          formData.district = cityTemp[2];
          //详细地址
          formData.detailAddr = cityStr.value + formData.streetAddr;
          //经纬度
          formData.lng = locationData.lng;
          formData.lat = locationData.lat;

        }
      }


    }
    return {
      cityData,
      cityStr,
      visibleFlag,
      showPickerFlag,
      addrDetailInfo,
      formData,
      locationData,
      labelCol: {span: 5},
      wrapperCol: {span: 19},
      showModal,
      okFn,
      cancelFn,
      onChooseFn,
    }
  }
  ,
  components:{
    EleMapPicker,
  }
  })
</script>
<style scoped>
  .ant-card {
    height: 557px;
  }

  .full-modal {

  .ant-modal {
    max-width: 100%;
    top: 0;
    padding-bottom: 0;
    margin: 0;
  }

  .ant-modal-content {
    display: flex;
    flex-direction: column;
    height: calc(100vh);
  }

  .ant-modal-body {
    flex: 1;
  }

  }
</style>
