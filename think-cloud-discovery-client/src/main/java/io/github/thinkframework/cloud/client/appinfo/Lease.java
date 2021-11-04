package io.github.thinkframework.cloud.client.appinfo;

import io.github.thinkframework.cloud.client.discovery.AbstractServiceInstance;

/**
 * 租约信息
 * 本地存一份,metadata放一份
 * 本地更新效率比较高
 * metadata提供给spring cloud接口抽象
 */
public class Lease {

    public static final long LEASE_RENEWAL_INTERVAL = 30L;
    public static final long LEASE_EXPIRATION_DURATION = 90L;

    public static final String LEASE_RENEWAL_INTERVAL_IN_SECONDS = "lease-renewal-interval-in-seconds";
    public static final String LEASE_EXPIRATION_DURATION_IN_SECONDS = "lease-expiration-duration-in-seconds";

    public static final String REGISTRATION_TIMESTAMP = "registrationTimestamp";
    public static final String LAST_RENEWAL_TIMESTAMP = "lastRenewalTimestamp";
    public static final String EVICTION_TIMESTAMP = "evictionTimestamp";
    public static final String SERVICE_UP_TIMESTAMP = "serviceUpTimestamp";

    public long registrationTimestamp;
    public long lastRenewalTimestamp;
    public long evictionTimestamp;
    public long serviceUpTimestamp;

//    private final AbstractServiceInstance serviceInstance;
//
//    public Lease(AbstractServiceInstance serviceInstance) {
//        this.serviceInstance = serviceInstance;
//    }
//
//    /**
//     * 租约更新间隔
//     *
//     * @return 秒
//     */
//    public Long getLeaseRenewalIntervalInSeconds() {
//        return Long.valueOf(serviceInstance.metadata.getOrDefault(LEASE_RENEWAL_INTERVAL_IN_SECONDS, String.valueOf(LEASE_RENEWAL_INTERVAL)));
//    }
//
//    public void setLeaseRenewalIntervalInSeconds(long leaseRenewalIntervalInSeconds) {
//        serviceInstance.metadata.put(LEASE_RENEWAL_INTERVAL_IN_SECONDS, String.valueOf(leaseRenewalIntervalInSeconds));
//    }
//
//    /**
//     * 租约到期持续时间
//     *
//     * @return
//     */
//    public Long getLeaseExpirationDurationInSeconds() {
//        return Long.valueOf(serviceInstance.metadata.getOrDefault(LEASE_EXPIRATION_DURATION_IN_SECONDS, String.valueOf(LEASE_EXPIRATION_DURATION)));
//    }
//
//    public void setLeaseExpirationDurationInSeconds(long LeaseExpirationDurationInSeconds) {
//        serviceInstance.metadata.put(LEASE_EXPIRATION_DURATION_IN_SECONDS, String.valueOf(LeaseExpirationDurationInSeconds));
//    }

    public long getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(long registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
//        serviceInstance.metadata.put(REGISTRATION_TIMESTAMP, String.valueOf(registrationTimestamp));
    }

    public long getLastRenewalTimestamp() {
        return lastRenewalTimestamp;
    }

    public void setLastRenewalTimestamp(long lastRenewalTimestamp) {
        this.lastRenewalTimestamp = lastRenewalTimestamp;
//        serviceInstance.metadata.put(LAST_RENEWAL_TIMESTAMP, String.valueOf(lastRenewalTimestamp));
    }

    public long getEvictionTimestamp() {
        return evictionTimestamp;
    }

    public void setEvictionTimestamp(long evictionTimestamp) {
        this.evictionTimestamp = evictionTimestamp;
//        serviceInstance.metadata.put(EVICTION_TIMESTAMP, String.valueOf(evictionTimestamp));
    }

    public long getServiceUpTimestamp() {
        return serviceUpTimestamp;
    }

    public void setServiceUpTimestamp(long serviceUpTimestamp) {
        this.serviceUpTimestamp = serviceUpTimestamp;
//        serviceInstance.metadata.put(SERVICE_UP_TIMESTAMP, String.valueOf(serviceUpTimestamp));
    }
}
