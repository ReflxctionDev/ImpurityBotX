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
package net.reflxction.impuritybot.core.others;

import me.brokenearth.core.container.Container;
import me.brokenearth.core.yaml.YamlConfiguration;
import me.brokenearth.core.yaml.YamlReader;
import me.brokenearth.core.yaml.YamlWriter;
import net.dv8tion.jda.core.entities.Role;
import net.reflxction.impuritybot.utils.GuildUtils;

import java.io.File;

public class Roles {

    public static final Role D_MEMBER = GuildUtils.roleById("434630886319980554");
    public static final Role UNREGISTERED = GuildUtils.roleById("433595739416035338");
    public static final Role BOT = GuildUtils.roleById("394214692362387456");
    public static final Role CAN_MUTE = GuildUtils.roleById("430447415515152384");
    public static final Role I_MEMBER = GuildUtils.roleById("413362035334840324");
    public static final Role BOT_UPDATES = GuildUtils.roleById("458614476682297344");
    public static final Role HELPER = GuildUtils.roleById("415187616774881282");
    public static final Role EVENTS_TEAM = GuildUtils.roleById("445237461912911883");
    public static final Role STAFF = GuildUtils.roleById("454636856387043329");
    public static final Role MUTED = GuildUtils.roleById("430697317537153034");
    public static final Role ADMIN = GuildUtils.roleById("367318849978105857");
    public static final Role OFFICER = GuildUtils.roleById("364809414743687169");
    public static final Role MUTE_ACCESS = GuildUtils.roleById("458737084467773471");

    public static YamlConfiguration roleFileConfiguration() {
       return YamlConfiguration.loadConfig(new File(Container.getFile().getPath() + "/roles.yml"));
    }

    public static Role getRoleFromYaml(String rolename) {
        YamlReader reader = roleFileConfiguration().getReader();
        String roleId = String.valueOf(reader.get(rolename));
        return GuildUtils.roleById(roleId);
    }

    public static Role getRoleFromId(String roleid) {
        YamlReader reader = roleFileConfiguration().getReader();
        return GuildUtils.roleById(roleid);
    }

    public static void addRoleToYaml(String rolename, String roleid) {
        YamlWriter writer = roleFileConfiguration().getWriter();
        writer.write(rolename, roleid);
    }

}
