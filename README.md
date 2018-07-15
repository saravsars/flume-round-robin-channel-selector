# flume-round-robin-channel-selector [![Build Status](https://travis-ci.org/saravsars/flume-round-robin-channel-selector.svg?branch=master)](https://travis-ci.org/saravsars/flume-round-robin-channel-selector)

Round-robin channel selector to distribute events from a source to multiple channels for increasing the throughput

## Getting started

1. **Build flume-round-robin-channel-selector**

    <pre>
    $ git clone https://github.com/saravsars/flume-round-robin-channel-selector.git
    $ cd flume-round-robin-channel-selector
    $ mvn clean package
    $ ls target
    flume-round-robin-channel-selector-1.0.jar
    </pre>

2. **Add JARs to the Flume classpath**

    <pre>
    $ cp /etc/flume-ng/conf/flume-env.sh.template /etc/flume-ng/conf/flume-env.sh
    $ vi /etc/flume-ng/conf/flume-env.sh
    FLUME_CLASSPATH="/path/to/file/flume-round-robin-channel-selector-1.0.jar"
    </pre>

3. **Set the Flume agent configuration**

    <pre>
    agent.sources = source1
    agent.sinks = sink1 sink2
    agent.channels = channel1 channel2
    
    agent.sources.source1.channels = channel1 channel2
    
    agent.sinks.sink1.channel = channel1
    agent.sinks.sink2.channel = channel2
    
    agent.sources.source1.selector.type = com.sars.flume.RoundRobinChannelSelector
    </pre>
