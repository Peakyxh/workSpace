// const { contextBridg, ipcRenderer} = require('electron')
// const {TestDll} = require( "./testDll");

// console.log("============ddddddddddddddddddddddddddd")
// console.log("============"+TestDll)
// console.log("============"+JSON.stringify(TestDll) )
// let testDll=  new TestDll()
// console.log("============"+testDll.Hello())

// contextBridge.exposeInMainWorld(
//   'testDll',
//   {
//     doThing: () => ipcRenderer.send('do-a-thing'),
//     hello:testDll.Hello,
//     strlen:testDll.strlen,
//     plus:testDll.plus,
//   }
// )

// // 在主进程中.
// const { ipcMain } = require('electron')
// console.log("=======ipcMain:"+ipcMain)
// ipcMain.on('asynchronous-message', (event, arg) => {
//   console.log(arg) // prints "ping"
//   event.reply('asynchronous-reply', "return:"+arg)
// })
//
// ipcMain.on('synchronous-message', (event, arg) => {
//   console.log(arg) // prints "ping"
//   event.returnValue = "return:"+arg
// })
//
// const { webContents } = require('electron')
// let replyTimes = 0;
// setInterval(()=>{
//   contents.send('asynchronous-reply', "asynchronous-reply"+(replyTimes++));
// },5000);
//
// console.log("============OK")

