
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

package net.reflxction.impuritybot.window;

import net.reflxction.impuritybot.main.ImpurityBot;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BotFrame extends JFrame {

    final ImpurityBot s;

    public BotFrame(final ImpurityBot s) {
        this.s = s;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (s.isBotEnabled()) {
                    JOptionPane.showMessageDialog(null, "You must disable the bot before closing!");
                } else {
                    dispose();
                }
            }
        });
        JPanel MainPanel = new JPanel();
        MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        MainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(MainPanel);

        MainPanel.add(new BotPanel(s));
    }
}
