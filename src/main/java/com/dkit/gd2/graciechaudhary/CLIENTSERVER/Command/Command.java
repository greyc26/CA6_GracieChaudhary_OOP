package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;


import com.dkit.gd2.graciechaudhary.Core.NetworkMessage;

import java.util.Scanner;

public interface Command
{
    public String generateResponse(NetworkMessage incomingMessage);
    public String generateRequest(Scanner keyboard);
    public void handleResponse(String response);
}
