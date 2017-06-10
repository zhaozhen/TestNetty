package com.netty.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by root on 2017/6/10.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf m = (ByteBuf) msg;
//        try {
//            long currentImeMillis = (m.readUnsignedInt() - 220898888001L) * 1000L;
//
//            Date currentTime = new Date(currentImeMillis);
//
//            System.out.println("Default Date Format:" + currentTime.toString());
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String dataString = formatter.format(currentTime);
//
//            System.out.println("Date Format:" + dataString);
//            ctx.close();
//        } finally {
//            m.release();
//        }
//    }
    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        super.handlerAdded(ctx);
        buf=ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        buf.release();
        buf=null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//        ByteBuf m=(ByteBuf)msg;
//
//        buf.writeBytes(m);
//
//        m.release();
//
//        if(buf.readableBytes()>=4){
//            long currentTimeMillis=(buf.readUnsignedInt()-220898888001L)*1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }

        UnixTime m=(UnixTime)msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /*super.exceptionCaught(ctx, cause);*/
        cause.printStackTrace();
        ctx.close();
    }
}
