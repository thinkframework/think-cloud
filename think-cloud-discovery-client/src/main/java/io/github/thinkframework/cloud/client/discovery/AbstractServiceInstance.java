package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.appinfo.Lease;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象服务实例
 */
public abstract class AbstractServiceInstance implements ServiceInstance {

    protected String instanceId;
    protected String serviceId;
    protected String host;
    protected int port;
    protected boolean secure;

    protected long leaseRenewalIntervalInSeconds;
    protected long leaseExpirationDurationInSeconds;

    // fixme 作用域,lease要不要移过来
    public Map<String, String> metadata = new HashMap<>();


    public AbstractServiceInstance() {
    }

    /**
     * 租约信息
     */
    protected Lease lease = new Lease();


    @Override
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    /**
     * 租约更新间隔
     *
     * @return 秒
     */
    public Long getLeaseRenewalIntervalInSeconds() {
        return leaseRenewalIntervalInSeconds;
    }

    public void setLeaseRenewalIntervalInSeconds(long leaseRenewalIntervalInSeconds) {
        this.leaseRenewalIntervalInSeconds = leaseRenewalIntervalInSeconds;
    }

    /**
     * 租约到期持续时间
     *
     * @return
     */
    public Long getLeaseExpirationDurationInSeconds() {
        return leaseExpirationDurationInSeconds;
    }

    public void setLeaseExpirationDurationInSeconds(long LeaseExpirationDurationInSeconds) {
        this.leaseExpirationDurationInSeconds = leaseRenewalIntervalInSeconds;
    }
}
