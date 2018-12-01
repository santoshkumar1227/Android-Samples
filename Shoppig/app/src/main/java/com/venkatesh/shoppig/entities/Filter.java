package com.venkatesh.shoppig.entities;

public class Filter {
    private String type;
    private boolean isChecked;

    public Filter(String type, boolean isChecked) {
        this.type = type;
        this.isChecked = isChecked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
