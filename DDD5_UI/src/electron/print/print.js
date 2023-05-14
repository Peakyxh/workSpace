const { ipcRenderer } = require('electron');
ipcRenderer.on('print-edit', (event, obj) => {
  let html = '';
  html+=`<div>${obj.html}</div>`
  document.body.innerHTML = html;
  ipcRenderer.send('do', obj.deviceName);
})


// //获取打印机列表
// getPrint(callback){
//   ipcRenderer.send('allPrint');
//   ipcRenderer.on('printName',(event,data)=>{
//     callback(data) //data就是返回的打印机数据列表
//   })
// }
