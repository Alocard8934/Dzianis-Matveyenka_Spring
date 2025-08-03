
package com.dmdev.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {
    
    @EventListener(condition= "#root.args[0].accespType.name()== 'READ'")
    @Order(10)
    public void accepEntity(EntityEvent entityEvent){
        System.out.println("Entity: " + entityEvent);
    }
}
