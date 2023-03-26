package com.dkit.gd2.graciechaudhary.Enum;

/**
 * Hello world!
 *
 */
public class ColoursTrial
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
//
//        System.out.println(ANSI_GREEN_BACKGROUND + "This text has a green background but default text!" + ANSI_RESET);
//        System.out.println(ANSI_RED + "This text has red text but a default background!" + ANSI_RESET);
//        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_RED + "This text has a green background and red text!" + ANSI_RESET);
        System.out.print(Colours.BLACK_BOLD);
        System.out.println("Black_Bold");
        System.out.print(Colours.RESET);

        System.out.print(Colours.YELLOW);
        System.out.print(Colours.WHITE_BACKGROUND);
        System.out.println("YELLOW & BLUE");
        System.out.print(Colours.RESET);

        System.out.print(Colours.YELLOW);
        System.out.println("YELLOW");
        System.out.print(Colours.RESET);


    }

}
