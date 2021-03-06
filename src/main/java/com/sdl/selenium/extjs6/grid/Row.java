package com.sdl.selenium.extjs6.grid;

import com.sdl.selenium.WebLocatorUtils;
import com.sdl.selenium.extjs6.form.CheckBox;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.table.AbstractCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row extends com.sdl.selenium.web.table.Row {
    private static final Logger LOGGER = LoggerFactory.getLogger(Row.class);

    public Row() {
        super();
        setTag("table");
    }

    public Row(WebLocator container) {
        this();
        setContainer(container);
    }

    public Row(WebLocator container, int indexRow) {
        this(container);
        setPosition(indexRow);
    }

    public Row(WebLocator table, String searchElement, SearchType... searchTypes) {
        this(table);
        setText(searchElement, searchTypes);
    }

    public Row(WebLocator table, AbstractCell... cells) {
        this(table);
        List<AbstractCell> collect = Stream.of(cells).filter(t -> t.getPathBuilder().getText() != null).collect((Collectors.toList()));
        setChildNodes(collect.toArray(new AbstractCell[collect.size()]));
    }

    public Row(WebLocator table, int indexRow, AbstractCell... cells) {
        this(table, cells);
        setPosition(indexRow);
    }

    @Override
    public Cell getCell(int columnIndex) {
        return new Cell(this, columnIndex);
    }

    public void select() {
        scrollInGrid(this);
        if (!isSelected()) {
            CheckBox checkBox = new CheckBox(this).setBaseCls("x-grid-row-checker");
            checkBox.click();
        }
    }

    public void unSelect() {
        scrollInGrid(this);
        if (isSelected()) {
            CheckBox checkBox = new CheckBox(this).setBaseCls("x-grid-row-checker");
            checkBox.click();
        }
    }

    public boolean isSelected() {
        return getAttributeClass().contains("x-grid-item-selected");
    }

    public boolean scrollTo() {
        while (!isElementPresent()) {
            WebLocator container = getPathBuilder().getContainer();
            int lastRowVisibleIndex = new Row(container).findElements().size() - 1;
            Row row = new Row(container, lastRowVisibleIndex);
            WebLocatorUtils.scrollToWebLocator(row);
        }
        return isElementPresent();
    }

    private void scrollInGrid(Row row) {
        while (!row.waitToRender(100)) {
            Grid grid = (Grid) getPathBuilder().getContainer();
            if (!grid.scrollPageDown()) {
                break;
            }
        }
    }
}
