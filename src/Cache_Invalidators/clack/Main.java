package Cache_Invalidators.clack;

import Cache_Invalidators.clack.endpoint.Client;

public class Main
{
    public static void main (String[] args)
    {
        Client client = new Client("demo");
        client.start();
    }
}
