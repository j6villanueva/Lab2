import java.io.IOException;
import java.net.URI;
import java.util.HashSet; 
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    //HashSet<String> a = new HashSet<String>();

    List<String> sList = new ArrayList<>();

    public String handleRequest(URI url) {
        List<String> subList = new ArrayList<>();
        // if (url.getPath().equals("/")) {
            // return String.format("Number: %d", num);
        // } else if (url.getPath().equals("/add")) {
        //     num += 1;
        //     return String.format("Number incremented!");
        // } else {

            if (url.getPath().contains("/add")) {
                System.out.println("Path: " + url.getPath());
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    sList.add(parameters[1]);
                    return String.format("Added Word");
                }
            }
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    // for (String string : sList) {
                        // if (string.contains(parameters[1])){

                        // }
                    // }
                    if(sList.contains(parameters[1])){
                        for(int index = 0; index<sList.size(); index++){
                        if(sList.get(index).contains(parameters[1])){
                        subList.add(sList.get(index));
                        }
                    }

                    }
                    return subList.toString();
                    //return String.format("Added Word");
                }
            }
            return "404 Not Found!";
        }
    }
// }

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
