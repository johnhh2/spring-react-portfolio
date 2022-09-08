package com.portfolio;

public class JSONResponse {

    private int key;

    public JSONResponse(int val) {
        this.key = val;
    }

    public int increment_by(int val) {
        this.key += val;
        return this.key;
    }

    public String toString() {
        return "{\"key\": " + this.key + "}";
    }
}
