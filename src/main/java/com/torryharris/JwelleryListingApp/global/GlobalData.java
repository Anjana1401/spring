package com.torryharris.JwelleryListingApp.global;

import com.torryharris.JwelleryListingApp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart; //id ,name.................
    static {
        cart=new ArrayList<Product>();
    }
}
