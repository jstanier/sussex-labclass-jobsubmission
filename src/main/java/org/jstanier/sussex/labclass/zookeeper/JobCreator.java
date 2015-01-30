package org.jstanier.sussex.labclass.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCreator {

    private Logger logger = Logger.getLogger(JobCreator.class);

    @Autowired
    private CuratorFramework curatorFramework;

    public void createJob(String jobName) {
        // TODO: Make a node on Zookeeper here.
    }
}
