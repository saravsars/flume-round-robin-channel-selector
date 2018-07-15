/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sars.flume;

import java.util.ArrayList;
import java.util.List;

import org.apache.flume.Channel;
import org.apache.flume.Event;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.event.EventBuilder;
import org.junit.Assert;
import org.junit.Test;

public class RoundRobinChannelSelectorTest {

	private static final String EVENT_BODY = "test event";

	@Test
	public void testRequiredChannels() {
		Event event = EventBuilder.withBody(EVENT_BODY.getBytes());
		RoundRobinChannelSelector channelSelector = new RoundRobinChannelSelector();
		
		final String CHANNEL_1 = "channel1";
		final String CHANNEL_2 = "channel2";
		final String CHANNEL_3 = "channel3";
		Channel channel1 = new MemoryChannel();
		channel1.setName(CHANNEL_1);
		Channel channel2 = new MemoryChannel();
		channel2.setName(CHANNEL_2);
		Channel channel3 = new MemoryChannel();
		channel3.setName(CHANNEL_3);
		
		List<Channel> configuredChannels = new ArrayList<Channel>();
		configuredChannels.add(channel1);
		configuredChannels.add(channel2);
		configuredChannels.add(channel3);
		channelSelector.setChannels(configuredChannels);

		List<Channel> requiredChannels = channelSelector.getRequiredChannels(event);
		Assert.assertEquals(1, requiredChannels.size());
		Assert.assertEquals(CHANNEL_1, requiredChannels.get(0).getName());

		requiredChannels = channelSelector.getRequiredChannels(event);
		Assert.assertEquals(CHANNEL_2, requiredChannels.get(0).getName());

		requiredChannels = channelSelector.getRequiredChannels(event);
		Assert.assertEquals(CHANNEL_3, requiredChannels.get(0).getName());

		requiredChannels = channelSelector.getRequiredChannels(event);
		Assert.assertEquals(CHANNEL_1, requiredChannels.get(0).getName());
	}

}
