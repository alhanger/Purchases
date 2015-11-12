package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;

/**
 * Created by alhanger on 11/11/15.
 */
@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() {
        if (customers.count() == 0) {
            parseCustomers();
        }

        if(purchases.count() == 0) {
            parsePurchases();
        }
    }

    @RequestMapping("/")
    public String home(Model model, String category, String showCategory, HttpServletRequest request) {

//        if (showCategory != null) {
//            String isAscStr = (String) request.getAttribute("isAsc");
//            boolean isAsc = isAscStr != null && isAscStr.equals("true");
//
//            if (isAsc == true) {
//                model.addAttribute("purchases", purchases.orderByAsc());
//            }
//            else {
//                model.addAttribute("purchases", purchases.orderByDsc());
//            }
//        }
        if (category != null) {
            model.addAttribute("purchases", purchases.findByCategory(category));
        }
        else {
            model.addAttribute("purchases", purchases.findAll());
        }

        return "home";
    }

    public String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    public void parseCustomers() {
        String content = readFile("customers.csv");
        String[] lines = content.split("\n");

        for (int i = 1; i < lines.length; i++) {
            Customer customer = new Customer();
            String[] col = lines[i].split(",");

            customer.name = col[0];
            customer.email = col[1];

            customers.save(customer);
        }
    }

    public void parsePurchases() {
        String content = readFile("purchases.csv");
        String[] lines = content.split("\n");

        for (int i = 1; i < lines.length; i ++) {
            Purchase purchase = new Purchase();
            String[] col = lines[i].split(",");

            purchase.date = col[1];
            purchase.cardNum = col[2];
            purchase.validation = col[3];
            purchase.category = col[4];

            int id = Integer.valueOf(col[0]);
            purchase.customer = customers.findOne(id);

            purchases.save(purchase);
        }
    }
}
