package com.sdl.selenium.web.form;

import com.sdl.selenium.web.WebLocator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleListTest {
    private static WebLocator container = new WebLocator("container");

    @DataProvider
    public static Object[][] testConstructorPathDataProvider() {
        return new Object[][]{
                {new SimpleList(),             "//select"},
                {new SimpleList(container),    "//*[contains(concat(' ', @class, ' '), ' container ')]//select"},
                {new SimpleList(container, "Label"), "//*[contains(concat(' ', @class, ' '), ' container ')]//label[text()='Label']//following-sibling::select"},
        };
    }

    @Test(dataProvider = "testConstructorPathDataProvider")
    public void getPathSelectorCorrectlyFromConstructors(SimpleList list, String expectedXpath) {
        Assert.assertEquals(list.getPath(), expectedXpath);
    }
}
