package com.madiv.jar.bsns;


public enum JarType {
    sonata_commons("jarcontentscommon"),
    sonata_dao("jarcontentsdao"),
    sonata_datamodel("jarcontentsdm"),
    sonata_pbx("jarcontentspbx"),
    sonata_rda("jarcontentsrda");
    
    private String folderName; 
    
    private JarType(String folderName){
        this.folderName = folderName;
    }
    
    public String getFolderName(){
        return folderName;
    }
    
}
