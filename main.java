import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
interface SupportHandler {
    void handleRequest(SupportRequest request);
}
class HardwareHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.HARDWARE) {
            System.out.println("Handling hardware issue: " + request.getDescription());
            // Process the request here
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for this type of request.");
        }
    }

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class SoftwareHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.SOFTWARE) {
            System.out.println("Handling software issue: " + request.getDescription());
            // Process the request here
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for this type of request.");
        }
    }

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class NetworkHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.NETWORK) {
            System.out.println("Handling network issue: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for this type of request.");
        }
    }

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
class SupportRequest {
    public enum Type { HARDWARE, SOFTWARE, NETWORK }

    private int id;
    private String description;
    private Type type;
    private int priority;

    public SupportRequest(int id, String description, Type type, int priority) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
public class projectbek {
    public static void main(String[] args) {
        HardwareHandler hardwareHandler = new HardwareHandler();
        SoftwareHandler softwareHandler = new SoftwareHandler();
        NetworkHandler networkHandler = new NetworkHandler();

        hardwareHandler.setNextHandler(softwareHandler);
        softwareHandler.setNextHandler(networkHandler);

        SupportRequest hardwareRequest = new SupportRequest(1, "Hardware issue", SupportRequest.Type.HARDWARE, 1);
        SupportRequest softwareRequest = new SupportRequest(2, "Software issue", SupportRequest.Type.SOFTWARE, 2);
        SupportRequest networkRequest = new SupportRequest(3, "Network issue", SupportRequest.Type.NETWORK, 3);

        hardwareHandler.handleRequest(hardwareRequest);
        hardwareHandler.handleRequest(softwareRequest);
        hardwareHandler.handleRequest(networkRequest);
    }
}
