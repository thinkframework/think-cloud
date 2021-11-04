package io.github.thinkframework.cloud.client.serviceregistry;

import io.github.thinkframework.cloud.client.appinfo.Application;
import io.github.thinkframework.cloud.client.appinfo.Applications;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 扩展ServiceRegistry接口
 */
public interface ExtendServiceRegistry<R extends Registration> {

    R renew(R registration);

    /**
     * 全量
     * @return
     */
    Applications getApplications();

    /**
     * 增量更新
     */
    Applications getDelta();
}
