/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.routing.util;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.util.PMap;

/**
 * Defines bit layout for cars with four wheel drive
 *
 * @author zstadler
 */
public class TruckFlagEncoder extends CarFlagEncoder {
    static final int MAX_TRUCK_SPEED = 100;

    public TruckFlagEncoder() {
        this(5, 5, 0);
    }

    public TruckFlagEncoder(PMap properties) {
        super(properties);
        applyTruckSpecifics();
    }

    public TruckFlagEncoder(String propertiesStr) {
        this(new PMap(propertiesStr));
    }

    public TruckFlagEncoder(int speedBits, double speedFactor, int maxTurnCosts) {
        super(speedBits, speedFactor, maxTurnCosts);
        applyTruckSpecifics();
        init();
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public EncodingManager.Access getAccess(ReaderWay way) {
       return super.getAccess(way);
    }

    @Override
    public String toString() {
        return "truck";
    }

    private void applyTruckSpecifics() {
        //allow delivery only roads. This was disallowed for car
        super.restrictedValues.remove("delivery");
        super.maxPossibleSpeed = MAX_TRUCK_SPEED;
    }
}
