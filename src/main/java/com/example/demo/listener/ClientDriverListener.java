package com.example.demo.listener;

import com.opcooc.storage.event.ClientDriverEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

/**
 * clientDriver变更通知 (需要注入IOC)
 */
@Slf4j
public class ClientDriverListener {

    @EventListener
    public void addEventListener(ClientDriverEvent event) {
        log.info("opcooc - storage - event listener, event driverName: [{}], type: [{}]", event.getDriverName(), event.getType());
    }

}
