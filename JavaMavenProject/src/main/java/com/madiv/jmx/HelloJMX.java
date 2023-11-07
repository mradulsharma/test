package com.madiv.jmx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloJMX {
	
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MyJMXBean bean = context.getBean(MyJMXBean.class);
		System.out.println("Started !!");
		for(int i=0; i<100; i++) {
			System.out.println("Invokved ["+(i+1)+"]:"+bean);
			Thread.sleep(2000l);
			if(bean.isShutdown()) break;
		}
		System.out.println("Ending...");
	}
}



/*Output

Dec 05, 2018 11:35:07 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@9f70c54: startup date [Wed Dec 05 11:35:07 AEDT 2018]; root of context hierarchy
Dec 05, 2018 11:35:07 AM org.springframework.jmx.export.MBeanExporter afterSingletonsInstantiated
INFO: Registering beans for JMX exposure on startup
Dec 05, 2018 11:35:08 AM org.springframework.jmx.export.MBeanExporter autodetect
INFO: Bean with name 'myJMXBean' has been autodetected for JMX exposure
Dec 05, 2018 11:35:08 AM org.springframework.jmx.export.MBeanExporter registerBeanInstance
INFO: Located managed bean 'myJMXBean': registering with JMX server as MBean [MyJMXBean:name=MyJMXManaged Bean Name]
Started !!
Invokved [1]:MyMXBean [items=[], shutdown=false]
Invokved [2]:MyMXBean [items=[], shutdown=false]
Invokved [3]:MyMXBean [items=[], shutdown=false]
Invokved [4]:MyMXBean [items=[], shutdown=false]
Invokved [5]:MyMXBean [items=[], shutdown=false]
Invokved [6]:MyMXBean [items=[], shutdown=false]
Invokved [7]:MyMXBean [items=[], shutdown=false]
Invokved [8]:MyMXBean [items=[], shutdown=false]
Invokved [9]:MyMXBean [items=[], shutdown=false]
Invokved [10]:MyMXBean [items=[], shutdown=false]
Invokved [11]:MyMXBean [items=[], shutdown=false]
Invokved [12]:MyMXBean [items=[], shutdown=false]
Invokved [13]:MyMXBean [items=[], shutdown=false]
Invokved [14]:MyMXBean [items=[], shutdown=false]
Invokved [15]:MyMXBean [items=[], shutdown=false]
Invokved [16]:MyMXBean [items=[], shutdown=false]
Invokved [17]:MyMXBean [items=[], shutdown=false]
Invokved [18]:MyMXBean [items=[], shutdown=false]
Invokved [19]:MyMXBean [items=[], shutdown=false]
Invokved [20]:MyMXBean [items=[], shutdown=false]
Invokved [21]:MyMXBean [items=[], shutdown=false]
Invokved [22]:MyMXBean [items=[], shutdown=false]
Invokved [23]:MyMXBean [items=[], shutdown=false]
Invokved [24]:MyMXBean [items=[], shutdown=false]
Invokved [25]:MyMXBean [items=[], shutdown=false]
Invokved [26]:MyMXBean [items=[Mark], shutdown=false]
Invokved [27]:MyMXBean [items=[Mark], shutdown=false]
Invokved [28]:MyMXBean [items=[Mark], shutdown=false]
Invokved [29]:MyMXBean [items=[Mark], shutdown=false]
Invokved [30]:MyMXBean [items=[Mark, Hock], shutdown=false]
Invokved [31]:MyMXBean [items=[Mark, Hock], shutdown=false]
Invokved [32]:MyMXBean [items=[Mark, Hock], shutdown=false]
Ending...


*/