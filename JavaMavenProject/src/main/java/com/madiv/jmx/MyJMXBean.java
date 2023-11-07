package com.madiv.jmx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "MyJMXManaged Description", objectName = "MyJMXBean:name=MyJMXManaged Bean Name")
public class MyJMXBean {
	List<String> items = new ArrayList<String>();
	boolean shutdown = false;

	@ManagedAttribute(description="getItems")
	public List<String> getItems() {
		return items;
	}

	@ManagedOperation(description="setItems")
	public void setItem(String item) {
		this.items.add(item);
	}

	@ManagedAttribute(description="isShutdown")
	public boolean isShutdown() {
		return shutdown;
	}

	@ManagedOperation(description="setShutdown")
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	@Override
	public String toString() {
		return "MyMXBean [items=" + items + ", shutdown=" + shutdown + "]";
	}
}
