package net.reflxction.impuritybot.core.others;/*
 * * Copyright 2018 github.com/ReflxctionDev
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

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;

public interface IAccess {

    default int getExp(User u) {
        return ImpurityBot.getBot().getExpFile().getInt("Exp." + u.getId() + ".Exp");
    }


    default int getLevel(User u) {
        return ImpurityBot.getBot().getExpFile().getInt("Exp." + u.getId() + ".Level");
    }


    default int getCredits(User u) {
        return ImpurityBot.getBot().getCreditsFile().getInt("Credits." + u.getId() + ".Credits");
    }

    default int getWarnings(User u) {
        return ImpurityBot.getBot().getWarningsFile().getInt("Warnings." + u.getId() + ".Amount");
    }

    default String getIGN(User u) {
        return ImpurityBot.getBot().getIgnsFile().getString("IGNs." + u.getId() + ".IGN");
    }


}
