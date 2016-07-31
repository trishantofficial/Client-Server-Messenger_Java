A Messenger in Java, based on basic Client-Server model. It operates on the first layer of TCP/IP model.

How it works:-

1.) The server opens a port(5217) and listens at it to all clients requesting a connection, and creates new connections simultaneously.<br>
2.) A client dispatches packets to the server using DataOutputStream and upon receiving the data.<br>
3.) The server distinguishes between the request using String tokenizers and further decides upon the action to be taken on the import, in order to complete the message transmission.