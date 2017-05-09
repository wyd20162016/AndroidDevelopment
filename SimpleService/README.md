这个例子是用来 打开一个service
- Android系统运行service有两种方式，这个例子是采用的是:
  - 通过调用Context中的startService()方法．访问者（activity）退出后，service也仍然运行．（这个例子采用的是这种方法）
  - 通过调用Context中的bindService()方法，访问者(acticity)退出后，service也就终止了．
