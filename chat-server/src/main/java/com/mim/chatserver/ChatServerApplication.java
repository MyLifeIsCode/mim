package com.mim.chatserver;

import com.mim.chatserver.bootstrap.ChatServer;
import io.netty.channel.ChannelFuture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class ChatServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class,args);
    }
    @Override
    public void run(String... strings) throws Exception {
        int port = 9999;
        final ChatServer chatServer = new ChatServer();
        ChannelFuture future = chatServer.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                chatServer.destory();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
