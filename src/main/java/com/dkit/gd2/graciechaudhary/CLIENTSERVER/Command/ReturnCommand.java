package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;

import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Scanner;

public class ReturnCommand implements Command{
    @Override
    public String generateResponse(String[] commandParts) {
        return Colours.MAGENTA_BOLD_BRIGHT+"Returning..."+Colours.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {
        return MultithreadedServerDetails.RETURN;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        System.out.println(Colours.WHITE_BOLD_BRIGHT  + response + Colours.RESET);
    }
}
