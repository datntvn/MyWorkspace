/**
 *
 * <p>Filename: Greeting.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */
package com.coffeeshop.app;

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
