package com.example.myapplication.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * @author lixiaolong
 * @date 2018/9/10
 */
public class ContactUtil {

    private final Activity mActivity;

    public ContactUtil(Activity activity) {
        this.mActivity = activity;
    }

    private void getList() {
        ContentResolver contentResolver = mActivity.getContentResolver();
        //拿到所有通讯录的姓名
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        while (cursor.moveToNext()) {
            String _id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));  //拿到id
            String display_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));  //拿到姓名

            Uri uriData = Uri.parse("content://com.android.contacts/raw_contacts/" + _id + "/data");  //根据id拿到这个对象的所有信息
            Cursor contactsInfo = contentResolver.query(uriData, null, null, null, null);
            while (contactsInfo.moveToNext()) {
                String mimetype = contactsInfo.getString(contactsInfo.getColumnIndex("mimetype"));
                String data1 = contactsInfo.getString(contactsInfo.getColumnIndex("data1"));
                int phoneType = contactsInfo
                        .getInt(contactsInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)); // 手机
                if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {

                }
            }

        }
    }

    public void read() {
        Cursor cursor = null;
        try {
            //cursor指针 query询问 contract协议 kinds种类
            cursor = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                L.e(displayName+number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
    }

}
