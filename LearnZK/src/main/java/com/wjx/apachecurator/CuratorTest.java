package com.wjx.apachecurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author WangJX
 * @Date 2022/5/21 23:07
 * @Description
 */
public class CuratorTest {
    public static void main(String[] args) throws Exception {

        // Zookeeper集群地址，多个节点地址可以用逗号分隔
        String zkAddress = "127.0.0.1:2181";

        // 重试策略，如果连接不上ZooKeeper集群，会重试三次，重试间隔会递增
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        // 创建Curator Client并启动，启动成功之后，就可以与Zookeeper进行交互了
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);

        client.start();
//        t1(client);
//        t2(client);
//        t3(client);
//        t4(client);

        // 创建NodeCache，监听的是"/user"这个节点
        NodeCache nodeCache = new NodeCache(client, "/user");
        // start()方法有个boolean类型的参数，默认是false。如果设置为true，
        // 那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的
        // 数据内容，并保存在Cache中。
        nodeCache.start(true);
        if (nodeCache.getCurrentData() != null) {
            System.out.println("NodeCache节点初始化数据为："
                    + new String(nodeCache.getCurrentData().getData()));
        } else {
            System.out.println("NodeCache节点数据为空");
        }

        // 添加监听器
        nodeCache.getListenable().addListener(() -> {
            String data = new String(nodeCache.getCurrentData().getData());
            System.out.println("NodeCache节点路径：" + nodeCache.getCurrentData().getPath()
                    + "，节点数据为：" + data);
        });

        // 创建PathChildrenCache实例，监听的是"user"这个节点
        PathChildrenCache childrenCache = new PathChildrenCache(client, "/user", true);
        // StartMode指定的初始化的模式
        // NORMAL:普通异步初始化
        // BUILD_INITIAL_CACHE:同步初始化
        // POST_INITIALIZED_EVENT:异步初始化，初始化之后会触发事件
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        // childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        // childrenCache.start(PathChildrenCache.StartMode.NORMAL);
        List<ChildData> children = childrenCache.getCurrentData();
        System.out.println("获取子节点列表：");
        // 如果是BUILD_INITIAL_CACHE可以获取这个数据，如果不是就不行
        children.forEach(childData -> {
            System.out.println(new String(childData.getData()));
        });
        childrenCache.getListenable().addListener(((client1, event) -> {
            System.out.println(LocalDateTime.now() + "  " + event.getType());
            if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                System.out.println("PathChildrenCache:子节点初始化成功...");
            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                String path = event.getData().getPath();
                System.out.println("PathChildrenCache添加子节点:" + event.getData().getPath());
                System.out.println("PathChildrenCache子节点数据:" + new String(event.getData().getData()));
            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                System.out.println("PathChildrenCache删除子节点:" + event.getData().getPath());
            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                System.out.println("PathChildrenCache修改子节点路径:" + event.getData().getPath());
                System.out.println("PathChildrenCache修改子节点数据:" + new String(event.getData().getData()));
            }
        }));

        // 创建TreeCache实例监听"user"节点
        TreeCache cache = TreeCache.newBuilder(client, "/user").setCacheData(false).build();
        cache.getListenable().addListener((c, event) -> {
            if (event.getData() != null) {
                System.out.println("TreeCache,type=" + event.getType() + " path=" + event.getData().getPath());
            } else {
                System.out.println("TreeCache,type=" + event.getType());
            }
        });
        cache.start();

        System.in.read();

    }

    private static void t4(CuratorFramework client) throws Exception {
        try {
            client.create().withMode(CreateMode.PERSISTENT)
                    .forPath("/user", "test".getBytes());
        } catch (Exception e) {
        }
        // 这里通过usingWatcher()方法添加一个Watcher
        List<String> children = client.getChildren().usingWatcher(
                new CuratorWatcher() {
                    public void process(WatchedEvent event) throws Exception {
                        System.out.println(event.getType() + "," +
                                event.getPath());
                    }
                }).forPath("/user");
        System.out.println(children);


        client.getData().usingWatcher(
                new CuratorWatcher() {
                    public void process(WatchedEvent event) throws Exception {
                        System.out.println(event.getType() + "," +
                                event.getPath());
                    }
                }).forPath("/user");


//        client.setData().forPath("/user", "data".getBytes());
        System.in.read();
    }

    private static void t3(CuratorFramework client) {
        // 添加ConnectionStateListener监听器
        client.getConnectionStateListenable().addListener(
                new ConnectionStateListener() {
                    public void stateChanged(CuratorFramework client,
                                             ConnectionState newState) {
                        // 这里我们可以针对不同的连接状态进行特殊的处理
                        switch (newState) {
                            case CONNECTED:
                                System.out.println("CONNECTED");
                                // 第一次成功连接到ZooKeeper之后会进入该状态。
                                // 对于每个CuratorFramework对象，此状态仅出现一次
                                break;
                            case SUSPENDED: //   ZooKeeper的连接丢失
                                System.out.println("SUSPENDED");
                                break;
                            case RECONNECTED: // 丢失的连接被重新建立
                                System.out.println("RECONNECTED");
                                break;
                            case LOST:
                                System.out.println("LOST");
                                // 当Curator认为会话已经过期时，则进入此状态
                                break;
                            case READ_ONLY: // 连接进入只读模式
                                System.out.println("READ_ONLY");
                                break;
                        }
                    }
                });
    }

    // Background
    private static void t2(CuratorFramework client) throws Exception {
        // 添加CuratorListener监听器，针对不同的事件进行处理
        client.getCuratorListenable().addListener(
                new CuratorListener() {
                    public void eventReceived(CuratorFramework client,
                                              CuratorEvent event) throws Exception {
                        switch (event.getType()) {
                            case CREATE:
                                System.out.println("CREATE:" +
                                        event.getPath());
                                break;
                            case DELETE:
                                System.out.println("DELETE:" +
                                        event.getPath());
                                break;
                            case EXISTS:
                                System.out.println("EXISTS:" +
                                        event.getPath());
                                break;
                            case GET_DATA:
                                System.out.println("GET_DATA:" +
                                        event.getPath() + ","
                                        + new String(event.getData()));
                                break;
                            case SET_DATA:
                                System.out.println("SET_DATA:" +
                                        new String(event.getData()));
                                break;
                            case CHILDREN:
                                System.out.println("CHILDREN:" +
                                        event.getPath());
                                break;
                            default:
                        }
                    }
                });
        // 注意:下面所有的操作都添加了inBackground()方法，转换为后台操作
        client.create().withMode(CreateMode.PERSISTENT)
                .inBackground()
                .forPath("/user", "test".getBytes());

        client.checkExists().
                inBackground().
                forPath("/user");

        client.setData().
                inBackground().
                forPath("/user", "setData-Test".getBytes());

        client.getData().
                inBackground().
                forPath("/user");

        for (int i = 0; i < 3; i++) {
            client.create().
                    withMode(CreateMode.EPHEMERAL_SEQUENTIAL).
                    inBackground().
                    forPath("/user/child-");
        }
        client.getChildren().inBackground().forPath("/user");
        // 添加BackgroundCallback
        client.getChildren().inBackground(new BackgroundCallback() {
            public void processResult(CuratorFramework client,
                                      CuratorEvent event) throws Exception {
                System.out.println("in background:"
                        + event.getType() + "," + event.getPath());
            }
        }).forPath("/user");

        client.delete().deletingChildrenIfNeeded().inBackground()
                .forPath("/user");
        System.in.read();
    }

    private static void t1(CuratorFramework client) throws Exception {
        // 下面简单说明Curator中常用的API
        // create()方法创建ZNode，可以调用额外方法来设置节点类型、添加Watcher
        // 下面是创建一个名为"user"的持久节点，其中会存储一个test字符串
        String path = client.create().withMode(CreateMode.PERSISTENT).forPath("/user", "test".getBytes());

        // 输出:/user
        System.out.println(path);

        // checkExists()方法可以检查一个节点是否存在
        Stat stat = client.checkExists().forPath("/user");

        // 输出:true，返回的Stat不为null，即表示节点存在
        System.out.println(stat != null);

        // getData()方法可以获取一个节点中的数据
        byte[] data = client.getData().forPath("/user");

        // 输出:test
        System.out.println(new String(data));

        // setData()方法可以设置一个节点中的数据
        stat = client.setData().forPath("/user", "data".getBytes());

        data = client.getData().forPath("/user");

        // 输出:data
        System.out.println(new String(data));

        // 在/user节点下，创建多个临时顺序节点
        for (int i = 0; i < 3; i++) {

            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/user/child-");

        }

        // 获取所有子节点
        List<String> children = client.getChildren().forPath("/user");

        System.out.println(children);

        // 输出：[child-0000000002, child-0000000001, child-0000000000]
        // delete()方法可以删除指定节点，deletingChildrenIfNeeded()方法
        // 会级联删除子节点
        client.delete().deletingChildrenIfNeeded().forPath("/user");
    }

}
