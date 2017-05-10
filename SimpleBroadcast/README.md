这个例子是用来　发送广播的
- BroadcasstReceiver本质上是一个系统级别的监听器，只要存在与之匹配的Intent被发送出来，该BroadcastReceiver就会被激发．
- BroadcastReceiver用于接收程序所发出的Broadcast Intent.
- 指定BroadcasstReceiver能匹配的Intent有两种方式：
  - 代码指定，调用Context的registerReceiver(BroadcastReceiver receiver, IntentFilter filter)指定
  - 在AndroidManifest.xml文件中配置（本例中采用的是这种方法）
