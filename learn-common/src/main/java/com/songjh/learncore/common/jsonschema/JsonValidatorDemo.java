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



    @Test
    public void testJsonSchema() {
        InputStream inputStream = getClass().getResourceAsStream("/schema/schemaDemo.json");
        JSONObject Schema = new JSONObject(new JSONTokener(inputStream));
        String test = "{"+
                    "\"street_address\": \"24 Sussex Drive\","+
                    "\"country\": \"Canada\","+
                    "\"postal_code1111\": \"10000\""+
                "}";
        JSONObject data = new JSONObject(test);

        SchemaLoader loader = SchemaLoader.builder()
                .schemaJson(Schema)
                .draftV7Support() // or draftV7Support()
                .build();
        Schema schema = loader.load().build();

        try {
            schema.validate(data);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            printLog(e);
        }
    }

    private void printLog(ValidationException e) {
        for (ValidationException validationException : e.getCausingExceptions()) {
            if(validationException.getCausingExceptions().size()>0){
                for (ValidationException exception : e.getCausingExceptions()) {
                    this.printLog(exception);
                }
            }else {
                System.out.println("pointerToViolation:"+validationException.getPointerToViolation());
                System.out.println("schemaLocation:"+validationException.getSchemaLocation());
                System.out.println("violatedSchema:"+validationException.getViolatedSchema());
                System.out.println("message:"+validationException.getMessage());
            }
        }
    }
}
