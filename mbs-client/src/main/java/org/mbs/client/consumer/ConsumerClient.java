/**
 * Author : chizf
 * Date : 2020年4月2日 下午2:48:46
 * Title : org.mbs.client.consumer.ConsumerClient.java
 *
**/
package org.mbs.client.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class ConsumerClient {

	private ConsumerConfig consumerConfig;

	private DefaultMQPushConsumer consumer;

	private ConsumerClient(ConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}

	public static ConsumerClient getInstance(ConsumerConfig consumerConfig) {
		consumerConfig.isVaildConfig();
		return new ConsumerClient(consumerConfig);
	}

	public void registerMessageListener(MessageListenerConcurrently messageListenerConcurrently)
			throws MQClientException {
		consumer = new DefaultMQPushConsumer(this.consumerConfig.getConsumerGroup());

		consumer.setNamesrvAddr(this.consumerConfig.getNamesrvAddr());

		consumer.subscribe(this.consumerConfig.getTopic(), this.consumerConfig.getSubExpression());

		consumer.registerMessageListener(messageListenerConcurrently);

	}

	public void start() throws MQClientException {
		consumer.start();
	}

	public static class ConsumerConfig {
		private String consumerGroup;
		private String namesrvAddr;
		private String topic;
		private String subExpression;

		public String getConsumerGroup() {
			return consumerGroup;
		}

		public void setConsumerGroup(String consumerGroup) {
			this.consumerGroup = consumerGroup;
		}

		public String getNamesrvAddr() {
			return namesrvAddr;
		}

		public void setNamesrvAddr(String namesrvAddr) {
			this.namesrvAddr = namesrvAddr;
		}

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getSubExpression() {
			return subExpression;
		}

		public void setSubExpression(String subExpression) {
			this.subExpression = subExpression;
		}

		private void isVaildConfig() {

			if (this.getConsumerGroup() == null) {
				throw new NullPointerException("consumer [group] is null.");
			}
			if (this.getNamesrvAddr() == null) {
				throw new NullPointerException("consumer [namesrvAddr] is null.");
			}
			if (this.getTopic() == null) {
				throw new NullPointerException("consumer [topic] is null.");
			}

			if (this.getSubExpression() == null) {
				throw new NullPointerException("consumer [subExpression] is null.");
			}
		}
	}

}
