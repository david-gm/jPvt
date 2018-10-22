/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpvt;

import com.jpvt.reader.RinexFileReader;
import com.jpvt.config.Config;

/**
 *
 * @author User
 */

public class JPvt {

    public static void main(String[] args) {
        
        Config cfg = new Config("data/config.json");
        cfg.parseJson();
        System.out.println(cfg);
        
        //String fn = "data/TWTF00TWN_R_20180120000_01D_30S_MO.rnx";
        RinexFileReader tc = new RinexFileReader(cfg.getNavFile());
        if (!tc.readFile()) {
            System.exit(-1);
        }
        
    }
}
