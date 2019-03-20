package com.songjh.learncore.common.jsonschema;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.io.InputStream;
import org.everit.json.schema.Schema;

/**
 * Created  by songjh on 2019-03-21 06:36.
 */
public class JsonValidatorDemo {

    public static void main(String[] args){

    }

    @Test
    public void testJsonSchema3() {
        InputStream inputStream = getClass().getResourceAsStream("/schema/schemaDemo.json");
        JSONObject Schema = new JSONObject(new JSONTokener(inputStream));
        JSONObject data = new JSONObject("{\"data\" : 1234}");
        Schema schema = SchemaLoader.load(Schema);
        try {
            schema.validate(data);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
