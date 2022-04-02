package com.techxtor.dependencyinjectionworking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/*public class CatService {
    public int getCatCount() {
        DataSource dataSource = new DataSource();
        int catCount = dataSource.getCats().size();
        System.out.println("Number of Cats: " + catCount);
        return catCount;
    }
}*/


@Component
public class CatService {

    private DataSource dataSource;

    @Autowired
    public CatService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getCatCount() {
        int catCount = dataSource.getCats().size();
        System.out.println("Number of Cats: " + catCount);
        return catCount;
    }
}
