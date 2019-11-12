package com.ishavanti.demo3;

import javax.annotation.Resource;

public class ProductService {
    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;
    @Resource(name = "productDao")
    private ProductDao productDao;

    public void save() {
        System.out.println("ProductService save ...");
        categoryDao.save();
        productDao.save();
    }
}
