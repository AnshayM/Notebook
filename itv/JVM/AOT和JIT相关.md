#### 为什么spring6和springBoot3强制要求jdk17

1. spring6以及springBoot3考虑到17是长期维护并且面试，还可以拥有java9-17之间发布多新特性，比如模块化、JFR、Revord、Vector Api以及高Sealed Class等。
2. GraalVm Compiler是java编写的，尝试作为 JIT C++ Compiler的替换者，实际上在java17之前内部实验性功能
3. java过去之所以没有全面编译，并非占用内存增加和启动时间变慢，GraalVM Native Image也需要很长的编译时间，消耗很大的内存。主要问题，GraalVm也无法解决，尤其JVM字节码动态性，比如CgLib动态申城或者java反射的不确定心。对于大部分业务代码GraalVM aot编译基本可以完成静态话，主要在框架代码上比较麻烦，比如spring stack，所以spring native做了大量复制工作，帮助GraalVM提升稳定性，然而部分代码还需要自行声明
4. Aot编译器过去就存在，比如Excelsior JET、GNU compiler for java
5. JIT预热可以通过代码或者负载权重来实现，方法很多比如Alibaba GragonWell JVM

