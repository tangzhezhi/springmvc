package com.saber.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MiniWebServer {

    private static MiniWebServer instance = null;

    public static final int WEB_SERVER_PORT = 8080;

    private int port = WEB_SERVER_PORT;

    private ServerSocket serverSocket = null;

    private ExecutorService executor = null;

    private RawServlet rawServlet = null;

    private boolean activable = false;

    private Thread listenThread = null;

    public static MiniWebServer getInstance() {
        if (instance == null) {
            synchronized (MiniWebServer.class) {
                if (instance == null) {
                    instance = new MiniWebServer();
                }
            }
        }
        return instance;
    }

    private MiniWebServer() {
        this(WEB_SERVER_PORT);
    }

    private MiniWebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            activable = true;
            executor = Executors.newFixedThreadPool(10);
            final CountDownLatch latch = new CountDownLatch(1);

            listenThread = new Thread() {
                public void run() {
                    latch.countDown();
                    MiniWebServer.this.join();
                }
            };
            listenThread.start();

            Thread.sleep(1000);
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {

        if (this.activable == true) {
            this.activable = false;
            try {
                serverSocket.close();
                // listenThread.interrupt();
                if (executor != null) {
                    shutdownAndAwaitTermination(executor);
                    executor = null;
                }
                listenThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // e.printStackTrace();
                // ignore this exeception
                // server socket terminate
            }
        }

    }

    private void join() {
        while (activable == true) {
            try {
                Socket cliSocket = serverSocket.accept();
                executor.execute(new Task(cliSocket));
            } catch (IOException e) {
                // e.printStackTrace();
            }

        }
    }

    public RawServlet getRawServlet() {
        return rawServlet;
    }

    public void setRawServlet(RawServlet rawServlet) {
        this.rawServlet = rawServlet;
    }

    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(1, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    // public static void main(String[] args) {
    // MiniWebServer webServer = new MiniWebServer(80);
    // webServer.startServer();
    // }

    class Task implements Runnable {

        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            // TODO Auto-generated method stub
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                rawServlet.doPost(is, os);
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // e.printStackTrace();
                    }
                }
            }

        }

    }

    public String getToken() {
        return rawServlet.getToken();
    }

}

// 该怎么写，是个问题
// 怎么来组织

