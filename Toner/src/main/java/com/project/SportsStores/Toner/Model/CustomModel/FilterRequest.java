package com.project.SportsStores.Toner.Model.CustomModel;


import lombok.Data;

import java.util.List;

@Data
public class FilterRequest {
    private List<Integer> listColors;
    private List<String> listSizes;
}
