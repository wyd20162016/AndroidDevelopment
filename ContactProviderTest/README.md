这个例子是用来　操作　系统的ContentProvider
- 获得联系人信息（联系人姓名，号码，Email）
- 由于是系统提供的Contenprovider,所以不用创建Contenprovider,只需要调用Context的getContentResolver()获取ContentResolver对象，进而操作ContentProvider的数据
