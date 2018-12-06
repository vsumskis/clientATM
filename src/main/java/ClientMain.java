import client.Socketor;
import client.Support;

import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) {
        Socketor socketor = new Socketor("127.0.0.1", 8000);
        Support support = new Support(socketor);
        support.start();


        //List<String> request = new ArrayList<String>();
        //request.add("CreateCard");
       // request.add("330739");
        //request.add("5641");
       // request.add("10");
        //socketor.sendRequest(request);
        //String response = socketor.getResponse();
        //System.out.println(request.get(0));
        //System.out.println(response);
        //socketor.close();

    }
}
