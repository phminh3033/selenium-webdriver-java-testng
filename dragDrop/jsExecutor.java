public Object executeForBrowser(String javaScript) {
    return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
    return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean isExpectedTextInInnerText(String textExpected) {
    String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
    return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
    jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
    jsExecutor.executeScript("window.location = '" + url + "'");
    sleepInSecond(3);
}

public void hightlightElement(String locator) {
    WebElement element = getElement(locator);
    String originalStyle = element.getAttribute("style");
    jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
    sleepInSecond(2);
    jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
    jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    sleepInSecond(3);
}

public void scrollToElementOnTop(String locator) {
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
    jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
    jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
    jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
    jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public String getAttributeInDOM(String locator, String attributeName) {
    return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
    return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
    boolean status = (boolean) jsExecutor.executeScript(
            "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
    return status;
}

public WebElement getElement(String locator) {
    return driver.findElement(By.xpath(locator));
}