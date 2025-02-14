package com.oblador.storereview;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.Map;
import java.util.HashMap;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.ReviewInfo;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.tasks.Task;

public class StoreReviewModuleImpl {

    public static final String NAME = "RNStoreReview";

    public static void requestReview(ReactApplicationContext context) {
        ReviewManager manager = ReviewManagerFactory.create(context);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                manager.launchReviewFlow(context.getCurrentActivity(), reviewInfo);
            } else {
                Log.w(NAME, "Requesting review failed");
            }
        });
    }

}
