package gov.laca.amp.modules.mac.backing;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

public class MacPerformTaskBean {
    public MacPerformTaskBean() {
        super();
    }
    
   private static transient ADFLogger _log = ADFLogger.createADFLogger(MacPerformTaskBean.class);
    
    public void onUnload(ClientEvent clientEvent) {
            // Add event code here...
            
            try {
                String action = (String) ADFContext.getCurrent().getSessionScope().get("lstbpmaction");
                if("addcomment".equalsIgnoreCase(action)) {
                    updateBPMTask();   
                }
                  
            } catch(Exception e) {
                e.printStackTrace();
                _log.severe("onUnload", e);
            }
           
          //  closeTaskFlow();

        }
    
    private void updateBPMTask() {
            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            oracle.binding.OperationBinding op = bindings.getOperationBinding("update");
            op.execute();
            
        }
}
