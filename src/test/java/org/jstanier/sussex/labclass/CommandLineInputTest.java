package org.jstanier.sussex.labclass;

import java.io.BufferedReader;
import java.io.IOException;

import org.jstanier.sussex.labclass.commandline.CommandLineInput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineInputTest {

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private CommandLineInput commandLineInput;

    @Test
    public void whenPollingForInput_IfAnIOExceptionOccurs_NullIsReturned() throws IOException {
        Mockito.when(bufferedReader.readLine()).thenThrow(new IOException());
        String input = commandLineInput.pollForInput();
        Assert.assertNull(input);
    }

    @Test
    public void whenPollingForInput_TheExpectedInputIsReturned() throws IOException {
        Mockito.when(bufferedReader.readLine()).thenReturn("hello");
        String input = commandLineInput.pollForInput();
        Assert.assertEquals("hello", input);
    }
}
