package anshay.notebook.learn.io;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Netty使用demo
 *
 * @author machao
 * @date 2022/6/23
 */
public class NettyTest {
	public void testNetty() {
		// 创建一组线性
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			// 初始化 Server
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(group);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.localAddress(new InetSocketAddress("localhost", 9999));

			// 设置收到数据后的处理的 Handler
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new MyHandler());
				}
			});
			// 绑定端口，开始提供服务
			ChannelFuture channelFuture = serverBootstrap.bind().sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
