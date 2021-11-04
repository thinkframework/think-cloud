package io.github.thinkframework.cloud.client.appinfo;

import java.util.Map;

/**
 * 应用
 */
public class Applications {
    private Map<String,Application> applications;

    public Applications() {
    }

    public Applications(Map<String, Application> applications) {
        this.applications = applications;
    }

    public Map<String, Application> getApplications() {
        return applications;
    }

    public void setApplications(Map<String, Application> applications) {
        this.applications = applications;
    }


    public Application getApplication(String instanceId) {
        return applications.get(instanceId);
    }

}
