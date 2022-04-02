package com.techxtor.dependencyinjectionworking;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSource {

    public List getCats() {
        return Arrays.asList("Leo", "Oliver", "Max");
    }
}
