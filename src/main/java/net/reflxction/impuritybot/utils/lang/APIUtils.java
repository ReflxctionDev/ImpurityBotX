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
package net.reflxction.impuritybot.utils.lang;

import java.util.UUID;

public class APIUtils {

    public static final UUID API_KEY = UUID.fromString("a7a3b3e5-04ec-49b8-9a2a-0ddcf7bb35e2");

    public static final String API_KEY_STRING = "a7a3b3e5-04ec-49b8-9a2a-0ddcf7bb35e2";


    public static void await() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public interface UUIDList {
        UUID HYPIXEL = UUID.fromString("f7c77d99-9f15-4a66-a87d-c4a51ef30d19");
    }

}
