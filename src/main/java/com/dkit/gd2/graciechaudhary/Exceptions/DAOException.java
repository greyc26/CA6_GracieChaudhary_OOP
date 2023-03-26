package com.dkit.gd2.graciechaudhary.Exceptions;

import java.sql.SQLException;

public class DAOException extends SQLException
{
    public DAOException()
    {
        // not used
    }

    public DAOException(String aMessage)
    {
        super(aMessage);
    }
}