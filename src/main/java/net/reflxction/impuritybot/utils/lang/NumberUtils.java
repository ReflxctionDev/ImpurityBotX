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

import java.util.*;

public class NumberUtils {

    public boolean isBetween(int n, int minimum, int maximum) {
        return n > minimum && n < maximum;
    }

    public int randomBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public int random(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public List<Integer> leastToGreatest(int[] a) {
        List<Integer> intList = new ArrayList<>();
        for (int index = 0; index < a.length; index++) {
            intList.add(a[index]);
        }
        Collections.sort(intList);
        return intList;
    }

    public List<Integer> greatestToLeast(int[] a) {
        List<Integer> intList = new ArrayList<>();
        for (int index = 0; index < a.length; index++) {
            intList.add(a[index]);
        }
        Collections.sort(intList);
        Collections.reverse(intList);
        return intList;
    }


    public List<Integer> leastToGreatest(List<Integer> a) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            intList.add(a.get(i));
        }
        Collections.sort(intList);
        return intList;
    }

    public List<Integer> greatestToLeast(List<Integer> a) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            intList.add(a.get(i));
        }
        Collections.sort(intList);
        Collections.reverse(intList);
        Arrays.asList(new int[]{});
        return intList;
    }

    public boolean isNegative(double d) {
        return String.valueOf(d).contains("-");
    }

}
