/**
 * Author : chizf
 * Date : 2020年4月2日 上午9:42:53
 * Title : org.mbs.client.producer.ProducerStarter.java
 *
**/
package org.mbs.client.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.mbs.client.producer.ProducerClient.ProducerConfig;

public class ProducerStarter {

	public static void start(ProducerConfig producerConfig) throws MQClientException {

		ProducerClient.create(producerConfig).start();
	}

	public static void shutdown() {
		ProducerClient.getInstance().shutdown();
	}

}
