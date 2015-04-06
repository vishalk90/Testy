package com.sdl.selenium.web.form;

import com.sdl.selenium.web.WebLocator;

public class CheckBox extends WebLocator implements ICheck {

    public CheckBox() {
        setClassName("SimpleCheckBox");
        setTag("input");
        setTemplate("input-type", "@type='%s'");
        setTemplateValue("input-type", "checkbox");
    }

    public CheckBox(WebLocator container) {
        this();
        setContainer(container);
    }

    public CheckBox(String id) {
        this();
        setId(id);
    }

    @Override
    public boolean isSelected() {
        return ready() && executor.isSelected(this);
    }
}