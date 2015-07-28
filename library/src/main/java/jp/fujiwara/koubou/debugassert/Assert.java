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
    private FragmentManager manager;// static�ɂ��Ă��܂��̂��l�������A���������[�N���|�������̂ŃV���O���g���ɂ��ĎQ�Ɓi�`�L���j

    private Assert(){}
    public static Assert of() {
        return  ins;
    }

    /**
     * DialogFragment�ɕK�v��FragmentManager���w�肷��
     * @param manager
     */
    public void setFragmentManager(FragmentManager manager) {
        this.manager = manager;

        // ���̂Ƃ���AAssert��p��Activity�����A��������FragmentManager���擾����`�ɂ��������悢�B
        // ���������̏ꍇ�́A���C�u�����v���W�F�N�g�Ȃ̂ŁA���̃v���W�F�N�g���ɂ�<activity>��錾���邱�Ƃ��ł��Ȃ��B
        // �܂�A���̃��C�u�������g�p���鑤�ɐ錾����K�v�����邽�߁A���̃��C�u�������g�����悤�Ƃ���ʂ̃��C�u�����ɖ���^���Ă��܂��B
        // ����Ċg�������c�����߂ɕK�v�ɉ�����FragmentManager���w�肷��悤�ɂ��Ă���B
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
     * message ���A�T�[�g
     * @param message
     */
    public void show(String message) {
        showDialog(message);
    }

    /**
     * object �� null �Ȃ� message ���A�T�[�g
     * @param message
     * @param object
     */
    public void isNull(String message, Object object) {
        if (object == null) {
            showDialog(message);
        }
    }

    /**
     * object �� not null �Ȃ� message ���A�T�[�g
     * @param message
     * @param object
     */
    public void isNotNull(String message, Object object) {
        if (object != null) {
            showDialog(message);
        }
    }

    /**
     * judge �� true �Ȃ� message ���A�T�[�g
     * @param message
     * @param judge
     */
    public void isTrue(String message, boolean judge) {
        if (judge == true) {
            showDialog(message);
        }
    }

    /**
     * judge �� false �Ȃ� message ���A�T�[�g
     * @param message
     * @param judge
     */
    public void isFalse(String message, boolean judge) {
        if (judge == false) {
            showDialog(message);
        }
    }
}
