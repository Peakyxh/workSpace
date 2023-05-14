const path = require('path')


function TestDll() {
  let src = "F:\\压力试验机";

  try {
    // Call *.dll with ffi
    let ffi = require('ffi-napi');
    // Create funtions
    // this.dll = ffi.Library(path.resolve(__dirname, '..\\dll\\MyDLL.dll'), {
    // this.dll = ffi.Library(path.resolve(__dirname, 'D:\\压力试验机\\MyDLL.dll'), {
    this.dll = ffi.Library( 'D:\\压力试验机\\MyDLL.dll', {
      'add': ['float', ['float', 'float']],
      'Hello': ['string', []],
      'StrLength': ['int', ['string']]
    })

    //无锡建仪
    // this.dll2 = ffi.Library(path.resolve(__dirname, '..\\dll\\wxjy_Control_kzky.dll'), {
      this.dll2 = ffi.Library( 'D:\\压力试验机\\wxjy_Control_kzky.dll', {
      //TODO:   初始化系统
      //    int WXJY_InitSystem(char *)
      //    初始化系统。正常返回1，异常返回0
      //    例子：WXJY_InitSystem("D:\\压力试验机");
      //    参数说明：压力机软件所在目录，默认位置为“D:\\压力试验机”。
      'WXJY_InitSystem': ['int', ['string']],

      //TODO:设置加载速度
      //     int WXJY_SetSpeed(double)
      //     单位为kN/s，正常返回1，异常返回0
      'WXJY_SetSpeed': ['int', ['speed']],

      //TODO:开始加载
      //    int WXJY_Start(int);
      //    正常返回1，异常返回0
      //    参数说明：抗压的参数为0，WXJY_Start(0)
      //              抗折的参数为1，WXJY_Start(1)
      'WXJY_Start': ['string', []],

      //TODO:获得加载时间
      //    double WXJY_GetTime(void);
      //    单位为s，也可不调用此函数，使用调用程序的时间
      'WXJY_GetTime': ['', ''],

      //TODO:获得力值
      'WXJY_GetForce': ['string', []],
      //停止加载
      'WXJY_StopSystem': ['string', []],
      //系统复位
      'WXJY_ReSet': ['string', []]
    })

    console.log('fii.Library result:', this.dll2);

  } catch (error) {
    console.error('ffi.Library', error);
  }

  this.plus = function (a, b) {
    console.log("到这里了！！！")
    let sum = this.dll.Ddd(a, b);
    console.log(`${a}+${b}=${sum}`);
    return sum;
  }

  this.strlen = function (str) {

    return this.dll.StrLength(str);
  }

  this.Hello = function () {

    return this.dll.Hello();
  }

  this.InitSystem = function () {
    console.log("到这里了！！！")
    return this.dll2.WXJY_InitSystem(src);
  }

  this.SetSpeed = function () {
    console.log("到这里了！！！")
    return this.dll2.WXJY_SetSpeed(1.5);
  }
}

exports.TestDll = TestDll;
