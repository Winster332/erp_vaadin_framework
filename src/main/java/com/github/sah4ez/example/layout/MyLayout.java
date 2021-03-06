package com.github.sah4ez.example.layout;

import com.github.sah4ez.core.elements.BottomPage;
import com.github.sah4ez.core.elements.Logic;
import com.github.sah4ez.core.elements.Workspace;
import com.github.sah4ez.example.data.Element;
import com.github.sah4ez.example.data.ElementContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.tepi.filtertable.FilterTable;

import java.util.Set;

/**
 * Created by aleksandr on 20.12.16.
 */
public class MyLayout extends Workspace {
    private ElementContainer container = new ElementContainer();
    private MyTabSheet tabSheet;
    private MyMenu menu;
    private BottomPage<Button, FilterTable> page1;
    private BottomPage<Label, FilterTable> page2;

    public MyLayout(Logic logic) {
        super(logic, "MyLayout1");
        tabSheet = new MyTabSheet(logic);

        page1 = new BottomPage<Button, FilterTable>(new Button("hello"), getTable(), "tab", FontAwesome.AMAZON) {

            @Override
            public void action(Event event) {
                Set<Element> set = ((Set<Element>) getExternalComponet().getValue());
                Element element = set.stream().findFirst().get();
                element.setName("nnnn");
                System.out.println(element);
                container.refresh();
            }
        };

        page2 = new BottomPage<Label, FilterTable>(new Label("lllll"), getTable(), "tab2", FontAwesome.AMAZON) {
            @Override
            public void action(Event event) {

            }
        };

        tabSheet.addPage(page1);
        tabSheet.addPage(page2);

        menu = new MyMenu("myMenu", this);
        logic.setDataToTable(container.loadAllData(), getTable());
        setBottomTabs(tabSheet);
        setNavigator(menu);
    }

    @Override
    protected ItemClickEvent.ItemClickListener editTableItemClick() {
        return itemClickEvent -> {
        };
    }

    @Override
    protected ItemClickEvent.ItemClickListener selectTableItemClick() {
        return itemClickEvent -> {
            page1.getComponent().setCaption(((Element) itemClickEvent.getItemId()).getId().toString() + " "
                    + ((Element) itemClickEvent.getItemId()).getName());
        };
    }

    @Override
    protected ItemClickEvent.ItemClickListener editTableAllItemClick() {
        return itemClickEvent -> {
        };
    }

    @Override
    protected ItemClickEvent.ItemClickListener selectTableAllItemClick() {
        return itemClickEvent -> {
        };
    }

    @Override
    public void clearLayout() {

    }

    public void initBottomTabs() {
    }

    public void initMenuNavigator() {
    }
}
