package com.mycompany.soc.network.project.persistence.connection;

public enum DBTypes {
    MYSQL("database");

    private String propertiesFile;

    DBTypes(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public String getPropertiesFile() {
        return propertiesFile;
    }
}
