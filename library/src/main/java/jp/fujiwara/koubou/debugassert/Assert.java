/*
 * Copyright (c) 2015 Takahito Fujiwara
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.fujiwara.koubou.debugassert;

import android.app.FragmentManager;
import android.util.Log;

/**
 * Created by Takahito Fujiwara on 2015/07/23.
 */
public class Assert {
    private static final String TAG = "Assert";
    private static final Assert ins = new Assert();
    private FragmentManager manager;// staticにしてしまうのも考えたが、メモリリークが怖かったのでシングルトンにして参照（チキン）

    private Assert(){}
    public static Assert of() {
        return  ins;
    }

    /**
     * DialogFragmentに必要なFragmentManagerを指定する
     * @param manager
     */
    public void setFragmentManager(FragmentManager manager) {
        this.manager = manager;

        // 実のところ、Assert専用のActivityを作り、そこからFragmentManagerを取得する形にした方がよい。
        // しかしその場合は、ライブラリプロジェクトなので、このプロジェクト内には<activity>を宣言することができない。
        // つまり、このライブラリを使用する側に宣言する必要があるため、このライブラリを拡張しようとする別のライブラリに問題を与えてしまう。
        // よって拡張性を残すために必要に応じてFragmentManagerを指定するようにしてある。
    }

    private void showDialog(String message) {
        Log.d(TAG, message);
        if (manager == null) {
            Log.w(TAG, "FragmentManager of Assert is null. #setFragmentmanager is requiered.");
            return;
        }
        new AssertDialog().showDialog(manager, "test");
    }

    /**
     * message をアサート
     * @param message
     */
    public void show(String message) {
        showDialog(message);
    }

    /**
     * object が null なら message をアサート
     * @param message
     * @param object
     */
    public void isNull(String message, Object object) {
        if (object == null) {
            showDialog(message);
        }
    }

    /**
     * object が not null なら message をアサート
     * @param message
     * @param object
     */
    public void isNotNull(String message, Object object) {
        if (object != null) {
            showDialog(message);
        }
    }

    /**
     * judge が true なら message をアサート
     * @param message
     * @param judge
     */
    public void isTrue(String message, boolean judge) {
        if (judge == true) {
            showDialog(message);
        }
    }

    /**
     * judge が false なら message をアサート
     * @param message
     * @param judge
     */
    public void isFalse(String message, boolean judge) {
        if (judge == false) {
            showDialog(message);
        }
    }
}
