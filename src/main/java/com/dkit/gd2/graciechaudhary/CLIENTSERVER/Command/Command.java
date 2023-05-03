package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;


import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Scanner;

public interface Command
{
    public String generateResponse(String[] commandParts);
    public String generateRequest(Scanner keyboard) throws DAOException;
    public void handleResponse(String response);
}
