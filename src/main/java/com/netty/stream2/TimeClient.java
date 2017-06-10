package com.netty.stream2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by root on 2017/6/10.
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(workGroup);

            b.channel(NioSocketChannel.class);

            b.option(ChannelOption.SO_KEEPALIVE, true);

            b.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                }
//
//                @Override
//                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientHandler());
//                }
            });

            //启动客户端
            ChannelFuture f = b.connect(host, port).sync();
            //等待连接
            f.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}
