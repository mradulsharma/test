package com.madiv.dtogtst.out;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput implements Output {
    private List<String> finalListToOutput = new ArrayList<String>();
    

    @Override
    public void outputResult() {
        for(String line : finalListToOutput){
            System.out.println(line);
        }

    }

    @Override
    public void addMethodDetail(List<String> methodLines) {
        for(String line : methodLines){
            finalListToOutput.add(line);
        }
        
    }

}
