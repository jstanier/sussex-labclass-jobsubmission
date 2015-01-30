package org.jstanier.sussex.labclass.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.jstanier.sussex.labclass.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZookeeperPathCreator {

    private Logger logger = Logger.getLogger(ZookeeperPathCreator.class);

    @Autowired
    private CuratorFramework curatorFramework;

    public void createPathIfNeeded() {
        try {
            logger.info("Attempting to create path on Zookeeper");
            curatorFramework.create().creatingParentsIfNeeded().forPath(Constants.ZOOKEEPER_PATH);
            logger.info("Path created successfully");
        } catch (NodeExistsException e) {
            logger.info("Path already exists - doing nothing.");
        } catch (Exception e) {
            logger.error("Something went wrong.", e);
        }
    }
}
