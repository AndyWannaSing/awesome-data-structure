package awesome.data.structure.socket;

import jdk.net.Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 套接字
 *
 * @author: Andy
 * @time: 2019/6/25 14:56
 * @since
 */
public class SocketServer {
    public static void main(String[] args) {
        int port = 6379;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new MyRuns(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyRuns implements Runnable{

        Socket socket;
        BufferedReader reader;
        BufferedWriter writer;

        public MyRuns(Socket socket) {
            super();
            this.socket = socket;
        }

        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//读取客户端消息
                writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//向客户端写消息
                String lineString="";

                while ((lineString = reader.readLine()) != null) {
                    System.out.print(lineString);
                    writer.flush();
                }
                writer.write("OK");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader!=null) {
                        reader.close();
                    }
                    if (writer!=null) {
                        writer.close();
                    }
                    if (socket!=null) {
                        socket.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

    }
}
