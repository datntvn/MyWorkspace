/**
 * @author DatNT29
 *
 * File       : Greeting.java
 * Created On : 10/09/2018 (dd/MM/YYYY)
 */

package com.datnt.models;

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
