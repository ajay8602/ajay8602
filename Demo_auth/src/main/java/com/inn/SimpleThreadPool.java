package com.inn;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;

public class SimpleThreadPool implements Runnable {
	private String command;	
    
    public SimpleThreadPool(String s){
        this.command=s;
    }
    
    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    
    public BasicAuthClient client = new BasicAuthClient();
	public BasicAuthClient client1 = new BasicAuthClient(restTemplateBuilder);

    @Override
    public void run() {      
        try {
			if(client1.invokeProtectedResource(command)) {
				String msg = "[EXTERNAL] This message comes from an external organization.  \n\n\nDear Admin, This is your Service "
						+ command + " info \n\n" + "		Used Vol = " + client1.used_bytes + " MB \n		Max Vol = "
						+ client1.max_bytes + " \n		Thread Count = " + client1.threads_count + "\n		Avg System Load = "
						+ client1.system_load_avg + "\n		Active JDBC Connections = " + client1.active_jdbc_count
						+ "\n		Used JDBC Connection = " + client1.used_jdbc_count + "\n\n\nPlease Check Asap."
						+ "\n\n\n Check these files for more details.";

				if (client1.sendmail.equalsIgnoreCase("true")) {
					 client1.mail(msg);
					 client1.subject = "";
				}				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}        
    }
   
    @Override
    public String toString(){
        return this.command;
    }
}
