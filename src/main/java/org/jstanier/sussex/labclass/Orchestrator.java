package org.jstanier.sussex.labclass;

import javax.annotation.PostConstruct;

import org.jstanier.sussex.labclass.commandline.CommandLineOrchestrator;
import org.jstanier.sussex.labclass.zookeeper.ZookeeperPathCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Orchestrator {

    @Autowired
    private ZookeeperPathCreator zookeeperPathCreator;

    @Autowired
    private CommandLineOrchestrator commandLineOrchestrator;

    @PostConstruct
    public void setup() {
        zookeeperPathCreator.createPathIfNeeded();
        commandLineOrchestrator.begin();
    }
}
