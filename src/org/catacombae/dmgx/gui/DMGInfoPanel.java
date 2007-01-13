/*-
 * Copyright (C) 2006 Erik Larsson
 * 
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

/*
 * DMGInfoPanel.java
 *
 * Created on den 7 november 2006, 09:35
 */

package org.catacombae.dmgx.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author  erik
 */
public class DMGInfoPanel extends javax.swing.JPanel {
    private CardLayout contentsCardLayout;
    private String[] contentTags = {
        "General info", 
        "plist", 
        "Unknown (256 bytes)", 
        "Block map", 
        "Unknown (12 bytes)", 
        "Apple Partition Map", 
        "Unknown (X bytes)", 
        "koly"};
    
    /** Creates new form DMGInfoPanel */
    public DMGInfoPanel() {
        initComponents();
        
        ListModel listModel = new javax.swing.AbstractListModel() {
            public int getSize() { return contentTags.length; }
            public Object getElementAt(int i) { return contentTags[i]; }
        };
        
        contentsList.setModel(listModel);
        
        contentsCardLayout = new CardLayout();
        contentsPane.setLayout(contentsCardLayout);
        
        
        // Now, let's add all components to contentsPane
        Component[] cmp = new Component[contentTags.length];
        cmp[0] = new GeneralInfoPanel();
        cmp[1] = new PlistPanel();
        cmp[2] = new UnknownDataViewPanel();
        cmp[3] = new JPanel(); // N/I
        cmp[4] = new UnknownDataViewPanel();
        cmp[5] = new JPanel(); // N/I
        cmp[6] = new UnknownDataViewPanel();
        cmp[7] = new KolyPanel();
        
        for(int i = 0; i < contentTags.length; ++i)
            contentsPane.add(cmp[i], contentTags[i]);
        
	contentsList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent lse) {
		    //System.out.println(lse);
                    if(!lse.getValueIsAdjusting()) {
                        int index = contentsList.getSelectedIndex();
                        //System.out.println("Switching to " + index + "...");
                        contentsCardLayout.show(contentsPane, contentTags[index]);
                    }
		}
	    });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        listContentsSplitter = new javax.swing.JSplitPane();
        contentsListScroller = new javax.swing.JScrollPane();
        contentsList = new javax.swing.JList();
        contentsPane = new javax.swing.JPanel();

        contentsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "General info", "plist", "Unknown (256 bytes)", "Block map", "Unknown (12 bytes)", "Apple Partition Map", "Unknown (X bytes)", "koly" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        contentsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        contentsListScroller.setViewportView(contentsList);

        listContentsSplitter.setLeftComponent(contentsListScroller);

        contentsPane.setLayout(new java.awt.CardLayout());

        listContentsSplitter.setRightComponent(contentsPane);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(listContentsSplitter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(listContentsSplitter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList contentsList;
    private javax.swing.JScrollPane contentsListScroller;
    private javax.swing.JPanel contentsPane;
    private javax.swing.JSplitPane listContentsSplitter;
    // End of variables declaration//GEN-END:variables
    
}
