package org.jstanier.sussex.labclass.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInput {

    private Logger logger = Logger.getLogger(CommandLineInput.class);

    private BufferedReader bufferedReader;

    @PostConstruct
    public void setup() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String pollForInput() {
        String input = null;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            logger.error("Error when reading input.", e);
        }
        return input;
    }

}
