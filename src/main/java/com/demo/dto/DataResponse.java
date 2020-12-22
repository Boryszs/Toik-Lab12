package com.demo.dto;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DataResponse {

    private Set<Integer> lineIds;
    private Set<Integer> kolumnIds;
    private Set<Integer> areaIds;

    public DataResponse() {
        this.lineIds = new LinkedHashSet<>();
        this.kolumnIds = new LinkedHashSet<>();
        this.areaIds = new LinkedHashSet<>();
    }

    public Set<Integer> getLineIds() {
        return lineIds;
    }

    public Set<Integer> getKolumnIds() {
        return kolumnIds;
    }

    public Set<Integer> getAreaIds() {
        return areaIds;
    }

    public void setLineIds(Integer lineIds) {
        this.lineIds.add(lineIds);
    }

    public void setKolumnIds(Integer kolumnIds) {
        this.kolumnIds.add(kolumnIds);
    }

    public void setAreaIds(Integer areaIds) {
        this.areaIds.add(areaIds);
    }
}
