package org.jstanier.sussex.labclass.commandline;

import org.apache.log4j.Logger;
import org.jstanier.sussex.labclass.zookeeper.JobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineOrchestrator {

    private boolean stopping = false;

    private Logger logger = Logger.getLogger(CommandLineOrchestrator.class);

    @Autowired
    private CommandLineInput commandLineInput;

    @Autowired
    private JobCreator jobCreator;

    public void begin() {
        while (!stopping) {
            System.out.print("Job name (or type exit to quit): ");
            String jobName = commandLineInput.pollForInput();
            if (jobName == null) {
                continue;
            } else if (jobName.equals("exit")) {
                stopping = true;
            } else {
                logger.info("Creating job called " + jobName);
                jobCreator.createJob(jobName);
            }
        }
    }
}
