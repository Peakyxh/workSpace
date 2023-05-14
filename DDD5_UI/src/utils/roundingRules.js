// ESmodule规范
import { create, all } from 'mathjs';
const math= create(all);
math.config({
  epsilon: 1e-12,
  matrix: 'Matrix',
  number: 'BigNumber',
  precision: 64,
  predictable: false,
  randomSeed: null
})
export default class RoundingRules {
  /**
   *
   * @param numVal  数值
   * @param fieldPrecision   精度(有效数字)
   * @returns {Promise<unknown>}
   */
  //正常四舍六入
  static roundingSix(numVal, fieldPrecision) {
    return new Promise((resolve) => {
      let ratio = Math.pow(10, fieldPrecision);
      let newVal = math.evaluate(`${numVal} * ${ratio}`);
      //取整
      let ival = parseInt(newVal, 10);

      /*获取有效的小数*/
      //有效小数位
      let m1 = math.evaluate(`${newVal}-${ival}`);

      let m2 = math.evaluate(`${m1} * 100.0`);
      //10进制转成正整数
      let dm = parseInt(m2, 10);
      //余数
      let mod = math.evaluate(`${dm} / 100.0`);
      //console.log(newVal, ival, m1, m2, dm, mod)
      /****** 主要测试区域 ******/
      if (mod >= 0.6) {
        /*6入 或者 当尾数为5，而尾数“5”的后面还有任何不是0的数字时，无论前一位在此时为奇数还是偶数，也无论“5”后面不为0的数字在哪一位上，都应向前进一位。*/
        resolve(math.evaluate(`(${ival} + 1) / ${ratio}`));
      } else if (mod < 0.5) {
        /*4舍*/
        resolve(math.evaluate(`${ival} / ${ratio}`));
      } else {
        /*5考虑*/
        if (0.5 == mod) {
          /*当尾数为5，而尾数后面的数字均为0时，应看尾数“5”的前一位：若前一位数字此时为奇数，就应向前进一位；若前一位数字此时为偶数，则应将尾数舍去。数字“0”在此时应被视为偶数。*/
          let ff = math.evaluate(`${ival} % 10`);
          /*获取5的前面的一位数字*/
          let tempival=(0 == math.evaluate(`${ff} % 2`) ? ival : math.evaluate(`${ival} + 1`));
          resolve(math.evaluate(` ${tempival}/ ${ratio}`));
        } else {
          resolve(math.evaluate(`(${ival} + 1) / ${ratio}`));
        }
      }
    })
  }

  /**
   *  0.2  0.5修约 (0,5)
   * @param numVal
   * @param fieldPrecision
   * @param specialCalculation
   * @returns {Promise<unknown>}
   */
  static  roundingSixToPrecision (numVal, fieldPrecision, specialCalculation) {
    console.log(numVal,fieldPrecision,specialCalculation)
    return new Promise((resolve) => {
      let ratio = Math.pow(10, fieldPrecision);
      let newVal = math.evaluate(`${numVal} * ${ratio}`);
      //取整
      let ival = parseInt(newVal, 10);

      /*获取有效的小数*/

      //有效小数位
      let m1 = math.evaluate(`${newVal}-${ival}`);

      let m2 = math.evaluate(`${m1} * 100.0`);
      //10进制转成正整数
      let dm = parseInt(m2, 10);
      //精度位前一位
      let pbBefore=Math.floor(math.evaluate(`(${ival}/10)%10`));
      pbBefore==Infinity?pbBefore=0:pbBefore;
      //精度位
      let pb=math.evaluate(`${ival} % 10`);
      //精度位后一位(关键位)
      let pbNext = math.evaluate(`${dm} / 10`);
      //精度位及后1位(共2位) 比如9.8652 保留2位小数 就是65
      let pbNextTwo=parseInt(`${pb}${pbNext}`);
      console.log(newVal, ival, m1, m2, dm,pbBefore,pb,pbNext,pbNextTwo);
      /****** 主要测试区域 ******/
      //TODO 0.2修约 二次处理
      if (specialCalculation == "0.2") {
        //>0 带小数点的数
        if(pb %2==0){
          //精度位0 2 4 6 8
          resolve(this.handleCalcRes(math.evaluate(`${ival} / ${ratio}`),specialCalculation));
          // resolve(result);
        }else{
          //精度位1 3 5 7 9
          //中间位 要5考虑
          // 精度位后一位非0,直接进位
          if(pbNext!=0){

            resolve(this.handleCalcRes(math.evaluate(`${ival+1} / ${ratio}`),specialCalculation));

          }else{
            //为0看精度位前一位是否为奇数,奇数进位,偶数退位
            let tempival=(0 == math.evaluate(`${pbBefore} % 2`) ? math.evaluate(`${ival} - 1`) : math.evaluate(`${ival} + 1`));
            resolve(this.handleCalcRes(math.evaluate(` ${tempival}/ ${ratio}`),specialCalculation));

          }
        }
      }
      //TODO 0.5修约二次处理
      if (specialCalculation == "0.5") {
        if(pbNextTwo<25){
          resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}) / ${ratio}`),specialCalculation));

        }else if(pbNextTwo>25 && pbNextTwo<75){
          resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}+5)/${ratio}`),specialCalculation));

        }else if(pbNextTwo>75){
          resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}+10) / ${ratio}`),specialCalculation));

        } else{
          if(pbBefore%2==0){
            //偶舍
            if(pbNextTwo==25){
              resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}) / ${ratio}`),specialCalculation));

            }else{
              //==75
              resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}+5) / ${ratio}`),specialCalculation));
            }
          }else{
            //奇进
            if(pbNextTwo==25){
              resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}+5) / ${ratio}`),specialCalculation));
            }else{
              //==75
              resolve(this.handleCalcRes(math.evaluate(`(${ival}-${pb}+10) / ${ratio}`),specialCalculation));
            }
          }
        }
      }
    })
  }

  /**
   *  计算精度
   * @param res
   * @param fieldPrecision
   * @returns {Promise<unknown>}
   */
  static handleCalcRes(res,fieldPrecision){
    let result=res;
    let leftDecimals = result.toString().split('.')[1]!=undefined?result.toString().split('.')[1]:'';
    //位数不够补0
    if(leftDecimals.length<fieldPrecision){
      result=result.toFixed(fieldPrecision);
    }
    return result;

  }

  /**
   *
   * @param numVal  数值
   * @param fieldPrecision   精度(有效数字)
   * @param specialCalculation  修约进制  (0.5,0.2) (0,5)
   * @returns {Promise<unknown>}
   */
  //修约处理
  static roundingZeroToFiveOrTwo(numVal, fieldPrecision, specialCalculation) {
    return new Promise((resolve) => {
      //0.5 0.2  0,5
      if (specialCalculation != null) {
        //TODO 0.2修约
        if (specialCalculation == "0.2") {
          numVal = math.evaluate(`${numVal} * 5`);
          //四舍六入 初步
          this.roundingSix(numVal, fieldPrecision).then((res) => {
            //四舍六入处理过的数据
            let tempRes=math.evaluate(`${res}/5`);
            //二次四舍六入0.2修约
            this.roundingSixToPrecision(tempRes, fieldPrecision, specialCalculation).then((resp) => {
              resolve(parseFloat(resp.valueOf()));
            })
          })
        }

        //TODO 0.5修约
        if(specialCalculation=="0.5"){
          numVal = math.evaluate(`${numVal} * 2`);
          //四舍六入 初步
          this.roundingSix(numVal,fieldPrecision).then((res)=>{
            //四舍六入处理过的数据
            let tempRes=math.evaluate(`${res}/2`);
            //二次四舍六入0.5修约
            this.roundingSixToPrecision(tempRes, fieldPrecision, specialCalculation).then((resp) => {
              resolve(parseFloat(resp.valueOf()));
            })
          })
        }
        //TODO 5修约
        if(specialCalculation=="0,5"){
          numVal = math.evaluate(`${numVal} * 2`);
          //四舍六入 初步
          this.roundingSix(numVal,fieldPrecision).then((res)=>{
            //四舍六入处理过的数据
            let tempRes=math.evaluate(`${res}/2`);
            //初步处理直接四舍六入
            this.roundingSixToPrecision(tempRes, 0, specialCalculation).then((resp) => {
              resolve(parseFloat(resp.valueOf()));
            })
          })
        }
      } else {
        //10进制直接四舍六入
        this.roundingSix(numVal, fieldPrecision).then((res) => {
          resolve(parseFloat(res.valueOf()));
        })
      }
    })
  }


}
//测试
// RoundingRules.roundingZeroToFiveOrTwo(5.7352,2,"0.5").then((res)=>{
//   console.log(parseFloat(res.valueOf()));
// })


