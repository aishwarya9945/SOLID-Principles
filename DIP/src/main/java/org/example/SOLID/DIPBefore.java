package org.example.SOLID;

// Application Layer
class MyMessenger1 {
    TCPProtocolHandler tcpProtocolHandler = new TCPProtocolHandler();

    public void send(String to, String message) {
        tcpProtocolHandler.sendMessage("Message to - " + to + ", message - " + message);
    }
}

// Transport Layer
class TCPProtocolHandler1 {
    public void sendMessage(String message) {
        System.out.println("TCPProtocol Handler Sending Message - " + message);
    }
}

public class DIPBefore {
    public static void main(String[] args) {
        MyMessenger1 messenger = new MyMessenger1();
        messenger.send("Alice", "SOLID is easy");
    }
}
