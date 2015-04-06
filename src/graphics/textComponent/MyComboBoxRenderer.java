package graphics.textComponent;

import graphics.Constants;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class MyComboBoxRenderer extends BasicComboBoxRenderer {
	JComboBox<String> combo;

	public MyComboBoxRenderer(JComboBox<String> combo) {
		this.combo = combo;
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			if (-1 < index) {
				list.setToolTipText(combo.getItemAt(index));
			}
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setFont(list.getFont());
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
