package dk.via.text;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiCaseClient {
    public RmiCaseClient() {
    }

    public String convert(String text, boolean upper) throws RemoteException, MalformedURLException, NotBoundException {
        TextConverter server = (TextConverter) Naming.lookup("rmi://localhost:1099/Case");
        if (upper) {
            return server.toUpperCase(text);
        }
        return server.capitalize(text);
    }
}
