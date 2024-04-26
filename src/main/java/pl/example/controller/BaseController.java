package pl.example.controller;

public abstract class BaseController {

    private String fxmlName;
    protected String title;
    protected String backgroundColor;

    public BaseController(String fxmlName, String title, String backgroundColor) {
        this.fxmlName = fxmlName;
        this.title = title;
        this.backgroundColor = backgroundColor;
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public BaseController(String title) {
        this.title = title;
    }
}

