# Basic-Java-Server

## Introduction

This Java project is a basic server in Java.

The server handle request from client, each client is handled in a different thread.

There is 2 main function, one that start the server, and one that start the client. The client will connect to the server.

The client can be started before the server, but if it try to connect while the server is offline, there will be an error.

When starting the server :

![image](https://user-images.githubusercontent.com/75326864/146203461-ef2ffad0-b8d6-428b-8953-5ea9ac260bca.png)

When starting the client :

![image](https://user-images.githubusercontent.com/75326864/146203517-e730b3c3-375d-4ac7-b5f3-285cd1355f7c.png)

So, you can see the server inform you when it is running.

The Client use a console interface to access function.

## Exemple of the client action

If the client want to see all the car with the make : "Ferrari" :

![image](https://user-images.githubusercontent.com/75326864/146203870-dbeef67d-598f-439d-8df5-eea02aca8c35.png)

The car with the Ferrari make will be displayed to the client.

The server keep track of performed action by client, identifying the client using it's ID.

![image](https://user-images.githubusercontent.com/75326864/146204351-da601983-2bcc-4d30-af4f-f68579566da8.png)

Let's say the client want to buy this Ferrari :

![image](https://user-images.githubusercontent.com/75326864/146204661-1430db55-8d12-4804-801a-4b8c8459cced.png)

The server keep track of this action.

![image](https://user-images.githubusercontent.com/75326864/146204711-bffa9153-449f-4ef5-8cce-4e3f3ab5a24c.png)

And if we try to display that Ferrari again, we can see that the value of ForSales changed :

![image](https://user-images.githubusercontent.com/75326864/146204983-25353220-68ab-4715-8b1b-bba15a2526d9.png)

The car is no longer for sale.

Other function are available to the client, but it work in the same way as the one described earlier.

## Possible evolution

The first evolution for this program would be to save the logs of the server on a txt file or a .dat file.

Another evolution possible is to add a semaphore to control the max number of connection to the server to avoid DDOS attack.










