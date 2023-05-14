'use strict'

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
const path = require('path')
const os = require('os')
const fs = require('fs')

function  initialize (app,webContents) {
// 在主进程中.
  const { ipcMain } = require('electron')
  console.log("=======ipcMain:"+ipcMain)
  ipcMain.on('asynchronous-message', (event, arg) => {
    console.log(arg) // prints "ping"
    event.reply('asynchronous-reply', "return:"+arg)
  })

  ipcMain.on('synchronous-message', (event, arg) => {
    console.log(arg) // prints "ping"
    event.returnValue = "return:"+arg
  })


  console.log("===========111webContents:"+webContents)

  let replyTimes = 0;
  setInterval(()=>{
    console.log("===========222webContents:"+replyTimes+webContents)
    webContents.send('asynchronous-reply', "asynchronous-reply"+(replyTimes++));
  },5000);

  console.log("============OK")
  app.print = function () {
    const options = {
      silent: true,
      deviceName: 'Brother DCP-7180DN Printer',
      pageRanges: [{
        from: 0,
        to: 1
      }]
    }
    webContents.print(options, (success, errorType) => {
      if (!success)
        console.log(errorType)
      else
        console.log("=====Print OK")
    })
  }

  app.printToPDF =function () {
    const pdfPath = path.join(os.homedir(), 'Desktop', 'temp.pdf')
    webContents.printToPDF({}).then(data => {
      fs.writeFile(pdfPath, data, (error) => {
        if (error) throw error
        console.log(`Wrote PDF successfully to ${pdfPath}`)
      })
    }).catch(error => {
      console.log(`Failed to write PDF to ${pdfPath}: `, error)
    })
  }
}

exports.initializeIpc = initialize;
