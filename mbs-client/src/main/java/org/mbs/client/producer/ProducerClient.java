/**
 * Author : chizf
 * Date : 2020年4月2日 上午10:05:47
 * Title : org.mbs.client.producer.ProducerClient.java
 *
**/
package org.mbs.client.producer;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ProducerClient {

	private static ProducerClient producerClient = null;
	private DefaultMQProducer producer;

	private ProducerClient(ProducerConfig producerConfig) {
		producer = new DefaultMQProducer(producerConfig.getProducerGroup());
		producer.setNamesrvAddr(producerConfig.getNamesrvAdd());
		producer.setSendMsgTimeout(producerConfig.getSendMsgTimeout());
	}

	static ProducerClient create(ProducerConfig producerConfig) throws MQClientException {
		if (producerClient != null) {
			throw new MQClientException("The producer client started ", null);
		}
		producerConfig.isVaildConfig();
		producerClient = new ProducerClient(producerConfig);
		return producerClient;
	}

	public static ProducerClient getInstance() {
		if (producerClient == null) {
			throw new RuntimeException("producer is not running!");
		}
		return producerClient;
	}

	void start() throws MQClientException {

		if (producer != null)
			producer.start();
	}

	void shutdown() {
		if (producer != null)
			producer.shutdown();
	}

	public SendResult sendMsg(String topic, String tags, String messageBody) throws UnsupportedEncodingException,
			MQClientException, RemotingException, MQBrokerException, InterruptedException {
		Message msg = new Message(topic, tags, messageBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
		return producer.send(msg);
	}

	public static class ProducerConfig {

		private String producerGroup;

		private String namesrvAdd;

		private int sendMsgTimeout;

		public String getProducerGroup() {
			return producerGroup;
		}

		public void setProducerGroup(String producerGroup) {
			this.producerGroup = producerGroup;
		}

		public String getNamesrvAdd() {
			return namesrvAdd;
		}

		public void setNamesrvAdd(String namesrvAdd) {
			this.namesrvAdd = namesrvAdd;
		}

		public int getSendMsgTimeout() {
			return sendMsgTimeout;
		}

		public void setSendMsgTimeout(int sendMsgTimeout) {
			this.sendMsgTimeout = sendMsgTimeout;
		}

		private void isVaildConfig() {

			if (this.getProducerGroup() == null) {
				throw new NullPointerException("producer [group] is null.");
			}
			if (this.getNamesrvAdd() == null) {
				throw new NullPointerException("producer [namesrvAdd] is null.");
			}
			if (this.getSendMsgTimeout() == 0) {
				throw new NullPointerException("producer [sendMsgTimeout] is null.");
			}
		}
	}

}
