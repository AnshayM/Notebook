# SpringMVC

简单说明



#### springMVC的工作原理

![](https://img-blog.csdnimg.cn/img_convert/de6d2b213f112297298f3e223bf08f28.png)

流程：

1. 客户端发送请求，直接请求到DispatcherServlet
2. DispatcherServlet 根据请求信息调用 HandlerMapping，解析对应的Handler((即Controller))
3. 解析到对应的Handler后,开始由HandlerAdapter适配器处理
4. HandlerAdapter会根据Handler调用真正的处理器开始处理请求，并处理相应的业务逻辑
5. 处理器处理完业务后，会返回一个ModelAndView对象，model时返回的数据对象，view是逻辑上的view
6. ViewResolver会根据逻辑View查找对应的实际的View
7. DispatherServlet把返回的Model传给View进行视图渲染
8. 把view返回给请求者