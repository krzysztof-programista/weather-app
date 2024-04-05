package pl.example.controller;

public abstract class BaseController {

    private String fxmlName;

    public BaseController(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
