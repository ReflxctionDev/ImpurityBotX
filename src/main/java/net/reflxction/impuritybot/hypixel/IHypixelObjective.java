package net.reflxction.impuritybot.hypixel;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.request.Request;
import net.hypixel.api.request.RequestBuilder;
import net.hypixel.api.request.RequestParam;
import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.Callback;
import net.reflxction.impuritybot.utils.lang.APIUtils;

/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class IHypixelObjective {

    public String getRank(String player) {
        final String[] rank = new String[1];
        HypixelAPI.getInstance().setApiKey(APIUtils.API_KEY);

        Request request = RequestBuilder.newBuilder(RequestType.PLAYER)
                .addParam(RequestParam.PLAYER_BY_NAME, player)
                .createRequest();
        HypixelAPI.getInstance().getAsync(request, (Callback<PlayerReply>) (failCause, result) -> {
            if (failCause != null) {
                failCause.printStackTrace();
            } else {
                rank[0] = result.getPlayer().get("newPackageRank").getAsString();
                if (rank[0] == null) {
                    rank[0] = "Default";
                }
                if (rank[0].equalsIgnoreCase("VIP_PLUS")) {
                    rank[0] = "VIP+";
                }
                if (rank[0].equalsIgnoreCase("MVP_PLUS")) {
                    rank[0] = "MVP+";
                }
                if (result.getPlayer().get("monthlyPackageRank").getAsString().equalsIgnoreCase("SUPERSTAR")) {
                    rank[0] = "MVP++";
                }
                HypixelAPI.getInstance().finish();
            }
        });
        return rank[0];
    }
}
