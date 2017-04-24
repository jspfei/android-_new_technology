/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.tt.fancycoverflow.example;

import android.app.Activity;
import android.os.Bundle;

import net.tt.fancycoverflow.R;
import net.tt.fancycoverflow.shared.FancyCoverFlowSampleAdapter;


import  net.tt.fancycoverflow.library.FancyCoverFlow;

public class SimpleExample extends Activity {

    // =============================================================================
    // Child views
    // =============================================================================

    private FancyCoverFlow fancyCoverFlow;

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fancyCoverFlow = (FancyCoverFlow) this.findViewById(R.id.fancyCoverFlow);

        fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter());
        fancyCoverFlow.setUnselectedAlpha(1.0f);
        fancyCoverFlow.setUnselectedSaturation(0.0f);
        fancyCoverFlow.setUnselectedScale(0.5f);
        fancyCoverFlow.setSpacing(50);
        fancyCoverFlow.setMaxRotation(0);
        fancyCoverFlow.setScaleDownGravity(0.2f);
        fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
    }

    // =============================================================================
    // Private classes
    // =============================================================================



}
