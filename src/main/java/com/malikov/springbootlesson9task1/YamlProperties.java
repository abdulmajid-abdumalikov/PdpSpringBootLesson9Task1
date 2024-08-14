package com.malikov.springbootlesson9task1;

import java.util.List;

public class YamlProperties {
    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers;

    public YamlProperties(String name, String environment, boolean enabled, List<String> servers) {
        this.name = name;
        this.environment = environment;
        this.enabled = enabled;
        this.servers = servers;
    }

    public YamlProperties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

}
