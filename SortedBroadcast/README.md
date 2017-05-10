这个例子是用来　发送广播的
- 广播（Broadcast）分为两种：
  - 普通的（sendBroadcast）,例如[SimpleBroadcast](https://github.com/wyd20162016/AndroidDevelopment/tree/master/SimpleBroadcast).他可以同一时刻被所有接受者接收到，效率高．缺点是接收者不能将处理的结果传递给其他接收者，并且无法终止传播．
  - 有序的(sendOrderedBroadcast),这个例子就是采用的这种方法．他们（BroadcastRecevier）按照优先级（在AndroidManifest,xml文件中Intent中设置）依次接收．可以将处理的结果传递给下一个BroadcastRecevier.同时还能中断广播的传播（abortBroadcast）.

> 这个例子还是采用的在AndroidManifest.xml文件中激发BroadcastRecevier．
