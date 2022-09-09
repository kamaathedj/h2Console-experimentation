package com.kamaathedj.RustedRoof;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class H2Config {
    private org.h2.tools.Server webServer;
    private org.h2.tools.Server tcpServer;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException{
        this.webServer = org.h2.tools.Server.createWebServer("-webPort","8082","-tcpAllowOthers").start();
        this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort","9092","-tcpAllowOthers").start();
    }
    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop(){
        this.webServer.stop();
        this.tcpServer.stop();
    }
}

