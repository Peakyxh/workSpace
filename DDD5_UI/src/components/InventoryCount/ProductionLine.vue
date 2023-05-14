<template>
  <div class="produceLineBkg">
            <a-col :span="4" :order="3">
              <div>
                  <div class="produceLineP">
                    一号生产线
                  </div>
                  <a-table :data-source="data" bordered class="widget-main">
                    <template v-slot:action>

                    </template>
                  </a-table>
                </div>

            </a-col>
  </div>

</template>

<script>

  import {defineComponent} from "vue";

  export default defineComponent({
    name: 'ProductionLine',
    // components: {
    //
    // },
    setup() {

    }

  })
</script>

<style scoped>
  .produceLineBkg {
    padding-left:15px ;
    height: 160px;
    z-index: 2;
  }
  .produceLineP{
    background-color: rgb(132, 132, 132);
    width: 200px;
    height: 40px;
    text-align: center;
    padding-top: 7px;
    color: white;
    font-size: 16px;
  }
  /*.stroageNum{*/
  /*  position: absolute;*/
  /*  left: 200px;*/
  /*}*/
  /*.stroageNum1{*/
  /*  position: absolute;*/
  /*  right: 800px;*/
  /*}*/
  .widget-main{
    width: 200px;
    height: 120px;
  }
  .ant-empty-normal {
    margin: 13px 0 !important;
  }
  /*.wareWid{*/
  /*   width: 80px;*/
  /*  z-index: 100;*/
  /*}*/
  /*.pict{*/
  /*  height: 130px;*/
  /*}*/
  /*.Ware{*/
  /*  margin-left: 20px;*/
  /*  top:-222px;;*/
  /*  position: relative;*/
  /*}*/
  /*.WareName{*/
  /*  margin-left:9px;*/
  /*}*/
  /*.materialsName{*/
  /*  position: absolute;*/
  /*  left: 0px;*/
  /*}*/
  /*.bsAmount{*/
  /*  top: 60px;*/
  /*}*/
  /*.bgmAmount{*/
  /*  top: 80px;*/
  /*  text-align: center;*/
  /*}*/
  /*.stroageN{*/
  /*  width: 100px;*/

  /*}*/
  /*.color1{*/
  /*  z-index: 2000;*/
  /*  position: relative;*/
  /*}*/
  /*.color2{*/
  /*  position: absolute;*/
  /*}*/
  /*.bgNum{*/
  /*  z-index: 200;*/
  /*  position: absolute;*/
  /*  width: 80px;*/
  /*  height: 100px;*/
  /*  background-color: rgb(20, 196, 25);*/
  /*  top: 55px;*/
  /*  left: 20px;*/
  /*  opacity: 0.6;*/
  /*}*/
</style>
