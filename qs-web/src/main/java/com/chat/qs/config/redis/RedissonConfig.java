package com.chat.qs.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.ReplicatedServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Redisson配置
 */
@Configuration
@Component
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 单Redis节点模式
     */
    @Primary
    @Bean(name = "singleRedisson")
    public RedissonClient singleRedisson(){
        return getSingleRedisson();
    }

    /**
     * 哨兵模式
     */
    //@Bean(name = "sentinelRedisson")
    public RedissonClient sentinelRedisson(){
        return getSentinelRedisson();
    }

    /**
     * 主从模式
     */
    //@Bean(name = "masterSlaveRedisson")
    public RedissonClient masterSlaveRedisson(){
        return getMasterSlaveRedisson();
    }

    /**
     * 云托管模式
     */
    //@Bean(name = "replicatedRedisso")
    public RedissonClient replicatedRedisso(){
        return getReplicatedRedisson();
    }

    /**
     * 集群模式
     */
    //@Bean(name = "clusterRedisson")
    public RedissonClient clusterRedisson(){
        return getClusterRedisson();
    }

    /**
     * 单Redis节点模式
     */
    private RedissonClient getSingleRedisson(){
        Config config = getDefaultConfig();
        //单机模式  依次设置redis地址和密码
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setSubscriptionConnectionMinimumIdleSize(1)//从节点发布和订阅连接的最小空闲连接数
                .setSubscriptionConnectionPoolSize(50)//从节点发布和订阅连接池大小
                .setConnectionMinimumIdleSize(32)//最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。
                .setConnectionPoolSize(64)//连接池最大容量。连接池的连接数量自动弹性伸缩。
                .setIdleConnectionTimeout(10000)//连接空闲超时,如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
                .setConnectTimeout(10000)//连接超时,同任何节点建立连接时的等待超时。时间单位是毫秒。
                .setTimeout(3000)//命令等待超时,等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setRetryAttempts(3)//命令失败重试次数
                .setRetryInterval(1500)//命令重试发送时间间隔
                .setReconnectionTimeout(3000)//重新连接时间间隔
                .setFailedAttempts(3)//执行失败最大次数
                .setDatabase(0)//尝试连接的数据库编号。
                .setPassword(null)//用于节点身份验证的密码。
                .setSubscriptionsPerConnection(5)//单个连接最大订阅数量
                .setClientName(null)//在Redis节点里显示的客户端名称。
                //.setSslProvider(JDK)//确定采用哪种方式（默认JDK或OPENSSL）来实现SSL连接。
                .setSslTruststore(null)//指定SSL信任证书库的路径。
                .setSslTruststorePassword(null)//指定SSL信任证书库的密码。
                .setSslKeystore(null)//指定SSL钥匙库的路径。
                .setSslKeystorePassword(null)//指定SSL钥匙库的密码。
                .setAddress("redis://"+host+":"+port).setPassword(null);
        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     */
    private RedissonClient getSentinelRedisson(){
        Config config = getDefaultConfig();
        //单机模式  依次设置redis地址和密码
        SentinelServersConfig sentinelConfig = config.useSentinelServers();
        sentinelConfig.setScanInterval(2000) // 对主节点变化节点状态扫描的时间间隔
                //可以用"rediss://"来启用SSL连接
                //.setReadMode()//读取操作的负载均衡模式,SLAVE - 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取。
                //.setSubscriptionMode()//订阅操作的负载均衡模式,SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅
                /**
                 * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
                 * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法(默认)
                 * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
                 */
                //.setLoadBalancer()//负载均衡算法类的选择
                .setDnsMonitoringInterval(5000)//DNS监控间隔
                .setSubscriptionConnectionMinimumIdleSize(1)//从节点发布和订阅连接的最小空闲连接数
                .setSubscriptionConnectionPoolSize(50)//从节点发布和订阅连接池大小
                .setSlaveConnectionMinimumIdleSize(32)//从节点最小空闲连接数
                .setSlaveConnectionPoolSize(64)//从节点连接池大小
                .setMasterConnectionMinimumIdleSize(32)//主节点最小空闲连接数
                .setMasterConnectionPoolSize(64)//主节点连接池大小
                .setIdleConnectionTimeout(10000)//连接空闲超时,如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
                .setConnectTimeout(10000)//连接超时,同任何节点建立连接时的等待超时。时间单位是毫秒。
                .setTimeout(3000)//命令等待超时,等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setRetryAttempts(3)//命令失败重试次数
                .setRetryInterval(1500)//命令重试发送时间间隔
                .setReconnectionTimeout(3000)//重新连接时间间隔
                .setFailedAttempts(3)//执行失败最大次数
                .setDatabase(0)//尝试连接的数据库编号。
                .setPassword(null)//用于节点身份验证的密码。
                .setSubscriptionsPerConnection(5)//单个连接最大订阅数量
                .setClientName(null)//在Redis节点里显示的客户端名称。
                .setSslEnableEndpointIdentification(true)//开启SSL终端识别能力。
                //.setSslProvider(JDK)//确定采用哪种方式（默认JDK或OPENSSL）来实现SSL连接。
                .setSslTruststore(null)//指定SSL信任证书库的路径。
                .setSslTruststorePassword(null)//指定SSL信任证书库的密码。
                .setSslKeystore(null)//指定SSL钥匙库的路径。
                .setSslKeystorePassword(null)//指定SSL钥匙库的密码。
                .setMasterName("mymaster")//（主服务器的名称）
                //可以用"rediss://"来启用SSL连接
                .addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26319");
        return Redisson.create(config);
    }

    /**
     * 主从模式
     */
    private RedissonClient getMasterSlaveRedisson(){
        Config config = getDefaultConfig();
        MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers();
        //添加主从配置
        masterSlaveConfig
                //可以用"rediss://"来启用SSL连接
                //.setReadMode()//读取操作的负载均衡模式,SLAVE - 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取。
                //.setSubscriptionMode()//订阅操作的负载均衡模式,SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅
                /**
                 * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
                 * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法(默认)
                 * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
                 */
                //.setLoadBalancer()//负载均衡算法类的选择
                .setDnsMonitoringInterval(5000)//DNS监控间隔
                .setSubscriptionConnectionMinimumIdleSize(1)//从节点发布和订阅连接的最小空闲连接数
                .setSubscriptionConnectionPoolSize(50)//从节点发布和订阅连接池大小
                .setSlaveConnectionMinimumIdleSize(32)//从节点最小空闲连接数
                .setSlaveConnectionPoolSize(64)//从节点连接池大小
                .setMasterConnectionMinimumIdleSize(32)//主节点最小空闲连接数
                .setMasterConnectionPoolSize(64)//主节点连接池大小
                .setIdleConnectionTimeout(10000)//连接空闲超时,如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
                .setConnectTimeout(10000)//连接超时,同任何节点建立连接时的等待超时。时间单位是毫秒。
                .setTimeout(3000)//命令等待超时,等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setRetryAttempts(3)//命令失败重试次数
                .setRetryInterval(1500)//命令重试发送时间间隔
                .setReconnectionTimeout(3000)//重新连接时间间隔
                .setFailedAttempts(3)//执行失败最大次数
                .setDatabase(0)//尝试连接的数据库编号。
                .setPassword(null)//用于节点身份验证的密码。
                .setSubscriptionsPerConnection(5)//单个连接最大订阅数量
                .setClientName(null)//在Redis节点里显示的客户端名称。
                .setSslEnableEndpointIdentification(true)//开启SSL终端识别能力。
                //.setSslProvider(JDK)//确定采用哪种方式（默认JDK或OPENSSL）来实现SSL连接。
                .setSslTruststore(null)//指定SSL信任证书库的路径。
                .setSslTruststorePassword(null)//指定SSL信任证书库的密码。
                .setSslKeystore(null)//指定SSL钥匙库的路径。
                .setSslKeystorePassword(null)//指定SSL钥匙库的密码。
                .setMasterAddress("redis://127.0.0.1:6379")
                .addSlaveAddress("redis://127.0.0.1:6389", "redis://127.0.0.1:6332", "redis://127.0.0.1:6419")
                .addSlaveAddress("redis://127.0.0.1:6399");
        //masterSlaveConfig.setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }

    /**
     * 云托管模式
     */
    private RedissonClient getReplicatedRedisson(){
        Config config = getDefaultConfig();
        ReplicatedServersConfig replicatedConfig = config.useReplicatedServers();
        replicatedConfig.setScanInterval(2000) // 对主节点变化节点状态扫描的时间间隔
                //可以用"rediss://"来启用SSL连接
                //.setReadMode()//读取操作的负载均衡模式,SLAVE - 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取。
                //.setSubscriptionMode()//订阅操作的负载均衡模式,SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅
                /**
                 * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
                 * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法(默认)
                 * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
                 */
                //.setLoadBalancer()//负载均衡算法类的选择
                .setDnsMonitoringInterval(5000)//DNS监控间隔
                .setSubscriptionConnectionMinimumIdleSize(1)//从节点发布和订阅连接的最小空闲连接数
                .setSubscriptionConnectionPoolSize(50)//从节点发布和订阅连接池大小
                .setSlaveConnectionMinimumIdleSize(32)//从节点最小空闲连接数
                .setSlaveConnectionPoolSize(64)//从节点连接池大小
                .setMasterConnectionMinimumIdleSize(32)//主节点最小空闲连接数
                .setMasterConnectionPoolSize(64)//主节点连接池大小
                .setIdleConnectionTimeout(10000)//连接空闲超时,如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
                .setConnectTimeout(10000)//连接超时,同任何节点建立连接时的等待超时。时间单位是毫秒。
                .setTimeout(3000)//命令等待超时,等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setRetryAttempts(3)//命令失败重试次数
                .setRetryInterval(1500)//命令重试发送时间间隔
                .setReconnectionTimeout(3000)//重新连接时间间隔
                .setFailedAttempts(3)//执行失败最大次数
                .setDatabase(0)//尝试连接的数据库编号。
                .setPassword(null)//用于节点身份验证的密码。
                .setSubscriptionsPerConnection(5)//单个连接最大订阅数量
                .setClientName(null)//在Redis节点里显示的客户端名称。
                .setSslEnableEndpointIdentification(true)//开启SSL终端识别能力。
                //.setSslProvider(JDK)//确定采用哪种方式（默认JDK或OPENSSL）来实现SSL连接。
                .setSslTruststore(null)//指定SSL信任证书库的路径。
                .setSslTruststorePassword(null)//指定SSL信任证书库的密码。
                .setSslKeystore(null)//指定SSL钥匙库的路径。
                .setSslKeystorePassword(null)//指定SSL钥匙库的密码。
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");
        return Redisson.create(config);
    }

    /**
     * 集群模式
     */
    private RedissonClient getClusterRedisson(){
        Config config = getDefaultConfig();
        ClusterServersConfig clusterConfig = config.useClusterServers();
        clusterConfig.setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                //.setReadMode()//读取操作的负载均衡模式,SLAVE - 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取。
                //.setSubscriptionMode()//订阅操作的负载均衡模式,SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅
                /**
                 * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
                 * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法(默认)
                 * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
                 */
                //.setLoadBalancer()//负载均衡算法类的选择
                .setSubscriptionConnectionMinimumIdleSize(1)//从节点发布和订阅连接的最小空闲连接数
                .setSubscriptionConnectionPoolSize(50)//从节点发布和订阅连接池大小
                .setSlaveConnectionMinimumIdleSize(32)//从节点最小空闲连接数
                .setSlaveConnectionPoolSize(64)//从节点连接池大小
                .setMasterConnectionMinimumIdleSize(32)//主节点最小空闲连接数
                .setMasterConnectionPoolSize(64)//主节点连接池大小
                .setIdleConnectionTimeout(10000)//连接空闲超时,如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
                .setConnectTimeout(10000)//连接超时,同任何节点建立连接时的等待超时。时间单位是毫秒。
                .setTimeout(3000)//命令等待超时,等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setRetryAttempts(3)//命令失败重试次数
                .setRetryInterval(1500)//命令重试发送时间间隔
                .setReconnectionTimeout(3000)//重新连接时间间隔
                .setFailedAttempts(3)//执行失败最大次数
                .setPassword(null)//用于节点身份验证的密码。
                .setSubscriptionsPerConnection(5)//单个连接最大订阅数量
                .setClientName(null)//在Redis节点里显示的客户端名称。
                .setSslEnableEndpointIdentification(true)//开启SSL终端识别能力。
                //.setSslProvider(JDK)//确定采用哪种方式（默认JDK或OPENSSL）来实现SSL连接。
                .setSslTruststore(null)//指定SSL信任证书库的路径。
                .setSslTruststorePassword(null)//指定SSL信任证书库的密码。
                .setSslKeystore(null)//指定SSL钥匙库的路径。
                .setSslKeystorePassword(null)//指定SSL钥匙库的密码。
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");
        return Redisson.create(config);
    }

    private Config getDefaultConfig(){
        Config config = new Config();
        //线程池数量(默认值: 当前处理核数量 * 2)
        //这个线程池数量被所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务共同共享。
        config.setThreads(2);
        //Netty线程池数量(默认值: 当前处理核数量 * 2)
        //这个线程池数量是在一个Redisson实例内，被其创建的所有分布式数据类型和服务，以及底层客户端所一同共享的线程池里保存的线程数量。
        config.setNettyThreads(2);
        //线程池
        //单独提供一个用来执行所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务的线程池（ExecutorService）实例。
        //config.setExecutor();
        /**
         * 传输模式
         * TransportMode.NIO,
         * TransportMode.EPOLL - 需要依赖里有netty-transport-native-epoll包（Linux）
         * TransportMode.KQUEUE - 需要依赖里有 netty-transport-native-kqueue包（macOS）
         */
        config.setTransportMode(TransportMode.NIO);
        //监控锁的看门狗超时
        config.setLockWatchdogTimeout(30000);
        //保持订阅发布顺序
        config.setKeepPubSubOrder(true);
        return config;
    }
}
