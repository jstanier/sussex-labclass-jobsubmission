# Leader election lab class - part 1

For the first part of this lab class, we're going to learn how to submit tasks to Zookeeper using the Apache Curator library. We're going to be building and running two applications, and this is the first one. This application, once you've written the rest of it, will allow you create nodes on Zookeeper that represent jobs. Then, after that, you will write another application that receives these jobs.

## Installing Zookeeper

Download Zookeeper 3.4.6 at the command line:

```wget http://mirrors.ukfast.co.uk/sites/ftp.apache.org/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz```

Then, unzip the tarball:

```tar xvf zookeeper-3.4.6.tar.gz```

You should now have a directory called `zookeeper-3.4.6` in which the Zookeeper files are located. Next, you'll need to create a config file for it. Luckily for you, there's already a sample one in there that you can copy over.

```
cd zookeeper-3.4.6
cd conf
cp zoo_sample.cfg zoo.cfg
```

Next, you can run it from the `bin` directory:

```
cd ../bin
./zkServer.sh start
```

You should see a very similar print out at the command line to show that it's running:

```
JMX enabled by default
Using config: /Users/jamess/zookeeper-3.4.6/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```

Since Zookeeper is written in Java, you can check that it's running by using the `jps` command, which will output something like this:

```
16513 sun.tools.jps.Jps
16509 org.apache.zookeeper.server.quorum.QuorumPeerMain
```

The second process in this case is Zookeeper. Great!

## Connecting to Zookeeper via the command line

You can connect via the command line to interact with it before you write any programs that do so. If you're not in the `bin` directory, then go back there. Then run:

```
bin/zkCli.sh -server 127.0.0.1:2181
```

You should now be looking at the Zookeeper shell! It's now waiting for you to type some commands:

```
[zk: 127.0.0.1:2181(CONNECTED) 0]
```

You interact with Zookeeper like a filesystem. There's nothing really on it yet as you haven't done anything, but you can see that it keeps some diagnostic information for itself. See which "folders" are already there:

```
[zk: 127.0.0.1:2181(CONNECTED) 0] ls /
[zookeeper]
```

Ah, there's one called `zookeeper`. Why not see what this node is? 

```
[zk: 127.0.0.1:2181(CONNECTED) 1] get /zookeeper

cZxid = 0x0
ctime = Thu Jan 01 01:00:00 GMT 1970
mZxid = 0x0
mtime = Thu Jan 01 01:00:00 GMT 1970
pZxid = 0x0
cversion = -1
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 1
```

Ah, so it has 1 child node, as signified by `numChildren = 1`. What is it?

```
[zk: 127.0.0.1:2181(CONNECTED) 2] ls /zookeeper
[quota]
```

Cool! Anyway, that's enough prodding around for now. Let's get out of the shell and start writing some Java.

```
[zk: 127.0.0.1:2181(CONNECTED) 3] quit
```

