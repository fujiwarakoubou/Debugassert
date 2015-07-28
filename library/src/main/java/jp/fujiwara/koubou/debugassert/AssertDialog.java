/*
 * Copyright (c) 2015 Takahito Fujiwara
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.fujiwara.koubou.debugassert;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Takahito Fujiwara on 2015/07/25.
 */
public class AssertDialog {
    public void showDialog(FragmentManager manager, String message) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = manager.beginTransaction();
        Fragment prev = manager.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = AssertDialogFragment.newInstance(message);
        newFragment.show(ft, "dialog");
    }
}
