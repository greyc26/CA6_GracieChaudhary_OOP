package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;

import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Enum.Colours;

import java.util.Scanner;

public class UnrecognizedCommand implements Command{


    @Override
    public String generateResponse(String[] commandParts) {
        return MultithreadedServerDetails.UNRECOGNIZED_COMMAND;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return MultithreadedServerDetails.UNRECOGNIZED_COMMAND;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        System.out.println(Colours.WHITE_BOLD_BRIGHT  + response + Colours.RESET);
    }
}
