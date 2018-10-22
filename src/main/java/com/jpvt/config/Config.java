/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpvt.config;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author User
 */
public class Config {

    private final String jsonFilePath;
    private String navFile;
    private String obsFile;

    public Config(String jsonPath) {
        this.jsonFilePath = jsonPath;
    }
    
    public void parseJson() {
        JSONParser parser = new JSONParser();

        try {

            File file = new File(this.jsonFilePath);
            String json_s = FileUtils.readFileToString(file, "UTF-8");

            Object obj = parser.parse(json_s);
            JSONObject jobj = (JSONObject) obj;
            
            this.navFile = jobj.get("nav").toString();
            this.obsFile = jobj.get("obs").toString();

        } catch (ParseException pe) {
            System.err.println("position: " + pe.getPosition());
            System.err.println(pe);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String toString() {
        String out = getClass().getName();
        out += "\n\tnav: " + this.navFile;
        out += "\n\tobs: " + this.obsFile;
        return out;
    }

    public String getNavFile() {
        return navFile;
    }

    public String getObsFile() {
        return obsFile;
    }
}
