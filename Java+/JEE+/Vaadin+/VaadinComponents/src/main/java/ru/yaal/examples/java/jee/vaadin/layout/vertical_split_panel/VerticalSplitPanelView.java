package ru.yaal.examples.java.jee.vaadin.layout.vertical_split_panel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("unused")
public class VerticalSplitPanelView extends VerticalSplitPanel implements View {

    public VerticalSplitPanelView() {
        setFirstComponent(new Button("Button"));
        setSecondComponent(new Button("Button2"));
        setSplitPosition(20, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
