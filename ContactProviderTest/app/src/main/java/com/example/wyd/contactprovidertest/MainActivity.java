package com.example.wyd.contactprovidertest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//操作　系统的ContentProvider
//可以在Android4.3(最低版本要求)实体机上跑。
//需要在AndroidManifest.xml文件中声明权限


public class MainActivity extends AppCompatActivity {

    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //activity_main中只有一个button组件search.
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ArrayList<>类是一个数组队列，相当于动态数组。继承于AbstractList.
                //Add方法：将对象添加到ArrayList尾处。
                final ArrayList<String> names = new ArrayList<>();
                final ArrayList<ArrayList<String>> details = new ArrayList<>();

                //查询结果会返回一个Cursor对象，Cursor类是每行的集合。通过MoveToNext()方法，将指针移到下一行。
                //通过Context的getContentResolver类来获取ContentResolver对象，调用该对象的query(Uri,String[],String,String[],String)方法查询。
                //调用COntentResolver的query方法，实际上是调用ContentProvider的query方法。
                //ContactsContract.Contacts.CONTENT_URI是联系人的Uri.
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

                while (cursor.moveToNext()) {
                    //getColumnIndex(String columnName) 返回指定列的名称，如果不存在返回-1
                    //返回该行联系人ID这一列的信息，即：获取到联系人ID
                    String contactid = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //返回该行联系人姓名这一列的信息，即：获取到联系人的姓名
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    names.add(name);

                    //ContactsContract.CommonDataKinds.Phone.CONTENT_URI:联系人电话的Uri
                    //ContactsContract.CommonDataKinds.Phone表示电话号码的类型，这些类型在ContactsContract.Data有定义。比如CONTACT_ID：就是指该数据所属的联系人表中的行的ID号
                    //所以下面这就话的意思就是，在ContactsContract.CommonDataKinds.Phone.CONTENT_URI中搜索行的ID号为contactid的数据。CONTACT_ID是该表中的一个数据类型
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                            "=" + contactid, null, null);

                    ArrayList<String> detail = new ArrayList<>();
                    //一个联系人可能会有多个号码，所以号码的搜索结果也是一个Cursor对象
                    while (phones.moveToNext()) {
                        //NUMBER:是ContactsContract.CommonDataKinds.Phone的一种类型表示，指的是：用户输入的号码
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        detail.add("电话号码：" + phoneNumber);

                    }
                    phones.close();
                        //emails的分析同上
                    Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID +
                            "=" + contactid, null, null);
                    while (emails.moveToNext()) {
                        String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        detail.add("邮件地址：" + emailAddress);
                    }

                    emails.close();
                    details.add(detail);
                }
                cursor.close();

                //LayoutInflater类（它是一个抽象类，一般不直接用）:将布局XML文件实例化到其对应的View对象中。
                //一般使用getLayoutInflater() 或者 getSystemService(Class)检索运行在设备上的LayoutInflater实例。
                //inflate:作用是返回资源布局文件产生的View
                    View resultDialog = getLayoutInflater().inflate(R.layout.result,null);

                //在result布局文件中添加一个ExpandableListView组件，一种用于垂直滚动展示两级列表的视图，和ListView的不同之处就是它可以展示两级列表
                    ExpandableListView list = (ExpandableListView) resultDialog.findViewById(R.id.list);
                //Adapter就是一个接口，ExpandableListAdapter用来给ExpandableListView提供数据、 实例化子布局的。
                //BaseExpandableListAdapter主要是作为ExpandableListView的Adapter.
                    ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

                        private TextView getTextView()
                        {
                            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
                            TextView textView = new TextView(MainActivity.this);
                            textView.setLayoutParams(lp);
                            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                            textView.setPadding(36, 0, 0, 0);
                            textView.setTextSize(20);
                            return textView;
                        }
                        @Override
                        //获得父列表项的数目
                        public int getGroupCount() {
                            return names.size();
                        }

                        //获得子列表项的数目
                        @Override
                        public int getChildrenCount(int groupPosition) {
                            return details.get(groupPosition).size();
                        }

                        @Override
                        //获得父列表项，获取给定组中的数据
                        public Object getGroup(int groupPosition) {
                            return names.get(groupPosition);
                        }

                        //获取子列表项，获取给定组中给定的子项的数据
                        @Override
                        public Object getChild(int groupPosition, int childPosition) {
                            return details.get(groupPosition).get(childPosition);
                        }

                        @Override
                        //获得父列表项的Id
                        public long getGroupId(int groupPosition) {
                            return groupPosition;
                        }

                        //获得子列表项的Id
                        @Override
                        public long getChildId(int groupPosition, int childPosition) {
                            return childPosition;
                        }

                        @Override
                        //该值为真，要求返回的ID稳定
                        public boolean hasStableIds() {
                            return true;
                        }

                        @Override
                        //决定父列表项的外观
                        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                            TextView textView = getTextView();
                            textView.setText(getGroup(groupPosition).toString());
                            return textView;
                        }

                        @Override
                        //决定子列表项的外观
                        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                            TextView textView = getTextView();
                            textView.setText(getChild(groupPosition,childPosition).toString());
                            return textView;
                        }
                        //子列表项是否能否触发事件，返回true则为可以响应点击
                        @Override
                        public boolean isChildSelectable(int groupPosition, int childPosition) {
                            return true;
                        }
                    };
                    list.setAdapter(adapter);
                    //AlertDialog对话框
                //setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener):设置监听，当按钮被按下时，相关处理被调用
                    new AlertDialog.Builder(MainActivity.this).setView(resultDialog).setPositiveButton("确定",null).show();

                }


            });
        }

}


