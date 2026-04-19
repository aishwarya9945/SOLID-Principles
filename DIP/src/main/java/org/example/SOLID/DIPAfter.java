package org.example.SOLID;

// Abstraction
interface ProtocolHandler {
    void sendMessage(String message);
}

// Transport Layer
class TCPProtocolHandler implements ProtocolHandler {
    @Override
    public void sendMessage(String message) {
        System.out.println("TCPProtocol Handler Sending Message - " + message);
    }
}

class UDPProtocolHandler implements ProtocolHandler {
    @Override
    public void sendMessage(String message) {
        System.out.println("UDPProtocol Handler Sending Message - " + message);
    }
}

// Application Layer
class MyMessenger {
    private ProtocolHandler protocolHandler;

    // Dependency injected via constructor
    public MyMessenger(ProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    public void send(String to, String message) {
        protocolHandler.sendMessage("Message to - " + to + ", message - " + message);
    }
}

public class DIPAfter {
    public static void main(String[] args) {
        // Choose protocol at runtime
        ProtocolHandler tcpHandler = new TCPProtocolHandler();
        ProtocolHandler udpHandler = new UDPProtocolHandler();

        MyMessenger messenger1 = new MyMessenger(tcpHandler);
        messenger1.send("Alice", "SOLID is easy with TCP");

        MyMessenger messenger2 = new MyMessenger(udpHandler);
        messenger2.send("Bob", "SOLID is easy with UDP");
    }
}
