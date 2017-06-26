package com.dima.weather.screen.general;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dima.weather.R;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by Liashenko Dima on 16.02.2017.
 */

public class LoadingDialog extends DialogFragment {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static String mMessage;

    @SuppressLint("ValidFragment")
    public LoadingDialog(String message) {
        this.mMessage = message;

    }

    public LoadingDialog() {
    }


    @NonNull
    public static LoadingView view(@NonNull FragmentManager fm) {
        return new LoadingDialogView(fm);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loading, null);
        if (mMessage != null && !mMessage.isEmpty()) {
            TextView mTextMessage = (TextView) view.findViewById(R.id.tv_message);
            mTextMessage.setText(mMessage);
        }
        builder.setView(view);
        return builder.create();
    }

    private static class LoadingDialogView implements LoadingView {

        private final FragmentManager mFm;

        private final AtomicBoolean mWaitForHide;

        private LoadingDialogView(@NonNull FragmentManager fm) {
            mFm = fm;
            boolean shown = fm.findFragmentByTag(LoadingDialog.class.getName()) != null;
            mWaitForHide = new AtomicBoolean(shown);
        }

        @Override
        public void showLoadingIndicator() {
            if (mWaitForHide.compareAndSet(false, true)) {
                if (mFm.findFragmentByTag(LoadingDialog.class.getName()) == null) {
                    DialogFragment dialog = new LoadingDialog();
                    dialog.show(mFm, LoadingDialog.class.getName());
                }
            }
        }


        @Override
        public void showLoadingIndicator(String message) {
            mMessage = message;
            if (mWaitForHide.compareAndSet(false, false)) {
                if (mFm.findFragmentByTag(LoadingDialog.class.getName()) == null) {
                    DialogFragment dialog = new LoadingDialog(message);
                    dialog.show(mFm, LoadingDialog.class.getName());
                }
            }
        }

        @Override
        public void hideLoadingIndicator() {
            if (mWaitForHide.compareAndSet(true, false)) {
                HANDLER.post(new HideTask(mFm));
            }
        }
    }

    private static class HideTask implements Runnable {

        private final Reference<FragmentManager> mFmRef;

        private int mAttempts = 10;

        public HideTask(@NonNull FragmentManager fm) {
            mFmRef = new WeakReference<>(fm);
        }

        @Override
        public void run() {
            HANDLER.removeCallbacks(this);
            final FragmentManager fm = mFmRef.get();
            if (fm != null) {
                final LoadingDialog dialog = (LoadingDialog) fm.findFragmentByTag(LoadingDialog.class.getName());
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss();
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, 300);
                }
            }
        }
    }

}
