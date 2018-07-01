/*
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
package net.reflxction.impuritybot.utils.guild;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.RestAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility manager class to help actions to abide by JDA's thread
 *
 * @author Reflxction
 * @see net.dv8tion.jda.core.requests.RestAction
 * @see net.dv8tion.jda.core.requests.restaction.AuditableRestAction
 */
public class GuildManager {

    private List<RestAction> restActions = new ArrayList<>();

    public GuildManager addRolesToUser(User user, Role... roles) {
        for (Role role : roles) {
            restActions.add(GuildUtils.guild().getController().addSingleRoleToMember(userToMember(user), role));
        }
        return this;
    }

    public GuildManager removeRolesFromUser(User user, Role... roles) {
        for (Role role : roles) {
            restActions.add(GuildUtils.guild().getController().removeSingleRoleFromMember(userToMember(user), role));
        }
        return this;
    }

    public Member userToMember(User user) {
        return GuildUtils.guild().getMember(user);
    }

    public void queueActions() {
        restActions.forEach(RestAction::queue);
    }

}
