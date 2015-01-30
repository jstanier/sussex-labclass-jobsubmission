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

## Java code

Clone this repository locally by using the following command:

```
git clone https://github.com/jstanier/sussex-labclass-jobsubmission.git
```

To check everything's OK, build it with maven:

```
cd sussex-labclass-jobsubmission
mvn clean test
```

Now, import the maven project into your IDE of choice. I assume most of you will be using Eclipse. You should now be able to open up the main class called `Main.java`, right-click anywhere around the code, and then select `Run As...` then `Java application`. You should see a program that starts up then asks you to type a job name. But it seems you haven't implemented it yet: 

```
Job name (or type exit to quit): hello
2015-01-30 14:57:38.900  INFO 18032 --- [           main] o.j.s.l.c.CommandLineOrchestrator        : Creating job called hello
2015-01-30 14:57:38.900  INFO 18032 --- [           main] o.j.s.labclass.zookeeper.JobCreator      : Whoops! You haven't implemented this yet
```

## Adding nodes to Zookeeper

Open up the `JobCreator` class. You should see a method called `createJob` that you need to fill in. 

```java
public void createJob(String jobName) {
        // TODO: Make a node on Zookeeper here.
    } 
```

For each job, make it so that it creates a node at the `/sussex/jobs` path, which is defined in `Constants.java`. For example, if the job was called `hello`, then it should create the path `/sussex/jobs/hello` on Zookeeper. You have access to the `CuratorFramework` client. Look at the [documentation](https://curator.apache.org/apidocs/org/apache/curator/framework/CuratorFramework.html) to see how to create nodes. 

## Done it?

Great - run it again and add a job called `ididit`. Then, type `exit` to quit the program. You can see the node you created on Zookeeper by using the `zkCli.sh` shell again:

```
[zk: localhost:2181(CONNECTED) 14] ls /sussex/jobs
[ididit]
```

Well done. Now, let's move on to the second part of the lab class.
