 if (window.addEventListener) {
              //  alert('10');
                    window.addEventListener('beforeunload',  performUnloadEvent, false);
                }
                else  {
            //    alert('14');
                window.attachEvent("onbeforeunload",  function (){performUnloadEvent()});
                
                }  
function performUnloadEvent(){ 

  //var x and y are dummy variables obviously needed to keep the page
  //alive for as long it takes to send the custom event to the server
  var windowId = AdfPage.PAGE.getDomWindow().name;
  var pg = AdfPage.PAGE;
  var windowId = pg.getDomWindow().name;
  if(!windowId || pg.isPerformingFullSubmit() || pg._partialRequestRedirect || pg._isPrintablePage() || pg._returnFromDialog || pg.isAttachmentMode()) {
  console.log('returning');
    return
  }

var eventSource = AdfPage.PAGE.findComponentByAbsoluteId('d1'); 
if(eventSource) {
 AdfCustomEvent.queue(eventSource,"handleOnUnload",{},true); 

 var eventQueue = AdfPage.PAGE._eventQueue;
 var event = eventQueue.shift();

 AdfPage.PAGE._serverQueue.push(event);
 AdfPage.PAGE._deliverQueuedServerEvents();

}

    

} 


